package FFSSM;
import java.util.*;
import java.time.LocalDate;

public class Plongeur extends Personne {
    
    private int niveau;
    private GroupeSanguin groupe;
    private ArrayList<Licence> Licences;

    public Plongeur(GroupeSanguin groupe,int niveau,String numeroINSEE, String nom,
                     String prenom, String adresse, String telephone, LocalDate naissance){
        super(numeroINSEE,  nom,  prenom,  adresse,  telephone, naissance);
        
        this.niveau=niveau;
        this.groupe=groupe;
        Licences=new ArrayList();
        }
   
    public int getNiveau() {
        return niveau;}


    public GroupeSanguin getGroupe() {
        return groupe;}

    
    public ArrayList<Licence> getLicences() {
        return Licences;}

    public void setNiveau(int newNiveau){
        niveau=newNiveau;}

    public void setGroupe(GroupeSanguin newGroupe){
        groupe=newGroupe;}

    public void ajouteLicence(String numero,LocalDate delivrance,Club club){
       
        Licence newLicence=new Licence(this,numero,delivrance,this.niveau,club);
        if(!Licences.contains(newLicence)){
        Licences.add(newLicence);}
    }
 

    
 

	
}
