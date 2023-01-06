package sep.tippspiel.tipprunde;

import sep.tippspiel.gewicht.Gewicht;
import sep.tippspiel.liga.Liga;
import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.tipp.Tipp;
import sep.tippspiel.user.Users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tipprunde implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "passwort")
    String passwort;

    @Column(name = "status")
    Status status;

    @OneToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;


    public Gewicht getGewicht() {
        return gewicht;
    }

    public void setGewicht(Gewicht gewicht) {
        this.gewicht = gewicht;
    }

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToOne
    @JoinColumn(name = "spiel_id")
    private Spiel spiel;

/*    @OneToOne
    @JoinColumn(name = "gewicht_id")
    private Gewicht gewicht;*/

    @ManyToOne
    @JoinColumn(name = "gewicht_id")
    private Gewicht gewicht;

/*    @ManyToOne
    @JoinColumn(name = "tipp_id")
    private Tipp tipp;*/

    @OneToMany(mappedBy = "tipprunde")
    private List<Tipp> tippList = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Tipprunde() {}

    public Tipprunde(String name, Status status, Users user) {
        this.users = user;
        this.name = name;
        this.status = status;
    }

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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Spiel getSpiel() {
        return spiel;
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

/*    public Tipp getTipp() {
        return tipp;
    }
    public void setTipp(Tipp tipp) {
        this.tipp = tipp;
    }*/

/*    public List<Tipp> getTippList() {
        return tippList;
    }
    public void setTippList(List<Tipp> tippList) {
        this.tippList = tippList;
    }*/

    @Override
    public String toString() {return "Name:" + name + " Status: " + status;}

}
