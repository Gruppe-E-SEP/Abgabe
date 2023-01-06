package sep.tippspiel.tipp;

import org.springframework.beans.factory.annotation.Autowired;
import sep.tippspiel.tipprunde.Tipprunde;
import sep.tippspiel.user.UserRepository;
import sep.tippspiel.user.Users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tipp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipp")
    private String tipp;

    @Column(name = "punkte")
    private int punkte;


    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

/*    @OneToMany(mappedBy = "tipp")
    private List<Tipprunde> tipprundeList = new ArrayList<>();*/

    @ManyToOne
    @JoinColumn(name = "tipprunde_id")
    private Tipprunde tipprunde;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tipp() {};

    public String getTipp() {
        return tipp;
    }

    public void setTipp(String tipp) {
        this.tipp = tipp;
    }

    public int getPunkte() {
        return punkte;
    }

    public Tipprunde getTipprunde() {
        return tipprunde;
    }

    public void setTipprunde(Tipprunde tipprunde) {
        this.tipprunde = tipprunde;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Tipp(String tipp, Users user) {
        this.tipp = tipp;
        this.users = user;
    }



}
