/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Mois;

/**
 *
 * @author IUT2
 */
public class FicheEvtUI extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    private PlanningUI planning;
    private JTextField intitule;
    private JButton enregistrer;
    private JButton annuler;
    private GridBagConstraints contrainteLayout;
    
    
    /**
     * Creates new form CarnetUI
     */
    public FicheEvtUI(PlanningUI planning) {
        super();
        
        this.planning = planning;
        
        initUIComponents();
        initListeners();
    }

    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        
        /** Projet : à compléter **/
        
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */    
    private void initUIComponents() {
        
        
        //mise en place du layout
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(planning.getSelectedDate()));
        //élements
        intitule = new JTextField(15);
        enregistrer = new JButton("Enregistrer");
        annuler = new JButton("Annuler");
        contrainteLayout = new GridBagConstraints();
        
        //ajout des composants
        contrainteLayout.gridx = 0;
        contrainteLayout.gridy = 0;
        contrainteLayout.weightx = 1.;
        contrainteLayout.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Intitulé : "), contrainteLayout);
        
        contrainteLayout.gridx = 1;
        contrainteLayout.gridy = 0;
        this.add(intitule, contrainteLayout);
        
        contrainteLayout.gridx = 2;
        contrainteLayout.gridy = 0;
        this.add(new JLabel(planning.getSelectedDate()), contrainteLayout);
        
        contrainteLayout.gridx = 1;
        contrainteLayout.gridy = 1;
        this.add(enregistrer, contrainteLayout);

        contrainteLayout.gridx = 2;
        contrainteLayout.gridy = 1;
        this.add(annuler, contrainteLayout);
               
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }            
                  
        /** Projet : à compléter **/
        
        intitule.setText(event.getIntitule());
            
        return false;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     * @param Evenement événement à affecter
     * @return 
     */    
    public boolean getValues(Evenement event) {
        
        if (event == null) { return false; }
        
        /** Projet : à compléter **/
       
        event.setIntitule(intitule.getText());
        
        return true;
    }
}
