package sep.tippspiel.liga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LigaService {

    @Autowired
    LigaRepository ligaRepository;

    public boolean createLiga(String name) {
        Liga liga = new Liga();
        liga.setName(name);
        if(!this.ligaRepository.existsByName(name)) {
            try {
                this.ligaRepository.save(liga);
                return true;
            } catch (Exception e) {
                System.err.println("Liga konnte nicht geschpeichert werden: " + e.getStackTrace());
                return false;
            }
        } else {
            System.out.println("Liga mit der Name " + name + " ist bereits angelegt");
            return false;
        }

    }

    public boolean setLigaName(String oldName, String newName) {
        Long id = this.ligaRepository.findByName(oldName);
        try {
            Liga liga = this.ligaRepository.getReferenceById(id);
            liga.setName(newName);
            this.ligaRepository.save(liga);
            return  true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

    }

    public boolean setLigaNameById(Long id, String newName) {
        try {
            Liga liga = this.ligaRepository.getReferenceById(id);
            liga.setName(newName);
            this.ligaRepository.save(liga);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

    }

    public boolean setLigaImage(Long id, String ligaImage) {
        try {
            Liga liga = this.ligaRepository.getReferenceById(id);
            liga.setName(ligaImage);
            this.ligaRepository.save(liga);
            return true;
        } catch (Exception e) {
            System.out.println("Liga Image konnte nicht gesetzt werden" + e.getStackTrace());
            return false;
        }
    }

    public boolean istImageFormat(MultipartFile file) {
        String TYPE = "image/jpeg";

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public List<Liga> gibAlleLiga() {
        return this.ligaRepository.findAll();
    }

}
