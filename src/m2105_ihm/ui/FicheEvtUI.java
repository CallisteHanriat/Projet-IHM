/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Mois;

public class FicheEvtUI extends javax.swing.JPanel {

    private PlanningUI planning;
    private JTextField intitule;
    private JButton enregistrer;
    private JButton annuler;
    private JButton ajouterParticipants;
    private JButton retirerParticipants;
    private JList listeParticipants;
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
        
        if (enregistrer != null){
            enregistrer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Enregistrer");
                    planning.getSelectedEvt().setIntitule(intitule.getText());
                }
            });
        }
        
        if (ajouterParticipants != null){
            ajouterParticipants.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ajouter participant");
                    planning.getControleur().ajouterParticipantEvenement();
                }
            });
        }
        
        if (retirerParticipants != null){
            retirerParticipants.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Retirer participant");
                    planning.getControleur().retirerParticipantEvenement();
                }
            });
        }
        
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */    
    private void initUIComponents() {
        
        this.setBorder(BorderFactory.createTitledBorder(planning.getSelectedDate()));
        
        if (planning.getSelectedEvt() != null){
            //mise en place du layout
            this.setLayout(new GridBagLayout());
            
            //élements
            contrainteLayout = new GridBagConstraints();
            
            //ajout des composants
            contrainteLayout.gridx = 0;
            contrainteLayout.gridy = 0;
            contrainteLayout.weightx = 1;
            contrainteLayout.anchor = GridBagConstraints.CENTER;
            contrainteLayout.weightx = 1.;
            this.add(new JLabel("Intitulé : "), contrainteLayout);

            contrainteLayout.gridx = 1;
            contrainteLayout.gridy = 0;
            intitule = new JTextField(15);
            this.add(intitule, contrainteLayout);
            
            contrainteLayout.gridx = 1;
            contrainteLayout.gridy = 3;
            listeParticipants = new JList();
            this.add(listeParticipants,contrainteLayout);
            
            ajouterParticipants = new JButton("Ajouter participants");
            this.add(ajouterParticipants);
            
            retirerParticipants = new JButton("Retirer participants");
            this.add(retirerParticipants);

            contrainteLayout.gridx = 1;
            contrainteLayout.gridy = 1;
            enregistrer = new JButton("Enregistrer");
            contrainteLayout.insets = new Insets(5, 0, 0, 5);
            this.add(enregistrer, contrainteLayout);

            contrainteLayout.gridx = 1;
            contrainteLayout.gridy = 2;
            annuler = new JButton("Annuler");
            contrainteLayout.ipadx = 25; 
            this.add(annuler, contrainteLayout);
        } else{
            this.add(new JLabel("Il n'y a pas d'évènements pour ce jour !"));
        }

    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }
        
        intitule.setText(event.getIntitule());
        listeParticipants.setListData(planning.getSelectedEvt().getParticipants());
            
        return false;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     * @param Evenement événement à affecter
     * @return 
     */    
    public boolean getValues(Evenement event) {
        
        if (event == null) { return false; }
       
        event.setIntitule(intitule.getText());
        
        return true;
    }
}
