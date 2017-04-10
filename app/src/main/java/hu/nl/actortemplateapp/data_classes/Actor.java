package hu.nl.actortemplateapp.data_classes;

import hu.nl.actortemplateapp.activities.SignInActivity;

/**
 * Created by Dyon on 24-3-2017.
 */
public class Actor {
    public String aantekeningen;
    public String email;
    public String foto;
    public String functie;
    public String naam;
    public String key;
    public String telefoonnummer;

    public Actor() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAantekeningen() { return aantekeningen; }

    public void setAantekeningen(String aantekeningen) {
        this.aantekeningen = aantekeningen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
}
