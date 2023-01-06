package sep.tippspiel.tipprunde;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.tippspiel.generaluser.GeneralUser;
import sep.tippspiel.gewicht.Gewicht;
import sep.tippspiel.gewicht.GewichtRepository;
import sep.tippspiel.liga.Liga;
import sep.tippspiel.liga.LigaRepository;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spiel.SpielRepository;
import sep.tippspiel.tipp.Tipp;
import sep.tippspiel.tipp.TippRepository;
import sep.tippspiel.user.UserController;
import sep.tippspiel.user.UserRepository;
import sep.tippspiel.user.Users;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@Service
public class TipprundeService {

    @Autowired
    private TipprundeRepository tipprundeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LigaRepository ligaRepository;

    @Autowired
    private SpielRepository spielRepository;

    @Autowired
    private TippRepository tippRepository;

    @Autowired
    private GewichtRepository gewichtRepository;

    public int bewerteTippspiel(String vorhersage, String score) {
        //richtiges Ergebnis
        if (vorhersage.equals(score)) {
            return Integer.parseInt(this.gewichtRepository.getReferenceById(1l).getErgebnis());
        }

        String[] tokens1 = vorhersage.split("-");
        String[] tokens2 = score.split("-");

        //richtige Tordifferenz

        if (((Integer.parseInt(tokens1[0]) < Integer.parseInt(tokens1[1])) &&
                (Integer.parseInt(tokens2[0]) < Integer.parseInt(tokens2[1])))
                || /*((Integer.parseInt(tokens1[0]) == Integer.parseInt(tokens1[1])) &&
                (Integer.parseInt(tokens2[0]) == Integer.parseInt(tokens2[1])))
                ||*/ ((Integer.parseInt(tokens1[0]) > Integer.parseInt(tokens1[1])) &&
                (Integer.parseInt(tokens2[0]) > Integer.parseInt(tokens2[1])))) {
            return Integer.parseInt(this.gewichtRepository.getReferenceById(1l).getSieger());
        }

        if (Math.abs(Integer.parseInt(tokens1[0]) - Integer.parseInt(tokens1[1]))== Math.abs(Integer.parseInt(tokens2[0]) - Integer.parseInt(tokens2[1]))) {
            return Integer.parseInt(this.gewichtRepository.getReferenceById(1l).getTordifferenz());
        }

        return 0;
    }

    public Tipprunde erstelleTipprunde(String name, Status status, Long besitzerId) {
        Tipprunde tipprunde = new Tipprunde();
        tipprunde.setName(name);
        tipprunde.setStatus(status);
        tipprunde.setUsers(this.userRepository.getReferenceById(besitzerId));
        Gewicht gewicht = new Gewicht("3", "2", "1");
        this.gewichtRepository.save(gewicht);
        tipprunde.setGewicht(this.gewichtRepository.getReferenceById(gewicht.getId()));
        return this.tipprundeRepository.save(tipprunde);
    }
    public boolean setTipprundePasswort(Long id, String passwort) {
        Tipprunde tipprunde = this.tipprundeRepository.getReferenceById(id);
        String sha256hex = Hashing.sha256()
                .hashString(passwort, StandardCharsets.UTF_8)
                .toString();
        tipprunde.setPasswort(sha256hex);
        try {
            this.tipprundeRepository.save(tipprunde);
            return true;
        } catch (Exception e) {
            System.err.println("Passwort konnte nicht gespeichert werden: " + e.getStackTrace());
            return false;
        }
    }

    public boolean setTipprundeLiga(Long tipprundeId, Long ligaId) {
        Tipprunde tipprunde = this.tipprundeRepository.getReferenceById(tipprundeId);
        Liga liga = this.ligaRepository.getReferenceById(ligaId);
        try {
            tipprunde.setLiga(liga);

            this.tipprundeRepository.save(tipprunde);
            return true;
        } catch ( Exception e) {
            System.err.println("Liga konnte nicht gesetzt werden: "  + e.getStackTrace());
            return false;
        }
    }

    public boolean setTipprundeSpiel(Long tipprundeId, Long spielId) {
        Tipprunde tipprunde = this.tipprundeRepository.getReferenceById(tipprundeId);
        Spiel spiel = this.spielRepository.getReferenceById(spielId);
        try {
            tipprunde.setSpiel(spiel);
            this.tipprundeRepository.save(tipprunde);
            return true;
        } catch (Exception e) {
            System.err.println("Spiel konnte nicht gesetzt werden: " + e.getStackTrace());
            return false;
        }
    }

    public String setGewichte(String egebnis, String sieger, String tordifferenz, Long tipprundeId) {
        Tipprunde tipprunde = this.tipprundeRepository.getReferenceById(tipprundeId);

        Gewicht gewicht = new Gewicht(egebnis, sieger, tordifferenz);
        this.gewichtRepository.save(gewicht);
        tipprunde.setGewicht(this.gewichtRepository.getReferenceById(gewicht.getId()));
        this.tipprundeRepository.save(tipprunde);
        return "Gewichte wurden erfolgreich gesetzt";
    }

    public String tippBewertungAbschliessen(Long tipprundeId) {
        Tipprunde tipprunde = this.tipprundeRepository.getReferenceById(tipprundeId);
        Long spielId = this.tipprundeRepository.getReferenceById(tipprundeId).getSpiel().getId();

        if(this.spielRepository.getReferenceById(spielId).getScore()!=null) {
            List<Tipp> tippList = this.tippRepository.findAll();
            for(Tipp tipp: tippList) {
                if(tipp.getTipprunde().getId()==tipprundeId) {
                    int punkte = (bewerteTippspiel(tipp.getTipp() ,this.spielRepository.getReferenceById(spielId).getScore()));
                    if(punkte==3) {
                        punkte = Integer.parseInt(this.gewichtRepository.getReferenceById(tipprunde.getGewicht().getId()).getErgebnis());
                    } else if (punkte==2) {
                        punkte = Integer.parseInt(this.gewichtRepository.getReferenceById(tipprunde.getGewicht().getId()).getSieger());
                    } else if (punkte==1) {
                        punkte = Integer.parseInt(this.gewichtRepository.getReferenceById(tipprunde.getGewicht().getId()).getTordifferenz());
                    } else {
                        punkte = 0;
                    }

                    tipp.setPunkte(punkte);
                    this.tippRepository.save(tipp);
                }
            }
            return "Tippbewertung wurde erfolgreich abgeschloßen";
        } else {
            return "Ergebnis des Spieles ist noch nicht bekannt oder nicht eingetragen worden ist";
        }
    }

    public List<Tipprunde> gibAllTipprunden() {
        return this.tipprundeRepository.findAll();
    }





}
