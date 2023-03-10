package sep.tippspiel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sep.tippspiel.gewicht.Gewicht;
import sep.tippspiel.gewicht.GewichtRepository;
import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.mannschaft.MannschaftRepository;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spiel.SpielRepository;
import sep.tippspiel.spielplan.Spielplan;
import sep.tippspiel.spielplan.SpielplanRepository;
import sep.tippspiel.spieltag.Spieltag;
import sep.tippspiel.spieltag.SpieltagRepository;
import sep.tippspiel.systemadministrator.Systemadministrator;
import sep.tippspiel.systemadministrator.SystemadministratorRepository;
import sep.tippspiel.user.UserRepository;
import sep.tippspiel.user.Users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) throws ParseException {

        String date = "2011-11-11";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateInString = date;
        Date dateD = formatter.parse(dateInString);

        Date datev = new Date(2001,12,11);
        Users u1 = new Users("Melika", "Front" ,datev, "mel.front@gmail.com", "angular");
        Users u2 = new Users("Miriam", "Test" ,datev, "mir.test@gmail.com", "junit");
        Users u3 = new Users("Vincent", "TS" ,datev, "vin.front@gmail.com", "typescript");
        Users u4 = new Users("Kevin", "Docker" ,dateD, "kev.dc@gmail.com", "docker");
        Users u5 = new Users("Serghei", "Back" ,dateD, "ser.sp@gmail.com", "springboot");

        Systemadministrator sa1 = new Systemadministrator("System", "Administrator", "sysadmin@web.de","sysadmin");

        return args -> {
            log.info("Preloading " + userRepository.save(u1));
            log.info("Preloading " + userRepository.save(u2));
            log.info("Preloading " + userRepository.save(u3));
            log.info("Preloading " + userRepository.save(u4));
            log.info("Preloading " + userRepository.save(u5));

        };
    }
    @Bean
    CommandLineRunner initSA(SystemadministratorRepository systemadministratorRepository) {

        Systemadministrator sa1 = new Systemadministrator("System", "Administrator", "sysadmin@web.de","sysadmin");

        return args -> {
            log.info("Preloading " + systemadministratorRepository.save(sa1));

        };
    }

    @Bean
    CommandLineRunner initGewicht(GewichtRepository gewichtRepository) {

        Gewicht g1 = new Gewicht("3","2","1");

        return args -> {
            log.info("Preloading" + gewichtRepository.save(g1));
        };
    }

    
}
