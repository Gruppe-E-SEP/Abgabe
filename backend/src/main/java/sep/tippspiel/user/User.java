package sep.tippspiel.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {


    private String email;
    private String passwort;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }



}
