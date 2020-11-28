/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;

import java.util.ArrayList;

public class Plongee {

	public Site lieu;

	public Moniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;

        public Club organisateur;

        public ArrayList<Licence> palanquee;

	public Plongee(Club organisateur,Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
                this.organisateur=organisateur;
                palanquee=new ArrayList();
                //On faut dire à l'organisateur qu'il s'agit de sa plongee
                organisateur.organisePlongee(this);


	}
        public Site getLieu(){
            return lieu;}
            
        public ArrayList<Licence> getPalanquee(){
                return palanquee;}

        public Club getOrganisateur(){
                return organisateur;}

	public void ajouteParticipant(Plongeur participant) {
                ArrayList<Licence> licences=new ArrayList();
                licences=participant.getLicences();
                Licence dernierLicence=licences.get(licences.size()-1);

		if(!palanquee.contains(dernierLicence)){
                        palanquee.add(dernierLicence);}
	}

	public LocalDate getDate() {
		return date;
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
            
             for(Licence l:palanquee){
                if(l.estValide(l.getDelivrance())==false){
                   return false;
                }
            }
             return true;
                
                
	}

}
