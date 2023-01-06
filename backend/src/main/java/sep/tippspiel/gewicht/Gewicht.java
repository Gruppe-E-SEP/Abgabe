package sep.tippspiel.gewicht;

import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.tipprunde.Tipprunde;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gewicht implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ergebnis")
    private String ergebnis;

    @Column(name = "sieger")
    private String sieger;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(String ergebnis) {
        this.ergebnis = ergebnis;
    }

    public String getSieger() {
        return sieger;
    }

    public void setSieger(String sieger) {
        this.sieger = sieger;
    }

    public String getTordifferenz() {
        return tordifferenz;
    }
/*    public Tipprunde getTipprunde() {
        return tipprunde;
    }

    public void setTipprunde(Tipprunde tipprunde) {
        this.tipprunde = tipprunde;
    }*/

    public void setTordifferenz(String tordifferenz) {
        this.tordifferenz = tordifferenz;
    }

    @Column(name = "tordifferenz")
    private String tordifferenz;

/*
    @OneToOne(mappedBy = "gewicht")
    private Tipprunde tipprunde;
*/

    @OneToMany(mappedBy = "gewicht")
    private List<Tipprunde> tipprundeList = new ArrayList<>();

    public Gewicht() {};

    public Gewicht(String ergebnis, String sieger, String tordifferenz) {
        this.ergebnis = ergebnis;
        this.sieger = sieger;
        this.tordifferenz = tordifferenz;
    }
}
