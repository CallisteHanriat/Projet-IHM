/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Mois;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends JPanel {
    /**
     * Creates new form CarnetUI
     */
    private Controleur controleur;
    private FicheEvtUI ficheEvt;
    private JPanel nord;
    private JPanel centre;
    private JComboBox mois;
    private JComboBox annee;
    private ArrayList<JLabel> jours;

    /** 
     * Constructeur : initialise les composants de l'IHM pour les événements
     * @param une instance du controleur
     */
    public PlanningUI(Controleur ctrl) {
        super();
        
        this.controleur = ctrl;
        
        initComponents();        
    }

    /**
     * Crée et place les composants graphiques de l'interface
     */
    private void initComponents() {
        /*
         * Fiche événement
         */        
        ficheEvt = new FicheEvtUI(this); 
        nord = new JPanel();
        centre = new JPanel();
        
        nord.setLayout(new GridLayout(1, 2));
        
        mois = new JComboBox(Mois.values());
        
        mois.setSelectedItem(Mois.JUIN);
        
        mois.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setCalendar();
                centre.updateUI();
            }
        });
        
        annee = new JComboBox();
        
        for (int i = 1900; i <= 2015; i++){
            annee.addItem(i);
        }
        
        annee.setSelectedIndex(2015 - 1900);
        
        annee.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setCalendar();
                centre.updateUI();
            }
        });
        
        nord.add(mois);
        nord.add(annee);
        
        setCalendar();
        
        this.setLayout(new BorderLayout());
        this.add(nord, BorderLayout.NORTH);
        this.add(centre, BorderLayout.CENTER);        
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
            
        return true;
    }
    
    /**
     * Retire une entrée dans l'arbre pour les contacts
     * @param Contact contact à retirer
     */    
    public boolean retirerEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
            
        return false;
    }
    
    /*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt() {    
        
        /** Projet à completer **/

        return null;
    }
    
    public static int getDayOfDate(int m, int d, int y){
        int z = y - 1;
        int D;        
        if (m >= 3){
            D = (((23*m)/9) + d + 4 + y + (y/4) - (y/100) + (y/400) - 2) % 7;
        } else{
            D = (((23*m)/9) + d + 4 + y + (z/4) - (z/100) + (z/400)) % 7;
        }
        return D;
    }
    
    public void setCalendar(){
        centre.removeAll();
        
        centre.add(new JLabel("Lundi"));
        centre.add(new JLabel("Mardi"));
        centre.add(new JLabel("Mercredi"));
        centre.add(new JLabel("Jeudi"));
        centre.add(new JLabel("Vendredi"));
        centre.add(new JLabel("Samedi"));
        centre.add(new JLabel("Dimanche")); 
        
        int m = mois.getSelectedIndex() + 1;
        int d = 1;
        int y = annee.getSelectedIndex() + 1900;
        
        int D = getDayOfDate(m, d, y);
        
        System.out.println("Mois : " + m + "\nAnnée : " + y + "\nJour : " + D);
        
        int nb = 0;
        jours = new ArrayList<>();
        
        for (int i = 1; i < D; i++){
            jours.add(new JLabel(Integer.toString(i+32-D)));
            centre.add(jours.get(nb));
            nb++;
        }
        
        for (int i = 1; i <= 31; i++){
            jours.add(new JLabel(Integer.toString(i)));
            centre.add(jours.get(nb));
            nb++;
        }
        
        if (nb > 35){
            int i = 1;
            while(nb < 42){
                jours.add(new JLabel(Integer.toString(i)));
                centre.add(jours.get(nb));
                i++;
                nb++;
            }
            centre.setLayout(new GridLayout(7, 7));
        } else {
            int i = 1;
            while(nb < 35){
                jours.add(new JLabel(Integer.toString(i)));
                centre.add(jours.get(nb));
                i++;
                nb++;
            }
            centre.setLayout(new GridLayout(6, 7));   
        }
    }
}
