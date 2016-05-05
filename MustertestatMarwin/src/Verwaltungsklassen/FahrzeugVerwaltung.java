/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verwaltungsklassen;

import Fachklassen.Fahrzeug;
import java.util.ArrayList;

/**
 *
 * @author EDV-Beratung
 */
public class FahrzeugVerwaltung {

    private ArrayList<Fahrzeug> fahrzeugListe;

    public FahrzeugVerwaltung() {
        this.fahrzeugListe = new ArrayList<>();
    }

    public boolean pruefeSitzplatzAnzahlOK(String sitzplatzAnzahl) {
        byte anzahlSitzplatz = 0;

        try {
            anzahlSitzplatz = Byte.parseByte(sitzplatzAnzahl);
        } catch (NumberFormatException e) {
            return false; // sitzplatzanzahl ist keine Zahl
        }

        return true;
    }

    public boolean pruefeMietpreisOK(String mietpreisProTag) {
        double mietpreis = 0;

        try {
            mietpreis = Double.parseDouble(mietpreisProTag);
        } catch (NumberFormatException es) {
            return false; // mietpreis ist keine Zahl
        }

        return true;
    }

    public boolean fahrzeugAnlegen(String beschreibung, String sitzpleatze, String mietpreis) {
        this.fahrzeugListe.add(new Fahrzeug(beschreibung, Byte.valueOf(sitzpleatze), Double.valueOf(mietpreis)));
        return true;
    }

    public ArrayList<Fahrzeug> getFahrzeugListe() {
        return fahrzeugListe;
    }

    public boolean pruefeObBenutzereingabeOK(String benutzerEingabe) {
        int eingabeInt = 0;

        try {
            eingabeInt = Integer.parseInt(benutzerEingabe);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public boolean pruefeObFahrzeugVorhanden(String fahrzeugID) {
        int fahrzeugIDInt = Integer.parseInt(fahrzeugID);

        for (Fahrzeug fahrzeug : this.fahrzeugListe) {
            if (fahrzeug.getFahrzeugnummer() == fahrzeugIDInt) {
                return true;
            }
        }

        return false;
    }

    public Fahrzeug holeFahrzeugMittelsID(String id) {
        
        int idInt = Integer.parseInt(id);
        
        for (Fahrzeug f : this.fahrzeugListe) {
            if (f.getFahrzeugnummer() == idInt) {
                return f;
            }
        }
        return null;
    }

    public void aendereFahrzeugInformation(Fahrzeug fahrzeug, String aenderungsWert, String betroffeneInfo) {
        switch (betroffeneInfo) {
            case "bez":
                fahrzeug.setBezeichnung(aenderungsWert);
                break;
            case "sitz":
                fahrzeug.setSitzplatzAnzahl(Byte.parseByte(aenderungsWert));
                break;
            case "preis":
                fahrzeug.setMietPreisProTag(Double.parseDouble(aenderungsWert));
        }
    }

}
