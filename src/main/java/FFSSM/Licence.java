/**
 * @(#) LicencePlongeur.java
 */
package FFSSM;

import java.time.LocalDate ;

public class Licence {
    //La variable possesseur est en read Only
    public Personne possesseur;

    public String numero;

    public LocalDate delivrance;

    public int niveau;

    public Club club;

    public Licence(Personne possesseur, String numero, LocalDate delivrance, int niveau, Club club) {
        this.possesseur = possesseur;
        this.numero = numero;
        this.delivrance = delivrance;
        this.niveau = niveau;
        this.club = club;
    }

    public Personne getPossesseur() {
        return possesseur;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getDelivrance() {
        return delivrance;
    }

    public int getNiveau() {
        return niveau;
    }

    public Club getClub() {
        return club;
    }

    /**
     * Est-ce que la licence est valide à la date indiquée ?
     * Une licence est valide pendant un an à compter de sa date de délivrance
     * @param d la date à tester
     * @return vrai si valide à la date d
     **/
    public boolean estValide(LocalDate d) {
         LocalDate Today;
         Today=LocalDate.now();
         //Avec now() on récupère la date actuelle
         
         //isBefore renvoie True si la date d'aujourd'hui est antérieur à la date à tester
         
         return d.isAfter(Today);
    }

}
