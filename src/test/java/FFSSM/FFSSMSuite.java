package FFSSM;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class FFSSMSuite{
    //On crée 2 Moniteurs
    private Moniteur m1=new Moniteur("2501", "Capitaine", "Crabs", "32 Rue du ZOO", "090501245", LocalDate.of(1940,1,1), 1);
    private Moniteur m2=new Moniteur("2411","Madame", "Peuf", "2 bikini Bottom", "064804175", LocalDate.of(1950,2,2), 2);
    //On crée 1 clubs
    private Club c1=new Club(m1, "Horus", "090804152");
    //3 plongeurs 
    private Plongeur p1=new Plongeur(GroupeSanguin.APLUS,4,"42", "L'éponge", "Bob", "4 bis avenue Georges Alquier", "0648263002", LocalDate.of(1950,9,10));
    private Plongeur p2=new Plongeur(GroupeSanguin.OPLUS,4,"52", "L'étoile de Mer", "Patrick", "4 bis avenue Georges Alquier", "0648263002", LocalDate.of(1980,7,15));
    private Plongeur p3=new Plongeur(GroupeSanguin.BPLUS,4,"72", "Calamar", "Carlo", "4 bis avenue Georges Alquier", "0648263002", LocalDate.of(2000,6,20));
    //1 plongeurs 
    private Plongeur p4=new Plongeur(GroupeSanguin.APLUS,2,"82", "DuBourgPalette", "Sacha", "4 bis avenue Georges Alquier", "06867002", LocalDate.of(1984,6,20));
    //3 plongee donc 3 sites
    private Site s1= new Site("Nul part", "Error 404 Not Found");
    private Site s2=new Site("En la casa de tu mama","RAD");
    private Site s3=new Site("Unys","kouniamanmanw");
    private Plongee P1=new Plongee(c1,s1, m1, LocalDate.of(2020,12,20), 380,20);
    private Plongee P2=new Plongee(c1,s2, m1, LocalDate.of(2021,12,20), 580,12);
    private Plongee P3=new Plongee(c1,s3, m2, LocalDate.of(2022,12,20), 880,10); 


          
    
   
    
        @Test
        public void ajoutCorrectLicence(){
        //On vérifie que la liste des licences est bien vide
        if(!p1.getLicences().isEmpty()){
           throw new IllegalArgumentException("La liste doit être vide"); }
        //On crée une licence avec la fonction ajouteLicence
        p1.ajouteLicence("1564",LocalDate.of(2050,1,1),c1);
        ArrayList<Licence> Liste=p1.getLicences();
        //On vérifie que la licence ajoutée est bien correct
        assertEquals("1564", Liste.get(0).getNumero(),"La dernière licence ajoutée est incorrect");
        assertEquals(LocalDate.of(2050,1,1), Liste.get(0).getDelivrance(),"La dernière licence ajoutée est incorrect");
        assertEquals(c1, Liste.get(0).getClub(),"La dernière licence ajoutée est incorrect");
        assertEquals(p1, Liste.get(0).getPossesseur(),"La dernière licence ajoutée est incorrect");
        }

        @Test
        public void testValiditeLicence(){
            //On crée une licence invalide à ce jour
            Licence L=new Licence(p1, "1542", LocalDate.of(1995,2,2), 5, c1);
            assertFalse(L.estValide(L.getDelivrance()),"La licence est exprirée et le résultat est True");
            //On crée une licence valide à ce jour
            Licence L1=new Licence(p2, "2222", LocalDate.of(2050,4,4), 2, c1);
            assertTrue(L.estValide(L1.getDelivrance()),"La licence n'est pas expirée et le résultat est False");
            
            }

        @Test
        public void testAjouteParticipant(){
            //On vérifie que la des participants est bien vide
             if(!P1.getPalanquee().isEmpty()){
           throw new IllegalArgumentException("La liste doit être vide");}
           //On ajoute un participant
            p1.ajouteLicence("1542", LocalDate.of(2080,2,2),c1);
            p2.ajouteLicence("1842", LocalDate.of(2090,2,2),c1);
            P1.ajouteParticipant(p2);
            P1.ajouteParticipant(p1);
            assertEquals(2,P1.getPalanquee().size(),"Le nombre de participant est incorrect");
            }

        @Test
        public void testPlongeeConforme(){
        //Une plongee est conforme si tout les participants on une licence valide 
        
        //On crée 3 licences valides
        p1.ajouteLicence("1542", LocalDate.of(2080,2,2),c1);
        p2.ajouteLicence("1842", LocalDate.of(2090,2,2),c1);
        p3.ajouteLicence("2542", LocalDate.of(2070,2,2),c1);
        //On ajoute chacun des participants
        P1.ajouteParticipant(p1);
        P1.ajouteParticipant(p2);
        P1.ajouteParticipant(p3);
        //On vérifie la conformité de la plongée
        assertTrue(P1.estConforme(),"Cette plongee est valide");
        //On crée une licence invalide
        p4.ajouteLicence("2222", LocalDate.of(1900,4,4), c1);
        P1.ajouteParticipant(p4);
        
        assertFalse(P1.estConforme(),"Cette plongee est invalide, il y a un plongeur avec une licence invalide");
        }
        @Test
        public void TestOrganisePlonge(){
            //On a créé 3 plongee dont l'organiqateur est le c1
            assertEquals(3,c1.getActivites().size(),"Le nombre de plongee organisée par le club est incorrect");
        }
        
        @Test
        public void TestPlongeesNonconforme(){
            p1.ajouteLicence("1542", LocalDate.of(2080,2,2),c1);
            p2.ajouteLicence("1842", LocalDate.of(2090,2,2),c1);
            p3.ajouteLicence("2542", LocalDate.of(1945,2,2),c1);
            P1.ajouteParticipant(p1);
            P2.ajouteParticipant(p2);
            P3.ajouteParticipant(p3);
            assertEquals(1,c1.plongeesNonConformes().size(),"Le nombre de plongée non conforme est incorrect");
            
        }

        @Test
        public void testNouvelleEmbauche(){
            m1.nouvelleEmbauche(c1, LocalDate.of(2020,12,24));
            assertEquals(1,m1.emplois().size(),"L'embauche n'est pas enregistré");}

        @Test
        public void pasDeuxEmploisCumule(){
            //On cree un nouvelle embauche avec une date de fin
            m1.nouvelleEmbauche(c1, LocalDate.of(2020,12,24));
            m1.emplois().get(0).terminer(LocalDate.of(2050,12,25));
            try {
			m1.nouvelleEmbauche(c1,LocalDate.of(2020,13,24));
			// Si on arrive ici, il n'y a pas eu d'exception, échec
			fail();
		} catch (Exception e) {
			// Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
		}
        }

        @Test
        public void TestEmbaucheTermineeFalse(){
            m1.nouvelleEmbauche(c1, LocalDate.of(2020,12,24));
            Embauche pizza=m1.emplois().get(0);
            pizza.terminer(LocalDate.of(2050,12,25));
            assertFalse(pizza.estTerminee(),"L'emploi n'a pas encore atteint sa date de fin");
            
            }
        
        @Test
          public void TestEmbaucheTermineeTrue(){
            m1.nouvelleEmbauche(c1, LocalDate.of(2020,4,24));
            Embauche pizza=m1.emplois().get(0);
            pizza.terminer(LocalDate.of(2020,10,25));
            assertTrue(pizza.estTerminee(),"L'emploi a atteint sa date de fin");
            
            }
         
        @Test
        public void TestTerminerEmbauche(){
            LocalDate today=LocalDate.now();
            m1.nouvelleEmbauche(c1, LocalDate.of(2000,4,24));
            m1.terminerEmbauche();
            assertEquals(today,m1.emplois().get(0).getFin(),"La date de fin d'embauche est incorrecte");
        }

        
        
      
}