package sep.tippspiel.user;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sep.tippspiel.liga.Liga;
import sep.tippspiel.liga.LigaRepository;
import sep.tippspiel.liga.LigaService;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spiel.SpielService;
import sep.tippspiel.tipp.TippService;
import sep.tippspiel.tipprunde.Status;
import sep.tippspiel.tipprunde.Tipprunde;
import sep.tippspiel.tipprunde.TipprundeService;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static sep.tippspiel.user.UserService.isValidEmailAddress;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    LigaService ligaService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TipprundeService tipprundeService;

    @Autowired
    LigaRepository ligaRepository;

    @Autowired
    SpielService spielService;

    @Autowired
    TippService tippService;



    @PostMapping(path = "/create", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody Users user) {

        if(isValidEmailAddress(user.getEmail())){
            if(this.userService.findByEmail(user.getEmail())!=null) {
                return new ResponseEntity<>("User mit diesem E-Mail-Adresse ist bereits registriert", HttpStatus.OK);
            } else {
                if(this.userService.createUser(user.getVorname(), user.getNachname(),user.getDate(),user.getEmail(), user.getPasswort())) {
                    return new ResponseEntity<>("User wurde erstellt:", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("User konnte nicht erstellt werden", HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>("Email ist ungültig:", HttpStatus.BAD_REQUEST);
        }


    }
    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        System.out.println(user.getEmail() + " " + user.getPasswort());

        Long id = this.userRepository.findUserIdByEmail(user.getEmail());
        Users users = this.userRepository.getReferenceById(id);
        System.out.println(users.getEmail()  + " " +  user.getPasswort());



        if(this.userService.loginUser(users.getEmail(), users.getPasswort())){
            return new ResponseEntity<>("Erfolgreich eingeloggt", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User konnte nicht eingeloggt werden", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(path = "/logout", consumes = "application/json")
    public ResponseEntity<String> logoutUser (@RequestBody User user){
        Users users = new Users();
        user.setEmail(users.getEmail());

        if(this.userService.logoutUser(user.getEmail())){
            return new ResponseEntity<>("Erfolgreich ausgeloggt", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User konnte nicht ausgeloggt werden", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> allUsers = this.userService.all();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{vorname}", produces = "application/json")
    public ResponseEntity<List<Users>> getUsersByName(@PathVariable String vorname) {
        List<Users> usersByName = this.userService.findByName(vorname);
        return new ResponseEntity<>(usersByName, HttpStatus.OK);
    }

    @GetMapping(path = "/allspiele", produces = "application/json")
    public ResponseEntity<List<Spiel>> getAllSpiele() {
        List<Spiel> allspiele = this.userService.allspiele();
        return new ResponseEntity<>(allspiele, HttpStatus.OK);
}

    @PutMapping(path = "/setuserimage")
    public ResponseEntity<String> setUserImage(@RequestParam String email, @RequestParam("image")MultipartFile multipartFile) {

        if(this.ligaService.istImageFormat(multipartFile)) {
            Long id = this.userRepository.findUserIdByEmail(email);
            String filename = "src/main/resources/userImage"+id.toString()+".jpeg";

            if(this.userService.setUserImage(email,filename)) {
                File file = new File(filename);

                try (OutputStream os = new FileOutputStream(file)) {
                    os.write(multipartFile.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return new ResponseEntity<>("UserImage wurde gespeichert", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Image konnte nicht gespeichert werden", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Es darf nur JPEG Format verwendet werden", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/gibtipphilfe"/*, produces = "application/json"*/)
    public ResponseEntity<String> gibMannschaftVorhersage(@RequestParam String name) {
        String vorhersage = this.spielService.calcVorhersage(name);
/*        int bewertung = this.tipprundeService.bewerteTippspiel("Tordifferenz-4", "0-3");
        System.out.println("SpielAuswertung: " + bewertung);*/

        Users users = this.userRepository.getReferenceById(1l);
        this.tipprundeService.erstelleTipprunde("neueTipprunde", Status.PRIVATE, 1L);
        this.tippService.erstelleTipp("2-2", users.getId());
        this.tippService.erstelleTipp("0-1", 2l);
        this.tippService.erstelleTipp("5-3", 4l);
        this.tippService.erstelleTipp("0-0", 3l);


        return new ResponseEntity<>(vorhersage, HttpStatus.OK);
    }

    @PostMapping(path = "/createTipprunde")
    public ResponseEntity<String> erstelleTipprunde(String name, Status status, Long besitzerId) {
        this.tipprundeService.erstelleTipprunde(name, status, besitzerId);
        return new ResponseEntity<>("Tipprunde wurde erfolgreich erstellt", HttpStatus.OK);
    }

    @PostMapping(path = "/createTipp")
    public ResponseEntity<String> erstelleTipp(String vorhersage, Long besitzerId) {
        this.tippService.erstelleTipp(vorhersage, besitzerId);
        return new ResponseEntity<>("Tipp wurde erflolgreich erstellt", HttpStatus.OK);
    }


    @PutMapping(path = "/setTipprundePassword")
    public ResponseEntity<String> setTipprundePasswort(@RequestParam Long id,@RequestParam  String passwort) {
        if(this.tipprundeService.setTipprundePasswort(id, passwort)){
            return new ResponseEntity<>("Passwort wurde gesetzt:", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Passwort konnte nicht gesetzt werden", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/setTipprundeLiga")
    public ResponseEntity<String> setTipprundeLiga(@RequestParam Long tipprundeId, @RequestParam Long ligaId) {
        if(this.tipprundeService.setTipprundeLiga(tipprundeId, ligaId)){
            return new ResponseEntity<>("Liga ist zugeordnet : ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Liga konnte nicht zugeordet werden ", HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping(path = "/settTipprundeSpiel")
    public ResponseEntity<String> setTipprundeSpiel(@RequestParam Long tipprundeId, @RequestParam Long spielId) {
        if(this.tipprundeService.setTipprundeSpiel(tipprundeId, spielId)){
            return new ResponseEntity<>("Spiel ist zugeordnet :", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Spiel konnte nicht zugeordnet werden ", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/setTippTipprunde")
    public ResponseEntity<String> setTippTipprunde(@RequestParam Long tippId, @RequestParam Long tipprundeId) {
        if(this.tippService.setTippTiprrunde(tippId,tipprundeId)) {
            return new ResponseEntity<>("Tipprunde ist zugeordnet", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tipprunde konnte nicht zugeordent werden", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/gibAlleSpiele")
    public ResponseEntity<List<Spiel>> gibAlleSpiele() {
        List<Spiel> spielList= this.spielService.gibAlleSpiele();
        return new ResponseEntity<>(spielList, HttpStatus.OK);
    }

    @GetMapping(path = "/gibAlleLiga")
    public ResponseEntity<List<Liga>> gibAlleLiga() {
        List<Liga> ligaList = this.ligaService.gibAlleLiga();
        return new ResponseEntity<>(ligaList, HttpStatus.OK);
    }

/*    @GetMapping(path = "/gibAlleTipprunden")
    public ResponseEntity<List<Tipprunde>> giballTipprunden() {
        List<Tipprunde> tipprundeList = this.tipprundeService.gibAlltipprunden();
        return new ResponseEntity<>(tipprundeList, HttpStatus.OK);
    }*/

    @GetMapping(path = "/gibAnstehendeSpiele")
    public ResponseEntity<List<Spiel>> gibAnstehendeSpiele(@RequestParam String date) throws ParseException {
        List<Spiel> spielList= this.spielService.gibAnstehendeSpiele(date);
        return new ResponseEntity<>(spielList, HttpStatus.OK);
    }

    @PutMapping(path = "/tipprundeAbschliessen")
    public ResponseEntity<String> tipprundeAbschliessen(@RequestParam Long tipprundeId) {
        this.tipprundeService.tippBewertungAbschliessen(tipprundeId);
        return new ResponseEntity<>("Tipprunden Bewertung wurde abgeschloßen", HttpStatus.OK);
    }

    @PutMapping(path = "/setGewichte")
    public ResponseEntity<String> setGewichte(@RequestParam String ergebnis, @RequestParam String sieger, @RequestParam String tordifferenz,@RequestParam Long tipprundeId) {
        this.tipprundeService.setGewichte(ergebnis,sieger,tordifferenz,tipprundeId);
        return new ResponseEntity<>("Gewichte wurden erfolgreich gespeichert", HttpStatus.OK);
    }



}
