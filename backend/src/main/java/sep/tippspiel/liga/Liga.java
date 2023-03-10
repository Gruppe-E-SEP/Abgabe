package sep.tippspiel.liga;

import org.checkerframework.checker.units.qual.C;
import sep.tippspiel.spielplan.Spielplan;
import sep.tippspiel.tipprunde.Tipprunde;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Liga implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ligaImage")
    private String ligaImage;

    public Spielplan getSpielplan() {
        return spielplan;
    }

    public void setSpielplan(Spielplan spielplan) {
        this.spielplan = spielplan;
    }

    @OneToOne
    @JoinColumn(name = "spielplan_id")
    private Spielplan spielplan;

    @OneToOne(mappedBy = "liga")
    private Tipprunde tipprunde;

    public Liga(){};

    public Liga(String name, Spielplan spielplan, String ligaImage) {
        this.name = name;
        this.spielplan = spielplan;
        this.ligaImage = ligaImage;
    }

    public Long getId() {
        return id;
    }

    public Tipprunde getTipprunde() {
        return tipprunde;
    }

    public void setTipprunde(Tipprunde tipprunde) {
        this.tipprunde = tipprunde;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLigaImage() {
        return ligaImage;
    }

    public void setLigaImage(String ligaImage) {
        this.ligaImage = ligaImage;
    }
}
