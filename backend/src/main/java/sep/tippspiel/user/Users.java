package sep.tippspiel.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sep.tippspiel.tipp.Tipp;
import sep.tippspiel.tipprunde.Tipprunde;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @NotEmpty(message = "Vorname darf nicht leer sein")
    @Column(name = "vorname")
    private String vorname;
    @NotNull
    @NotEmpty(message = "Nachname darf nicht leer sein")
    @Column(name = "nachname")
    private String nachname;
    @NotNull
    @NotEmpty(message = "Email darf nicht leer sein")
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "date")
    private Date date;
    @NotNull
    @NotEmpty(message = "Passwort darf nicht leer sein")
    @Column(name = "passwort")
    private String passwort;

    @Column(name = "image")
    private String image;
    @Column(name = "LoggedIn")
    private boolean isLoggedIn = false;


    @OneToMany(mappedBy = "users")
    private List<Tipprunde>  tipprundeList= new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Tipp> tippList = new ArrayList<>();

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void logIn(){
        this.isLoggedIn = true;
    }
    public void logOut(){this.isLoggedIn = false;
    }

    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }



    public Users(String vorname, String nachname,Date date, String email, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.date = date;
        this.email = email;
        this.passwort = passwort;
        this.isLoggedIn = isLoggedIn;

    }

    @Override
    public String toString() {return "Vorname: " + vorname + " Nachname: " + nachname + "E-Mail: " + email; }


}