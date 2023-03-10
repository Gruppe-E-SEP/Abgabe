package sep.tippspiel.spielplan;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import sep.tippspiel.liga.Liga;
import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.mannschaft.MannschaftRepository;
import sep.tippspiel.mannschaft.MannschaftService;
import sep.tippspiel.spieltag.Spieltag;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spielplan implements Serializable {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

/*    @ManyToOne
    @JoinColumn(name = "spieltag")
    private Spieltag spieltag;*/

    public Spielplan() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    @OneToOne(mappedBy = "spielplan")
    private Liga liga;

    public List<Spieltag> getSpieltagList() {
        return spieltagList;
    }

    public void setSpieltagList(List<Spieltag> spieltagList) {
        this.spieltagList = spieltagList;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spielplan")
    private List<Spieltag> spieltagList = new ArrayList<>();


}
