package sep.tippspiel.mannschaft;

import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.tipprunde.Tipprunde;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mannschaft implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;

/*  @ManyToOne
    @JoinColumn(name = "spiel_id")
    private Spiel spiel;*/

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mannschaft")
    private List<Spiel> spielList = new ArrayList<>();


    public Mannschaft() {};

    public Mannschaft(String name)  {
        this.name = name;
    }

    @Override
    public String toString() {return "Name: " + name;}

/*    public Spiel getSpiel() {
        return spiel;
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }*/
}
