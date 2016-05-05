/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oberflaeche;

import Fachklassen.Fahrzeug;
import Verwaltungsklassen.FahrzeugVerwaltung;
import java.util.Scanner;

/**
 *
 * @author EDV-Beratung
 */
public class FahrzeugeVerwaltenAnsicht {

    private MenueSteuerung menueSteuerung;
    private Scanner scanner = new Scanner(System.in);

    public FahrzeugeVerwaltenAnsicht(MenueSteuerung menue) {
        this.menueSteuerung = menue;
    }

    public void fahrzeugAnlegenMenueAufrufen() {
        FahrzeugVerwaltung fahrzeugVerwaltung = this.menueSteuerung.getFahrzeugVerwaltung();

        String bezeichnung = "";
        String sitzplatzAnzahl = "";
        String mietpreisProTag = "";

        boolean sitzAnzahlNOk = true;
        boolean preisNOk = true;

        System.out.println("Bitte machen Sie folgende Angaben:");
        System.out.println("Fahrzeugbezeichnung:\n");
        bezeichnung = this.scanner.next();

        while (sitzAnzahlNOk) {

            System.out.println("Sitzplatzanzahl:\n");
            sitzplatzAnzahl = this.scanner.next();

            if (fahrzeugVerwaltung.pruefeSitzplatzAnzahlOK(sitzplatzAnzahl)) {
                sitzAnzahlNOk = false;
            } else {
                System.out.println("Keine Zahl eingegeben. Eingabe wiederholen");
            }

        }

        while (preisNOk) {

            System.out.println("Mietpreis pro Tag:\n");
            mietpreisProTag = this.scanner.next();

            if (fahrzeugVerwaltung.pruefeMietpreisOK(mietpreisProTag)) {
                preisNOk = false;
            } else {
                System.out.println("Keine Zahl eingegeben. Eingabe wiederholen");
            }

        }

        if (fahrzeugVerwaltung.fahrzeugAnlegen(bezeichnung, sitzplatzAnzahl, mietpreisProTag)) {
            System.out.println("Fahrzeug wurde erfolgreich angelegt");
            //menueSteuerung.fahrzeugverwaltungAnzeigen();
            return;
        }

    }

    public void fahrzeugeAnzeigenMuenueAufrufen() {
        FahrzeugVerwaltung fahrzeugVerwaltung = this.menueSteuerung.getFahrzeugVerwaltung();

        if (!fahrzeugVerwaltung.getFahrzeugListe().isEmpty()) {

            for (Fahrzeug fahrzeug : fahrzeugVerwaltung.getFahrzeugListe()) {
                System.out.println("ID:                 " + fahrzeug.getFahrzeugnummer());
                System.out.println("Bezeichnung:        " + fahrzeug.getBezeichnung());
                System.out.println("Sitzplatzanzahl:    " + fahrzeug.getSitzplatzAnzahl());
                System.out.println("Miete pro Tag:      " + fahrzeug.getMietPreisProTag());
                System.out.println("----------------------------------------");
            }

        } else {
            System.out.println("Keine Fahrzeuge vorhanden!");
        }

    }

    public void fahrzeugeAendernMenueAnzeigen() {
        String benutzerEingabe = "";
        String benutzerEingabeAenderung = "";
        boolean solangeEingabeFalsch = true;
        boolean solangeMenueAuswahlFalsch = true;
        Fahrzeug fahrzeugAusgewaehlt = null;

        System.out.println("Waehlen Sie ein Fahrzeug aus, indem Sie die entsprechende ID eingeben");

        FahrzeugVerwaltung fahrzeugVerwaltung = this.menueSteuerung.getFahrzeugVerwaltung();

        if (!fahrzeugVerwaltung.getFahrzeugListe().isEmpty()) {

            for (Fahrzeug fahrzeug : fahrzeugVerwaltung.getFahrzeugListe()) {
                System.out.println("ID:                 " + fahrzeug.getFahrzeugnummer());
                System.out.println("Bezeichnung:        " + fahrzeug.getBezeichnung());
                System.out.println("----------------------------------------");
            }

        } else {
            System.out.println("Keine Fahrzeuge vorhanden!");
            return;
        }

        System.out.println("Waehlen Sie ein Fahrzeug aus, indem Sie die entsprechende ID eingeben");

        while (solangeEingabeFalsch) {

            benutzerEingabe = this.scanner.next();

            if (fahrzeugVerwaltung.pruefeObBenutzereingabeOK(benutzerEingabe)) {
                solangeEingabeFalsch = false;
            } else {
                System.out.println("Ihre Eingabe entspricht keiner Zahl! Bitte geben Sie die ID des zu bearbeitenden Fahrzeuges ein:\n");
            }

        }

        if (fahrzeugVerwaltung.pruefeObFahrzeugVorhanden(benutzerEingabe)) {

            fahrzeugAusgewaehlt = fahrzeugVerwaltung.holeFahrzeugMittelsID(benutzerEingabe);
            String aenderungsWert = "";

            System.out.println("Fahrzeug: " + fahrzeugAusgewaehlt.getFahrzeugnummer() + " " + fahrzeugAusgewaehlt.getBezeichnung());
            System.out.println("Welche Informationen möchten Sie ändern?");
            System.out.println("Fahrzeugbezeichnung:        1");
            System.out.println("Sitzplatzanzahl:            2");
            System.out.println("Monatlicher Mietpreis:      3");

            while (solangeMenueAuswahlFalsch) {

                benutzerEingabeAenderung = this.scanner.next();

                switch (this.menueSteuerung.pruefeEingabeBenutzer(benutzerEingabeAenderung, "fahrAendern")) {
                    case 1:
                        System.out.println("Geben Sie eine neue Fahrzeugbezeichnung an:");
                        System.out.println("Alter Wert: " + fahrzeugAusgewaehlt.getBezeichnung());
                        System.out.println("Neuer Wert: ");

                        aenderungsWert = this.scanner.next();

                        if (!aenderungsWert.equals("")) {
                            fahrzeugVerwaltung.aendereFahrzeugInformation(fahrzeugAusgewaehlt, aenderungsWert, "bez");
                            return;
                        } else {
                            System.out.println("Bitte eine Eingabe treffen!");
                        }
                        break;
                    case 2:
                        System.out.println("Geben Sie eine neue Sitzplatzanzahl an:");
                        System.out.println("Alter Wert: " + fahrzeugAusgewaehlt.getSitzplatzAnzahl());
                        System.out.println("Neuer Wert: ");

                        aenderungsWert = this.scanner.next();

                        if (!aenderungsWert.equals("")) {
                            if(fahrzeugVerwaltung.pruefeSitzplatzAnzahlOK(aenderungsWert)){
                            fahrzeugVerwaltung.aendereFahrzeugInformation(fahrzeugAusgewaehlt, aenderungsWert, "sitz");
                            return;
                            }else{
                                System.out.println("Bitte eine Zahl angeben");
                            }
                        } else {
                            System.out.println("Bitte eine Eingabe treffen!");
                        }
                        break;

                    case 3:
                        System.out.println("Geben Sie einen neuen Monatspreis an:");
                        System.out.println("Alter Wert: " + fahrzeugAusgewaehlt.getMietPreisProTag());
                        System.out.println("Neuer Wert: ");

                        aenderungsWert = this.scanner.next();

                        if (!aenderungsWert.equals("")) {
                            if(fahrzeugVerwaltung.pruefeMietpreisOK(aenderungsWert)){
                            fahrzeugVerwaltung.aendereFahrzeugInformation(fahrzeugAusgewaehlt, aenderungsWert, "preis");
                            return;
                            }else{
                                System.out.println("Bitte eine Zahl angeben");
                            }
                        } else {
                            System.out.println("Bitte eine Eingabe treffen!");
                        }
                        break;

                    case 99099:
                        System.out.println("Keine Zahl angegeben!");
                }

            }

        } else {
            System.out.println("Die angegenebe FahrzeugID existiert nicht!");
        }
    }

}
