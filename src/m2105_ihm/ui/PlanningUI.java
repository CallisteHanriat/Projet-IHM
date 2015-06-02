/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
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

    private Controleur controleur;
    private JPanel nord;
    private JPanel centre;
    private JPanel ficheEvt;
    private JComboBox mois;
    private JComboBox annee;
    private ArrayList<JButton> jours;
    private int selectedDay = Calendar.getInstance().getTime().getDay();
    private int selectedButton;

    /**
     * Constructeur : initialise les composants de l'IHM pour les événements
     *
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
        nord = new JPanel();
        centre = new JPanel();
        nord.setLayout(new GridLayout(1, 2));

        mois = new JComboBox(Mois.values());
        Mois currentMonth = null;
        int nb = 0;
        for (Mois m : Mois.values()) {
            if (nb == Calendar.getInstance().get(Calendar.MONTH)) {
                currentMonth = m;
                break;
            }
            nb++;
        }

        mois.setSelectedItem(currentMonth);
        mois.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setCalendar();
                centre.updateUI();
            }
        });

        annee = new JComboBox();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= currentYear; i++) {
            annee.addItem(i);
        }

        annee.setSelectedIndex(currentYear - 1900);
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

        ficheEvt = new FicheEvtUI(this);

        this.setLayout(new BorderLayout());
        this.add(nord, BorderLayout.NORTH);
        this.add(centre, BorderLayout.CENTER);
        this.add(ficheEvt, BorderLayout.EAST);
    }

    /**
     * Ajoute une entrée dans la liste de événements
     *
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt) {
        if (evt == null) {
            return false;
        }
        
        return true;
    }

    /**
     * Retire une entrée dans l'arbre pour les contacts
     *
     * @param Contact contact à retirer
     */
    public boolean retirerEvt(Evenement evt) {
        if (evt == null) {
            return false;
        }

        /**
         * Projet à completer *
         */
        return false;
    }

    /**
     * Renvoie l'évènement sélectionné
     * 
     * @return l'évènement sélectionné l
     */
    public Evenement getSelectedEvt() {

        /**
         * Projet à completer *
         */
        return null;
    }

    /**
     * Renvoie le jour de la semaine correspondant à une date
     * 
     * @param m le mois
     * @param d le numéro du jour
     * @param y l'année
     * @return le jour de la semaine
     */
    public static int getDayOfDate(int m, int d, int y) {
        int z = y - 1;
        int D;
        if (m >= 3) {
            D = (((23 * m) / 9) + d + 4 + y + (y / 4) - (y / 100) + (y / 400) - 2) % 7;
        } else {
            D = (((23 * m) / 9) + d + 4 + y + (z / 4) - (z / 100) + (z / 400)) % 7;
        }
        return D;
    }

    /**
     * Renvoie le nombre de jour dans un mois
     * 
     * @param month le mois
     * @param year l'année
     * @return le nombre de jour dans le mois
     */
    public static int getNumberOfDayInMonth(int month, int year) {
        Boolean leapYear = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
        int daysInMonth;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            if (month == 2) {
                daysInMonth = (leapYear) ? 29 : 28;
            } else {
                daysInMonth = 31;
            }
        }
        return daysInMonth;
    }

    /**
     * Renvoie la date sélectionnée
     * 
     * @return la date sélectionnée
     */
    public String getSelectedDate() {
        return selectedDay + "/" + (mois.getSelectedIndex() + 1) + "/" + (annee.getSelectedIndex() + 1900);
    }

    /**
     * Mets en place le calendrier affiché sur l'interface
     */
    public void setCalendar() {
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

        int nombreJours = getNumberOfDayInMonth(m - 1, y);

        for (int i = 1; i < D; i++) {
            jours.add(new JButton(Integer.toString(i + 1 + nombreJours - D)));
            jours.get(nb).setEnabled(false);
            centre.add(jours.get(nb));
            nb++;
        }

        nombreJours = getNumberOfDayInMonth(m, y);

        int currentDay = Calendar.getInstance().getTime().getDay();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = 1; i <= nombreJours; i++) {
            jours.add(new JButton(Integer.toString(i)));
            final int v = i;
            final int w = nb;
            jours.get(nb).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedDay = v;
                    selectedButton = w;
                    System.out.println(getSelectedDate());
                    PlanningUI.this.remove(ficheEvt);
                    ficheEvt = new FicheEvtUI(PlanningUI.this);
                    PlanningUI.this.add(ficheEvt, BorderLayout.EAST);
                    ficheEvt.updateUI();
                }
            });
            if (i == currentDay && m == currentMonth && y == currentYear) {
                jours.get(nb).setBackground(Color.ORANGE);
            }
            centre.add(jours.get(nb));
            nb++;
        }

        if (nb > 35) {
            int i = 1;
            while (nb < 42) {
                jours.add(new JButton(Integer.toString(i)));
                jours.get(nb).setEnabled(false);
                centre.add(jours.get(nb));
                i++;
                nb++;
            }
            centre.setLayout(new GridLayout(7, 7));
        } else {
            if (nb > 28) {
                int i = 1;
                while (nb < 35) {
                    jours.add(new JButton(Integer.toString(i)));
                    jours.get(nb).setEnabled(false);
                    centre.add(jours.get(nb));
                    i++;
                    nb++;
                }
                centre.setLayout(new GridLayout(6, 7));
            } else {
                centre.setLayout(new GridLayout(5, 7));
            }
        }
    }
}
