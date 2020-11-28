/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class Moniteur extends Personne {

    private int numeroDiplome;
    private ArrayList<Embauche> embauches;
    private ArrayList<Club >employeurs ;
    private ArrayList<Plongee> plongeesDirigees;
    
    

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.numeroDiplome = numeroDiplome;
        embauches=new ArrayList();
        employeurs=new ArrayList();
        plongeesDirigees=new ArrayList();
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
        Optional<Club> dernierEmployeur;
        //Si le moniteur n'a pas d'embauche
        if (embauches.isEmpty()){
           dernierEmployeur=Optional.empty();
           return dernierEmployeur;}
        Embauche dernierEmbauche;
        dernierEmbauche=embauches.get( embauches.size()-1 );
        //Si son dernier emploi est terminée 
        if(dernierEmbauche.estTerminee()){
           dernierEmployeur=Optional.empty();
           return dernierEmployeur;}
        //Si son dernier emploi est en cours
        dernierEmployeur=Optional.ofNullable(dernierEmbauche.getEmployeur());

        return dernierEmployeur;
            }
        

        
    
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     * @throw Il faut que le dernière empbauche soit terminé
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) { 
        //Si il n'a pas d'emploi ou si son dernier emploi est terminé
         if (embauches.isEmpty() || embauches.get( embauches.size()-1 ).estTerminee() ){
             Embauche newEmbauche=new Embauche(debutNouvelle, this, employeur);
             embauches.add(newEmbauche);
             
             employeurs.add(employeur);}
         else{
            throw new UnsupportedOperationException("Le dernière embauche n'est pas terminé");}
    }

    public List<Embauche> emplois() {
            return embauches;
    }
    /*Termine l'embauche à la date d'aujourd'hui
    *@throws Il faut pour se faire avoir au moins 1 emploi
    */
    public void terminerEmbauche() {
        //On fixe la date de fin à aujourd'hui
        LocalDate today=LocalDate.now();
        if (embauches.isEmpty())
            throw new UnsupportedOperationException("Pas d'embauche enregistré");
        else{
            embauches.get(embauches.size()-1).terminer(today);
        }
      
    }
}

    
