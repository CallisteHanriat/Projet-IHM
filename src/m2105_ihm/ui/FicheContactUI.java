/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import m2105_ihm.nf.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author IUT2
 */
public class FicheContactUI extends JPanel {

    private CarnetUI         carnet;
    private JTextField       champNom;
    private JTextField champPrenom;
    private JTextField champEmail;
    private JFormattedTextField numeroTel;

    
    private MaskFormatter numero;
    
    private JCheckBox checkHobby;
    private JRadioButton     choixDispo;
    private ButtonGroup     groupeRadio;
    private ButtonGroup     groupeCheck;
    private HashMap<DispoSortie,JRadioButton> mapRadio;
    
    private String[] annees;
    private String[] jours;
    
    private HashMap<Hobby, JCheckBox> hobbyCheckTable;
    private JComboBox annee;
    private JComboBox moi;
    private JComboBox jour;
    
    private JComboBox regions;
    
    private JPanel fenPanel;
    private JPanel infoGeneralPanel;
    private JPanel centreInteretPanel;
    private JPanel boutonPanel;
    private GridLayout grilleLayoutPrincipal;
    private GridLayout grilleInfo;
    private JPanel dateNaissancePanel;
    private GridLayout grilleCentreInteret;
    private JPanel hobbyPanel;
    private GridLayout grilleHobby;
    private JPanel dispoPanel;
    private GridLayout grilleDispoLayout;
    
    private JButton boutonAccepter;
    private JButton boutonAnnuler;
    private GridBagLayout grilleBouton;
    private GridBagConstraints contrainteGrilleBouton;
    /**
     * Formulaire pour saisir les informations relatives à un contact
     */
    public FicheContactUI(CarnetUI carnet) {
        super();
            
        int i = 31;
        this.carnet      = carnet;
        jours = new String[i];
        annees = new String[100];        
        annee = new JComboBox();
        moi = new JComboBox(Mois.values());
        jour = new JComboBox();
        
        choixDispo = new JRadioButton();
        groupeRadio = new ButtonGroup();
        mapRadio = new HashMap();
        
        groupeRadio = new ButtonGroup();
        groupeCheck = new ButtonGroup();
        hobbyCheckTable = new HashMap<>();

        champPrenom  = new JTextField(30);
        champNom = new JTextField(30);
        champEmail = new JTextField(30);
        regions = new JComboBox();
       
        boutonAccepter = new JButton("Accepter");
        boutonAnnuler = new JButton("Annuler");
        
        initUIComponents();
        initListeners();
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {      
        /** Pour l'exemple **/
        //création d'une grille à trois lignes et une colone.
        
        //Declaration des élements Layout et Panel
        grilleInfo = new GridLayout();
        grilleLayoutPrincipal = new GridLayout(3, 1);
        grilleCentreInteret = new GridLayout();
        infoGeneralPanel = new JPanel();
        centreInteretPanel = new JPanel();
        boutonPanel = new JPanel();
        dateNaissancePanel = new JPanel();
        hobbyPanel = new JPanel();
        grilleHobby= new GridLayout();
        dispoPanel = new JPanel();
        grilleDispoLayout = new GridLayout();
        grilleBouton = new GridBagLayout();
        contrainteGrilleBouton  = new GridBagConstraints();
        
        //Mise en place des Layout pour les panels
        this.setLayout(grilleLayoutPrincipal);
        this.infoGeneralPanel.setLayout(grilleInfo);
        this.dateNaissancePanel.setLayout(new GridLayout());
        this.centreInteretPanel.setLayout(grilleCentreInteret);
        this.hobbyPanel.setLayout(grilleHobby);
        this.dispoPanel.setLayout(grilleDispoLayout);
        this.boutonPanel.setLayout(grilleBouton);
        
        //Propriétées sur les GridLayout
        this.infoGeneralPanel.setBorder(BorderFactory.createTitledBorder("informations générales"));
        this.centreInteretPanel.setBorder(BorderFactory.createTitledBorder("Centres d'intérêts"));
        this.boutonPanel.setBorder(BorderFactory.createTitledBorder("Enregistrer modifications"));
        this.grilleInfo.setRows(7);
        this.grilleInfo.setColumns(2);
        this.grilleCentreInteret.setColumns(2);
        this.grilleCentreInteret.setRows(2);
        this.grilleHobby.setRows(2);
        
        
        //Ajouts sur la fenêtre des panels.
        this.add(infoGeneralPanel);
        this.add(centreInteretPanel);
        this.add(boutonPanel);
        this.add(dateNaissancePanel);
        this.add(hobbyPanel);
        
        String[] sjour = new String[32];
        String[] sannee = new String[100];  
        
        try {
            //les # représentent des chiffres
            numero = new MaskFormatter("## ## ## ## ##");
            numeroTel = new JFormattedTextField(numero);
        } catch (ParseException ex) {
            Logger.getLogger(FicheContactUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         * Ajoute un label associé au champ pour la saisie du nom du contact
         */
        this.infoGeneralPanel.add(new JLabel("Nom :"));
         /*
         * Ajoute dans l'IHM un champ pour la saisie/Affichage du nom
         */
        this.infoGeneralPanel.add(champNom);
        this.infoGeneralPanel.add(new JLabel("Date de naissance : "));
        
        for (int i=1; i<=31; i++) {
            sjour[i] = Integer.toString(i);
            jour.addItem(sjour[i]);
        }
        
        this.dateNaissancePanel.add(jour);
        this.dateNaissancePanel.add(moi);
        
        for (int i=1; i<100; i++) {
            sannee[i] = Integer.toString(i+1900);
            annee.addItem(sannee[i]);
        }
        this.dateNaissancePanel.add(annee);
        
        this.infoGeneralPanel.add(this.dateNaissancePanel);
        
        
        this.infoGeneralPanel.add (new JLabel("Prénom : " )); 
        this.infoGeneralPanel.add(champPrenom);
        this.infoGeneralPanel.add (new JLabel("Email : " ));
        this.infoGeneralPanel.add(champEmail);
        this.infoGeneralPanel.add (new JLabel("Numero : " ));
        this.infoGeneralPanel.add(numeroTel);
        this.infoGeneralPanel.add (new JLabel("Region : " ));
        
        
        for (Region r : Region.values()) {
            regions.addItem(r);
        }
        
        this.infoGeneralPanel.add(regions);
        
        
        
        this.centreInteretPanel.add (new JLabel("Hobby préférés (plusieurs choix) : " ));        

        for (Hobby h : Hobby.values()) {
            checkHobby = new JCheckBox(h.toString());
            hobbyCheckTable.put(h, checkHobby);
            this.hobbyPanel.add(checkHobby);   
        }   
        this.centreInteretPanel.add(this.hobbyPanel);
        /** TP 1 : à compléter **/
        this.centreInteretPanel.add (new JLabel("Disponibilité préférée : " ));        
        
        
        for (DispoSortie h : DispoSortie.values()) {
            choixDispo = new JRadioButton(h.toString());
            this.dispoPanel.add(choixDispo);
            groupeRadio.add(choixDispo);
            mapRadio.put(h, choixDispo);
        }
        this.centreInteretPanel.add(this.dispoPanel);
        
        //ajout des boutons

        this.contrainteGrilleBouton.fill = GridBagConstraints.NONE;
        this.contrainteGrilleBouton.weightx = 1.;
        this.contrainteGrilleBouton.anchor = GridBagConstraints.CENTER;
        this.contrainteGrilleBouton.insets = new Insets(0, 15, 40, 0);
        this.boutonPanel.add(this.boutonAnnuler, contrainteGrilleBouton);
        this.contrainteGrilleBouton.anchor = GridBagConstraints.CENTER;
        this.contrainteGrilleBouton.insets = new Insets(0,0,40,15);
        this.contrainteGrilleBouton.fill = GridBagConstraints.NONE;
        this.contrainteGrilleBouton.weightx = 1.;
        this.boutonPanel.add(this.boutonAccepter, contrainteGrilleBouton);
        
        
}
    
    /**
     * Affecte des valeurs au formulaire de fiche contact
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean setValues(Contact contact) {
        if (contact == null) { return false; }
        
        /** TP 1 : à compléter **/
        champPrenom.setText(contact.getPrenom());
        champEmail.setText(contact.getEmail());
        moi.setSelectedItem(contact.getDateNaissanceMois());
        annee.setSelectedItem(Integer.toString(contact.getDateNaissanceAnnee()));
        jour.setSelectedIndex(contact.getDateNaissanceJour()-1);
        
        for(DispoSortie d : mapRadio.keySet()) {
            if(d.equals(contact.getDisponibilite())) {
                mapRadio.get(d).setSelected(true);
            }
        }       
        regions.setSelectedItem(contact.getRegion());
        numeroTel.setText(contact.getNumeroTelephone());
        /*
         * Nom du contact
         */
        champNom.setText(contact.getNom());        
        
        for (Hobby hob : hobbyCheckTable.keySet()) {
            hobbyCheckTable.get(hob).setSelected(false);
        }
        
        for (Hobby h : contact.getHobbies()) {
            for(Hobby hPrime : hobbyCheckTable.keySet()) {
                if (h.equals(hPrime)) {
                    hobbyCheckTable.get(hPrime).setSelected(true);
                }
            }
        }
        return true;
    }

    
    
    /**
     * Retourne les valeurs associées au formulaire de fiche contact
     * @param contact un contact à mettre à jour à partir de l'IHM
     * @return vrai si ok
     */
    public boolean getValues(Contact contact) {
        if (contact == null) { return false; }
        
        /** TP 1 : à compléter **/
        contact.setPrenom(champPrenom.getText());
        contact.setEmail(champPrenom.getText());
        contact.setNumeroTelephone(numeroTel.getText());
        contact.setDateNaissance(Integer.parseInt(jour.getSelectedItem().toString()), 
                (Mois) moi.getSelectedItem(),
                Integer.parseInt(annee.getSelectedItem().toString()));
        for (DispoSortie d : mapRadio.keySet()) {
            if (mapRadio.get(d).isSelected()) {
                contact.setDisponibilite(d);
            }
        }
        
        
        for (Hobby h : hobbyCheckTable.keySet()) {
            if (hobbyCheckTable.get(h).isSelected()) {
                contact.addHobby(h);
            }
            else {
                contact.removeHobby(h);
            }
        }
        
        contact.setRegion((Region) regions.getSelectedItem());
        /*
         * Affecte une valeur à l'attribut Nom avec le nom saisi dans le champ
         * correspondant de l'IHM
         */
        contact.setNom(champNom.getText());        

        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        /** TP 5 : à compléter **/ 
        
        this.boutonAccepter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(true);
            }
        });
        
        this.boutonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(false);
            }
        });
    }    
}