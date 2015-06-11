/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm;

import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.NoyauFonctionnel;
import m2105_ihm.nf.Evenement;

import m2105_ihm.ui.CarnetUI;
import m2105_ihm.ui.FenetreUI;
import m2105_ihm.ui.PlanningUI;
import m2105_ihm.ui.BoiteDialogUI;

public class Controleur {
    
    NoyauFonctionnel nf;
            
    private CarnetUI carnetUI;
    private FenetreUI fenetre;
    private PlanningUI planningUI;

    /**
     * Constructeur de la fenêtre principale
     */
    public Controleur() {
        initUI();
        initContent();
    }
    
    /**
     * Action créer un nouveau contact
     */
    public void creerContact() {
        Contact contact = new Contact();
        nf.addContact(contact);
        carnetUI.ajouterContact(contact);        
    }

    /**
     * Action supprimer contact
     */
    public void supprimerContact() {
        Contact c = (carnetUI.getSelectedContact());
        if (BoiteDialogUI.afficherConfirmation(fenetre, c)) {
            nf.removeContact(c);
            carnetUI.retirerContact(c);
        }
    }
    
    /**
     * Action créer un groupe de contacts
     */
    public void creerGroupe() {
        GroupeContacts groupe = new GroupeContacts();
        nf.addGroupe(groupe);
        carnetUI.ajouterGroupe(groupe);    
    }

    /**
     * Action supprimer un groupe de contacts
     */
    public void supprimerGroupe() {
        nf.removeGroupe(carnetUI.getSelectedGroupe());
    }
    
    /**
     * Ajoute un contact à un groupe donné à l'aide d'une pop-up
     */
    public void ajouterContactGroupe() {
        Contact contactAAjouter = carnetUI.getSelectedContact();
        GroupeContacts groupe = BoiteDialogUI.afficherChoixMembreContact(fenetre, "Choisir groupe dans lequel le mec va aller", nf.getGroupes());
        if (groupe != null){
            groupe.addContact(contactAAjouter);
        }
    }
    
    /**
     * Supprime un contact d'un groupe à l'aide d'une pop-up
     */
    public void supprimerContactGroupe() {
        Contact contactASupprimer = carnetUI.getSelectedContact();
        GroupeContacts groupe = BoiteDialogUI.afficherChoixMembreContact(fenetre, "Choisir groupe dans lequel le mec n'est plus accepté", nf.getGroupesContact(contactASupprimer));
        if (groupe != null){
            groupe.removeContact(contactASupprimer);
        }    }
    
    /**
     * Crée un nouvel événement
     */
    public void creerEvenement() {
        Evenement evenement = new Evenement();
        nf.addEvenement(evenement);
        planningUI.ajouterEvt(evenement);       
    }

    /**
     * Supprime un événement existant
     */
    public void supprimerEvenement() {
        nf.removeEvenement(planningUI.getSelectedEvt());
        planningUI.retirerEvt(planningUI.getSelectedEvt());
    }
    
    /**
     * Ajouter un participant à un événement
     */
    public void ajouterParticipantEvenement() {
        Evenement event = planningUI.getSelectedEvt();
        Contact contactAAjouter = BoiteDialogUI.afficherChoixContactEvent(fenetre, nf.getContactsNotInEvenements(event));
        if (contactAAjouter != null){
            event.addParticipant(contactAAjouter);
        }        
    }

    /**
     * Retire un participant d'un événement
     */
    public void retirerParticipantEvenement() {
        Evenement event = planningUI.getSelectedEvt();
        Contact contactASupprimer = BoiteDialogUI.afficherChoixContactEvent(fenetre, nf.getContactsEvenements(event));
        if (contactASupprimer != null){
            event.removeParticipant(contactASupprimer);
        }
    }

    /**
     * Met à jour la base de données
     */
    public void enregistrer() {
        nf.updateDB();
    }    
        
    /**
     * Quitter l'application sans enregistrer les modifications
     */
    public void quitter() {
        if (BoiteDialogUI.quitterSansEnregistrer(fenetre)){
            nf.updateDB();
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    /**
     * Création des composants constituant la fenêtre principale
     */
    private void initUI() {
        /* Onglets */
        carnetUI = new CarnetUI(this);
        planningUI = new PlanningUI(this);

        /* Fenêtre principale */
        fenetre = new FenetreUI(this);
        fenetre.addTab(carnetUI, "Carnet");     // onglet carnet
        fenetre.addTab(planningUI, "Planning"); // onglet planning
        fenetre.afficher();
    }
        
    /**
     * Alimente la liste avec la liste des contacts existants
     */
    private void initContent() {
        nf = new NoyauFonctionnel();
                
        for(Contact c : nf.getContacts()) {
            carnetUI.ajouterContact(c);
        }
        
        for(GroupeContacts g : nf.getGroupes()) {
            carnetUI.ajouterGroupe(g);
        }
        
        for(Evenement e : nf.getEvenements()) {
            planningUI.ajouterEvt(e);
        }
        
        carnetUI.show();
    }
    
    public void setContactSelected(boolean selected) {
        fenetre.setMenuContactSelected(selected);
    }
    
    public void setEvtSelected(boolean selected) {
        fenetre.setMenuEvenementSelected(selected);
    }    
}