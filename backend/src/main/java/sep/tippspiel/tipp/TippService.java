package sep.tippspiel.tipp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import sep.tippspiel.tipprunde.Tipprunde;
import sep.tippspiel.tipprunde.TipprundeRepository;
import sep.tippspiel.user.UserRepository;
import sep.tippspiel.user.Users;

@Service
public class TippService {

    @Autowired
    TippRepository tippRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TipprundeRepository tipprundeRepository;

    public boolean erstelleTipp(String vorhersage, Long userId) {
        Tipp tipp = new Tipp();
        Users users = this.userRepository.getReferenceById(userId);
        tipp.setTipp(vorhersage);
        tipp.setUsers(users);
        try {
            this.tippRepository.save(tipp);
            return true;
        } catch (Exception e) {
            System.err.println("Tipp konnte nicht erstellt werden: " + e.getStackTrace());
            return false;
        }
    }

    public boolean setTippTiprrunde(Long tippId, Long tipprundeId) {
        Tipp tipp = this.tippRepository.getReferenceById(tippId);
        Tipprunde tipprunde = this.tipprundeRepository.getReferenceById(tipprundeId);

        try {
            tipp.setTipprunde(tipprunde);
            this.tippRepository.save(tipp);
            return true;
        } catch (Exception e) {
            System.err.println("Tipprunde konnte nicht gesetzt werden:" + e.getStackTrace());
            return false;
        }
    }
}
