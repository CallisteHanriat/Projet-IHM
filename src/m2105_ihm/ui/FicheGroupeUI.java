/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.Symbole;

/**
 *
 * @author IUT2
 */
public class FicheGroupeUI extends javax.swing.JPanel {
    /*
     * Composants graphiques constituants l'interface
     */
    private CarnetUI carnet;
    private JTextField champNom;
    private JTable tableMembres;
    private ZoneDessinUI zoneDessin;
    private JButton boutonEfface, boutonAnnuler, boutonAccepter;
    private JList listeSymboles;
    private DefaultListModel modelList;
    private DefaultTableModel modelTable;
    
    private GridBagLayout grilleFenetre;
    private GridBagConstraints contrainteGrilleFenetreJpanel;
    private JPanel infosGroupeGeneralPanel;
    private JPanel infosGroupeListePanel;
    private JPanel infosGroupeBoutonsPanel;
    private GridBagLayout grilleInfoGeneral;
    private GridBagConstraints contrainteGrilleInfoGeneral;
    private GridBagLayout grilleListe;
    private GridBagConstraints contrainteGrilleListe;
    private String[] colonnes = {"Nom", "Prénom"};

    
    /**
     * Creates new form CarnetUI
     */
    public FicheGroupeUI(CarnetUI carnet) { 
        super();
        this.carnet = carnet;
        
        initUIComponents();    
        initListeners();
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {
        /** TP 2 : à compléter **/
        
        //initialisation des grilles et contraintes ainsi que Panels
        grilleFenetre = new GridBagLayout();
        contrainteGrilleFenetreJpanel = new GridBagConstraints();
        infosGroupeBoutonsPanel  = new JPanel();
        infosGroupeGeneralPanel = new JPanel();
        infosGroupeListePanel = new JPanel();
        contrainteGrilleInfoGeneral = new GridBagConstraints();
        grilleInfoGeneral = new GridBagLayout();
        grilleListe = new GridBagLayout();
        contrainteGrilleListe = new GridBagConstraints();
        
        //propriétées sur les grilles et les panels

        //ajout des grille et contraintes sur les panels ainsi que infos
        this.setLayout(grilleFenetre);
        
        contrainteGrilleFenetreJpanel.gridx = 0;
        contrainteGrilleFenetreJpanel.gridy = 0;
        contrainteGrilleFenetreJpanel.weighty = 3.;
        contrainteGrilleFenetreJpanel.weightx = 1.;
        contrainteGrilleFenetreJpanel.anchor = GridBagConstraints.LINE_START;
        contrainteGrilleFenetreJpanel.gridheight = 1;
        contrainteGrilleFenetreJpanel.fill = GridBagConstraints.BOTH;
        this.add(infosGroupeGeneralPanel, contrainteGrilleFenetreJpanel);
        
        contrainteGrilleFenetreJpanel.gridy = 1; 
        contrainteGrilleFenetreJpanel.weighty = 3.;
        this.add(infosGroupeListePanel, contrainteGrilleFenetreJpanel);
        
        contrainteGrilleFenetreJpanel.gridy = 2;
        contrainteGrilleFenetreJpanel.weighty = 1.;
        this.add(infosGroupeBoutonsPanel, contrainteGrilleFenetreJpanel);
        
        infosGroupeGeneralPanel.setLayout(grilleInfoGeneral);
        infosGroupeListePanel.setLayout(grilleListe);
        
        this.infosGroupeBoutonsPanel.setBorder(BorderFactory.createTitledBorder("Enregistrer modificaions"));
        this.infosGroupeGeneralPanel.setBorder(BorderFactory.createTitledBorder("Informations générales"));
        this.infosGroupeListePanel.setBorder(BorderFactory.createTitledBorder("Liste des membres"));
        
        //initialisation des autres objets
        champNom = new JTextField(15);
        zoneDessin = new ZoneDessinUI();
        boutonEfface = new JButton("effacer dessin");
        boutonAccepter = new JButton("Accepter");
        boutonAnnuler = new JButton("Annuler");
        modelList = new DefaultListModel();
        modelTable = new DefaultTableModel();
        
        tableMembres = new JTable(modelTable);
        
        
        //ajout des objets aux différents panels
        for (Symbole s : Symbole.values()) {
            modelList.addElement(s);
        }
        listeSymboles = new JList(modelList);
        
        contrainteGrilleInfoGeneral.gridx = 0;
        contrainteGrilleInfoGeneral.gridy = 0;

        this.infosGroupeGeneralPanel.add(new javax.swing.JLabel("nom groupe : "), contrainteGrilleInfoGeneral);
        
        contrainteGrilleInfoGeneral.fill = GridBagConstraints.HORIZONTAL;
        contrainteGrilleInfoGeneral.gridx = 1;
        this.infosGroupeGeneralPanel.add(champNom, contrainteGrilleInfoGeneral);
        
        contrainteGrilleInfoGeneral.gridx = 2;
        this.infosGroupeGeneralPanel.add(zoneDessin, contrainteGrilleInfoGeneral);
        
        contrainteGrilleInfoGeneral.gridx = 2;
        contrainteGrilleInfoGeneral.gridy = 2;
        this.infosGroupeGeneralPanel.add(boutonEfface, contrainteGrilleInfoGeneral);
        
        contrainteGrilleInfoGeneral.gridx = 1;
        contrainteGrilleInfoGeneral.gridy = 2;
        this.infosGroupeGeneralPanel.add(listeSymboles, contrainteGrilleInfoGeneral); 
        
        infosGroupeListePanel.setSize(300, 150);
        this.infosGroupeBoutonsPanel.add(boutonAccepter);
        this.infosGroupeBoutonsPanel.add(boutonAnnuler);        
    }

    /**
     * Affecte des valeurs au formulaire de fiche groupe de contacts
     * @param groupe groupe de contacts
     * @return
    */    
    public boolean setValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        int i =  0;
        
        champNom.setText(groupe.toString());
        
        int tab[] = new int[groupe.getSymboles().length];
        for (Symbole s : groupe.getSymboles()) {
                tab[i] = s.ordinal();
                i++;
        }
        
        listeSymboles.setSelectedIndices(tab);
        
        for ( i=0 ; i<modelTable.getRowCount(); i++) {
            modelTable.removeRow(i);
        }
        modelTable.setNumRows(0);
        i = 0;
        
        for (Contact c: groupe.getContacts()) {
            i++;
            String tab2[] = new String[2];   
            tab2[0] = c.getNom();
            tab2[1] = c.getPrenom();
            modelTable.addRow(tab2);
        }
        tableMembres.removeAll();
        modelTable.setColumnIdentifiers(colonnes);
        tableMembres.setModel(modelTable);
        
        contrainteGrilleListe.gridx = 0;
        contrainteGrilleListe.gridy = 0;
        this.infosGroupeListePanel.add(tableMembres.getTableHeader(), contrainteGrilleListe);
        
        contrainteGrilleListe.gridx = 0;
        contrainteGrilleListe.gridy = 1;      
        this.infosGroupeListePanel.add(tableMembres, contrainteGrilleListe);
        
        this.zoneDessin.setPoints(groupe.getPoints());
        return true;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche groupe de contacts
     * @return
     */    
    public boolean getValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }

        for (Symbole s : groupe.getSymboles()) {
            groupe.removeSymbole(s);
        }
        for (Object o : listeSymboles.getSelectedValuesList()) {
            groupe.addSymbole((Symbole) o);
        }
        groupe.setNom(champNom.getText());
//        tableMembres.getSelectedRow();
        /*
         * Ne pas s'occuper des membres du groupe car traité via des
         * commandes du menu qui appelera setValues
         */
        groupe.setPoints(this.zoneDessin.getPoints());
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    public void initListeners() {        
        /*
         * Réagit aux évènements produits par le bouton effacer
         */

        boutonEfface.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    zoneDessin.effacer();
            }
        });
        
        
        //TP5 : à compléter
        this.boutonAccepter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(true);
            }
        });
        
        this.boutonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(false);
            }
        });
    }    
}
