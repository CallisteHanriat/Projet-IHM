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

public class FicheContactUI extends JPanel {

    private CarnetUI carnet;
    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champEmail;
    private JFormattedTextField numeroTel;
    private MaskFormatter numero;
    private JCheckBox checkHobby;
    private JRadioButton choixDispo;
    private ButtonGroup groupeRadio;
    private ButtonGroup groupeCheck;
    private HashMap<DispoSortie, JRadioButton> mapRadio;
    private String[] annees;
    private String[] jours;
    private HashMap<Hobby, JCheckBox> hobbyCheckTable;
    private JComboBox annee;
    private JComboBox moi;
    private JComboBox jour;
    private JComboBox regions;
    private JButton boutonAccepter;
    private JButton boutonAnnuler;
    private JPanel infoGeneralPanel;
    private JPanel centreInteretPanel;
    private JPanel boutonPanel;
    private JPanel dateNaissancePanel;
    private JPanel hobbyPanel;
    private JPanel dispoPanel;
    private JPanel champsGauchePanel;
    private GridLayout grilleLayoutPrincipal;
    private GridLayout grilleInfo;
    private GridLayout grilleCentreInteret;
    private GridLayout grilleHobby;
    private GridLayout grilleDispoLayout;
    private GridBagLayout grilleBouton;
    private GridBagConstraints contrainteGrilleBouton;

    /**
     * Formulaire pour saisir les informations relatives à un contact
     */
    public FicheContactUI(CarnetUI carnet) {
        super();

        setCarnet(carnet);
        setJour(new JComboBox());
        setJours(new String[31]);
        setAnnee(new JComboBox());
        setAnnees(new String[100]);
        setMoi(new JComboBox(Mois.values()));
        setChoixDispo(new JRadioButton());
        setGroupeRadio(new ButtonGroup());
        setMapRadio(new HashMap<DispoSortie, JRadioButton>());
        setGroupeCheck(new ButtonGroup());
        setHobbyCheckTable(new HashMap<Hobby, JCheckBox>());
        setChampPrenom(new JTextField(30));
        setChampNom(new JTextField(30));
        setChampEmail(new JTextField(30));
        setRegions(new JComboBox());
        setBoutonAccepter(new JButton("Accepter"));
        setBoutonAnnuler(new JButton("Annuler"));

        initUIComponents();
        initListeners();
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {
        /**
         * Pour l'exemple *
         */
        //création d'une grille à trois lignes et une colone.

        //Declaration des élements Layout et Panel
        setGrilleInfo(new GridLayout());
        setGrilleLayoutPrincipal(new GridLayout(3, 1));
        setGrilleCentreInteret(new GridLayout());
        setInfoGeneralPanel(new JPanel());
        setCentreInteretPanel(new JPanel());
        setBoutonPanel(new JPanel());
        setDateNaissancePanel(new JPanel());
        setHobbyPanel(new JPanel());
        setGrilleHobby(new GridLayout());
        setDispoPanel(new JPanel());
        setGrilleDispoLayout(new GridLayout());
        setGrilleBouton(new GridBagLayout());
        setContrainteGrilleBouton(new GridBagConstraints());
        setChampsGauchePanel(new JPanel());

        //Mise en place des Layout pour les panels
        this.setLayout(getGrilleLayoutPrincipal());
        this.getInfoGeneralPanel().setLayout(getGrilleInfo());
        this.getDateNaissancePanel().setLayout(new GridLayout());
        this.getCentreInteretPanel().setLayout(getGrilleCentreInteret());
        this.getHobbyPanel().setLayout(getGrilleHobby());
        this.getDispoPanel().setLayout(getGrilleDispoLayout());
        this.getBoutonPanel().setLayout(getGrilleBouton());

        //Propriétées sur les GridLayout
        this.getInfoGeneralPanel().setBorder(BorderFactory.createTitledBorder("informations générales"));
        this.getCentreInteretPanel().setBorder(BorderFactory.createTitledBorder("Centres d'intérêts"));
        this.getBoutonPanel().setBorder(BorderFactory.createTitledBorder("Enregistrer modifications"));
        this.getGrilleInfo().setRows(7);
        this.getGrilleInfo().setColumns(2);
        this.getGrilleCentreInteret().setColumns(2);
        this.getGrilleCentreInteret().setRows(2);
        this.getGrilleHobby().setRows(2);

        //Ajouts sur la fenêtre des panels.
        this.add(getInfoGeneralPanel());
        this.add(getCentreInteretPanel());
        this.add(getBoutonPanel());
        this.add(getDateNaissancePanel());
        this.add(getHobbyPanel());

        String[] sjour = new String[32];
        String[] sannee = new String[100];

        try {
            //les # représentent des chiffres
            setNumero(new MaskFormatter("## ## ## ## ##"));
            setNumeroTel(new JFormattedTextField(getNumero()));
        } catch (ParseException ex) {
            Logger.getLogger(FicheContactUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         * Ajoute un label associé au champ pour la saisie du nom du contact
         */
        JLabel nom = new JLabel("Nom :", JLabel.RIGHT);
        this.getInfoGeneralPanel().add(nom);
        /*
         * Ajoute dans l'IHM un champ pour la saisie/Affichage du nom
         */
        this.getInfoGeneralPanel().add(getChampNom());
        this.getInfoGeneralPanel().add(new JLabel("Date de naissance : ", JLabel.RIGHT));

        for (int i = 1; i <= 31; i++) {
            sjour[i] = Integer.toString(i);
            getJour().addItem(sjour[i]);
        }

        this.getDateNaissancePanel().add(getJour());
        this.getDateNaissancePanel().add(getMoi());

        for (int i = 1; i < 100; i++) {
            sannee[i] = Integer.toString(i + 1900);
            getAnnee().addItem(sannee[i]);
        }
        this.getDateNaissancePanel().add(getAnnee());

        this.getInfoGeneralPanel().add(this.getDateNaissancePanel());

        this.getInfoGeneralPanel().add(new JLabel("Prénom : ", JLabel.RIGHT));
        this.getInfoGeneralPanel().add(getChampPrenom());
        this.getInfoGeneralPanel().add(new JLabel("Email : ", JLabel.RIGHT));
        this.getInfoGeneralPanel().add(getChampEmail());
        this.getInfoGeneralPanel().add(new JLabel("Numero : ", JLabel.RIGHT));
        this.getInfoGeneralPanel().add(getNumeroTel());
        this.getInfoGeneralPanel().add(new JLabel("Region : ", JLabel.RIGHT));

        for (Region r : Region.values()) {
            getRegions().addItem(r);
        }

        this.getInfoGeneralPanel().add(getRegions());

        this.getCentreInteretPanel().add(new JLabel("Hobby préférés (plusieurs choix) : "));

        for (Hobby h : Hobby.values()) {
            setCheckHobby(new JCheckBox(h.toString()));
            getHobbyCheckTable().put(h, getCheckHobby());
            this.getHobbyPanel().add(getCheckHobby());
        }
        this.getCentreInteretPanel().add(this.getHobbyPanel());
        /**
         * TP 1 : à compléter *
         */
        this.getCentreInteretPanel().add(new JLabel("Disponibilité préférée : "));

        for (DispoSortie h : DispoSortie.values()) {
            setChoixDispo(new JRadioButton(h.toString()));
            this.getDispoPanel().add(getChoixDispo());
            getGroupeRadio().add(getChoixDispo());
            getMapRadio().put(h, getChoixDispo());
        }
        this.getCentreInteretPanel().add(this.getDispoPanel());

        //ajout des boutons
        this.contrainteGrilleBouton.fill = GridBagConstraints.NONE;
        this.contrainteGrilleBouton.weightx = 1.;
        this.contrainteGrilleBouton.anchor = GridBagConstraints.CENTER;
        this.contrainteGrilleBouton.insets = new Insets(0, 15, 40, 0);
        this.getBoutonPanel().add(this.getBoutonAnnuler(), getContrainteGrilleBouton());
        this.contrainteGrilleBouton.anchor = GridBagConstraints.CENTER;
        this.contrainteGrilleBouton.insets = new Insets(0, 0, 40, 15);
        this.contrainteGrilleBouton.fill = GridBagConstraints.NONE;
        this.contrainteGrilleBouton.weightx = 1.;
        this.getBoutonPanel().add(this.getBoutonAccepter(), getContrainteGrilleBouton());

    }

    /**
     * Affecte des valeurs au formulaire de fiche contact
     *
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean setValues(Contact contact) {
        if (contact == null) {
            return false;
        }

        getChampPrenom().setText(contact.getPrenom());
        getChampEmail().setText(contact.getEmail());
        getMoi().setSelectedItem(contact.getDateNaissanceMois());
        getAnnee().setSelectedItem(Integer.toString(contact.getDateNaissanceAnnee()));
        getJour().setSelectedIndex(contact.getDateNaissanceJour() - 1);

        for (DispoSortie d : getMapRadio().keySet()) {
            if (d.equals(contact.getDisponibilite())) {
                getMapRadio().get(d).setSelected(true);
            }
        }
        getRegions().setSelectedItem(contact.getRegion());
        getNumeroTel().setText(contact.getNumeroTelephone());
        /*
         * Nom du contact
         */
        getChampNom().setText(contact.getNom());

        for (Hobby hob : getHobbyCheckTable().keySet()) {
            getHobbyCheckTable().get(hob).setSelected(false);
        }

        for (Hobby h : contact.getHobbies()) {
            for (Hobby hPrime : getHobbyCheckTable().keySet()) {
                if (h.equals(hPrime)) {
                    getHobbyCheckTable().get(hPrime).setSelected(true);
                }
            }
        }
        return true;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche contact
     *
     * @param contact un contact à mettre à jour à partir de l'IHM
     * @return vrai si ok
     */
    public boolean getValues(Contact contact) {
        if (contact == null) {
            return false;
        }

        contact.setPrenom(getChampPrenom().getText());
        contact.setEmail(getChampPrenom().getText());
        contact.setNumeroTelephone(getNumeroTel().getText());
        contact.setDateNaissance(Integer.parseInt(getJour().getSelectedItem().toString()),
                (Mois) getMoi().getSelectedItem(),
                Integer.parseInt(getAnnee().getSelectedItem().toString()));
        for (DispoSortie d : getMapRadio().keySet()) {
            if (getMapRadio().get(d).isSelected()) {
                contact.setDisponibilite(d);
            }
        }

        for (Hobby h : getHobbyCheckTable().keySet()) {
            if (getHobbyCheckTable().get(h).isSelected()) {
                contact.addHobby(h);
            } else {
                contact.removeHobby(h);
            }
        }

        contact.setRegion((Region) getRegions().getSelectedItem());
        /*
         * Affecte une valeur à l'attribut Nom avec le nom saisi dans le champ
         * correspondant de l'IHM
         */
        contact.setNom(getChampNom().getText());

        return true;
    }

    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {

        this.getBoutonAccepter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCarnet().setContactModified(true);
            }
        });

        this.getBoutonAnnuler().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCarnet().setContactModified(false);
            }
        });
    }

    /**
     * @return the carnet
     */
    private CarnetUI getCarnet() {
        return carnet;
    }

    /**
     * @param carnet the carnet to set
     */
    private void setCarnet(CarnetUI carnet) {
        this.carnet = carnet;
    }

    /**
     * @return the champNom
     */
    private JTextField getChampNom() {
        return champNom;
    }

    /**
     * @param champNom the champNom to set
     */
    private void setChampNom(JTextField champNom) {
        this.champNom = champNom;
    }

    /**
     * @return the champPrenom
     */
    private JTextField getChampPrenom() {
        return champPrenom;
    }

    /**
     * @param champPrenom the champPrenom to set
     */
    private void setChampPrenom(JTextField champPrenom) {
        this.champPrenom = champPrenom;
    }

    /**
     * @return the champEmail
     */
    private JTextField getChampEmail() {
        return champEmail;
    }

    /**
     * @param champEmail the champEmail to set
     */
    private void setChampEmail(JTextField champEmail) {
        this.champEmail = champEmail;
    }

    /**
     * @return the numeroTel
     */
    private JFormattedTextField getNumeroTel() {
        return numeroTel;
    }

    /**
     * @param numeroTel the numeroTel to set
     */
    private void setNumeroTel(JFormattedTextField numeroTel) {
        this.numeroTel = numeroTel;
    }

    /**
     * @return the numero
     */
    private MaskFormatter getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    private void setNumero(MaskFormatter numero) {
        this.numero = numero;
    }

    /**
     * @return the checkHobby
     */
    private JCheckBox getCheckHobby() {
        return checkHobby;
    }

    /**
     * @param checkHobby the checkHobby to set
     */
    private void setCheckHobby(JCheckBox checkHobby) {
        this.checkHobby = checkHobby;
    }

    /**
     * @return the choixDispo
     */
    private JRadioButton getChoixDispo() {
        return choixDispo;
    }

    /**
     * @param choixDispo the choixDispo to set
     */
    private void setChoixDispo(JRadioButton choixDispo) {
        this.choixDispo = choixDispo;
    }

    /**
     * @return the groupeRadio
     */
    private ButtonGroup getGroupeRadio() {
        return groupeRadio;
    }

    /**
     * @param groupeRadio the groupeRadio to set
     */
    private void setGroupeRadio(ButtonGroup groupeRadio) {
        this.groupeRadio = groupeRadio;
    }

    /**
     * @return the groupeCheck
     */
    private ButtonGroup getGroupeCheck() {
        return groupeCheck;
    }

    /**
     * @param groupeCheck the groupeCheck to set
     */
    private void setGroupeCheck(ButtonGroup groupeCheck) {
        this.groupeCheck = groupeCheck;
    }

    /**
     * @return the mapRadio
     */
    private HashMap<DispoSortie, JRadioButton> getMapRadio() {
        return mapRadio;
    }

    /**
     * @param mapRadio the mapRadio to set
     */
    private void setMapRadio(HashMap<DispoSortie, JRadioButton> mapRadio) {
        this.mapRadio = mapRadio;
    }

    /**
     * @return the annees
     */
    private String[] getAnnees() {
        return annees;
    }

    /**
     * @param annees the annees to set
     */
    private void setAnnees(String[] annees) {
        this.annees = annees;
    }

    /**
     * @return the jours
     */
    private String[] getJours() {
        return jours;
    }

    /**
     * @param jours the jours to set
     */
    private void setJours(String[] jours) {
        this.jours = jours;
    }

    /**
     * @return the hobbyCheckTable
     */
    private HashMap<Hobby, JCheckBox> getHobbyCheckTable() {
        return hobbyCheckTable;
    }

    /**
     * @param hobbyCheckTable the hobbyCheckTable to set
     */
    private void setHobbyCheckTable(HashMap<Hobby, JCheckBox> hobbyCheckTable) {
        this.hobbyCheckTable = hobbyCheckTable;
    }

    /**
     * @return the annee
     */
    private JComboBox getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    private void setAnnee(JComboBox annee) {
        this.annee = annee;
    }

    /**
     * @return the moi
     */
    private JComboBox getMoi() {
        return moi;
    }

    /**
     * @param moi the moi to set
     */
    private void setMoi(JComboBox moi) {
        this.moi = moi;
    }

    /**
     * @return the jour
     */
    private JComboBox getJour() {
        return jour;
    }

    /**
     * @param jour the jour to set
     */
    private void setJour(JComboBox jour) {
        this.jour = jour;
    }

    /**
     * @return the regions
     */
    private JComboBox getRegions() {
        return regions;
    }

    /**
     * @param regions the regions to set
     */
    private void setRegions(JComboBox regions) {
        this.regions = regions;
    }

    /**
     * @return the boutonAccepter
     */
    private JButton getBoutonAccepter() {
        return boutonAccepter;
    }

    /**
     * @param boutonAccepter the boutonAccepter to set
     */
    private void setBoutonAccepter(JButton boutonAccepter) {
        this.boutonAccepter = boutonAccepter;
    }

    /**
     * @return the boutonAnnuler
     */
    private JButton getBoutonAnnuler() {
        return boutonAnnuler;
    }

    /**
     * @param boutonAnnuler the boutonAnnuler to set
     */
    private void setBoutonAnnuler(JButton boutonAnnuler) {
        this.boutonAnnuler = boutonAnnuler;
    }

    /**
     * @return the infoGeneralPanel
     */
    private JPanel getInfoGeneralPanel() {
        return infoGeneralPanel;
    }

    /**
     * @param infoGeneralPanel the infoGeneralPanel to set
     */
    private void setInfoGeneralPanel(JPanel infoGeneralPanel) {
        this.infoGeneralPanel = infoGeneralPanel;
    }

    /**
     * @return the centreInteretPanel
     */
    private JPanel getCentreInteretPanel() {
        return centreInteretPanel;
    }

    /**
     * @param centreInteretPanel the centreInteretPanel to set
     */
    private void setCentreInteretPanel(JPanel centreInteretPanel) {
        this.centreInteretPanel = centreInteretPanel;
    }

    /**
     * @return the boutonPanel
     */
    private JPanel getBoutonPanel() {
        return boutonPanel;
    }

    /**
     * @param boutonPanel the boutonPanel to set
     */
    private void setBoutonPanel(JPanel boutonPanel) {
        this.boutonPanel = boutonPanel;
    }

    /**
     * @return the dateNaissancePanel
     */
    private JPanel getDateNaissancePanel() {
        return dateNaissancePanel;
    }

    /**
     * @param dateNaissancePanel the dateNaissancePanel to set
     */
    private void setDateNaissancePanel(JPanel dateNaissancePanel) {
        this.dateNaissancePanel = dateNaissancePanel;
    }

    /**
     * @return the hobbyPanel
     */
    private JPanel getHobbyPanel() {
        return hobbyPanel;
    }

    /**
     * @param hobbyPanel the hobbyPanel to set
     */
    private void setHobbyPanel(JPanel hobbyPanel) {
        this.hobbyPanel = hobbyPanel;
    }

    /**
     * @return the dispoPanel
     */
    private JPanel getDispoPanel() {
        return dispoPanel;
    }

    /**
     * @param dispoPanel the dispoPanel to set
     */
    private void setDispoPanel(JPanel dispoPanel) {
        this.dispoPanel = dispoPanel;
    }

    /**
     * @return the champsGauchePanel
     */
    private JPanel getChampsGauchePanel() {
        return champsGauchePanel;
    }

    /**
     * @param champsGauchePanel the champsGauchePanel to set
     */
    private void setChampsGauchePanel(JPanel champsGauchePanel) {
        this.champsGauchePanel = champsGauchePanel;
    }

    /**
     * @return the grilleLayoutPrincipal
     */
    private GridLayout getGrilleLayoutPrincipal() {
        return grilleLayoutPrincipal;
    }

    /**
     * @param grilleLayoutPrincipal the grilleLayoutPrincipal to set
     */
    private void setGrilleLayoutPrincipal(GridLayout grilleLayoutPrincipal) {
        this.grilleLayoutPrincipal = grilleLayoutPrincipal;
    }

    /**
     * @return the grilleInfo
     */
    private GridLayout getGrilleInfo() {
        return grilleInfo;
    }

    /**
     * @param grilleInfo the grilleInfo to set
     */
    private void setGrilleInfo(GridLayout grilleInfo) {
        this.grilleInfo = grilleInfo;
    }

    /**
     * @return the grilleCentreInteret
     */
    private GridLayout getGrilleCentreInteret() {
        return grilleCentreInteret;
    }

    /**
     * @param grilleCentreInteret the grilleCentreInteret to set
     */
    private void setGrilleCentreInteret(GridLayout grilleCentreInteret) {
        this.grilleCentreInteret = grilleCentreInteret;
    }

    /**
     * @return the grilleHobby
     */
    private GridLayout getGrilleHobby() {
        return grilleHobby;
    }

    /**
     * @param grilleHobby the grilleHobby to set
     */
    private void setGrilleHobby(GridLayout grilleHobby) {
        this.grilleHobby = grilleHobby;
    }

    /**
     * @return the grilleDispoLayout
     */
    private GridLayout getGrilleDispoLayout() {
        return grilleDispoLayout;
    }

    /**
     * @param grilleDispoLayout the grilleDispoLayout to set
     */
    private void setGrilleDispoLayout(GridLayout grilleDispoLayout) {
        this.grilleDispoLayout = grilleDispoLayout;
    }

    /**
     * @return the grilleBouton
     */
    private GridBagLayout getGrilleBouton() {
        return grilleBouton;
    }

    /**
     * @param grilleBouton the grilleBouton to set
     */
    private void setGrilleBouton(GridBagLayout grilleBouton) {
        this.grilleBouton = grilleBouton;
    }

    /**
     * @return the contrainteGrilleBouton
     */
    private GridBagConstraints getContrainteGrilleBouton() {
        return contrainteGrilleBouton;
    }

    /**
     * @param contrainteGrilleBouton the contrainteGrilleBouton to set
     */
    private void setContrainteGrilleBouton(GridBagConstraints contrainteGrilleBouton) {
        this.contrainteGrilleBouton = contrainteGrilleBouton;
    }
}
