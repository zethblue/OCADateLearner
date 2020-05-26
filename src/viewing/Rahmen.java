package viewing;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Rahmen extends JFrame {

    private final Controller C1;
    private JLabel aktuellesDatum = new JLabel();
    private JLabel aktuelleUhrzeit = new JLabel();
    private JLabel savedDate = new JLabel();
    private JLabel savedTime = new JLabel();


    public Rahmen(Controller C1){
        this.C1 = C1;

        JLabel aktuellBeschreibung = new JLabel("aktuelle Daten:");
        JButton refreshButton = new JButton("Start");
        JButton saveDateButton = new JButton("Save Date");
        JButton saveTimeButton = new JButton("Save Time");

        JPanel aktuellPanel = new JPanel();
        aktuellPanel.setLayout(new GridLayout(3,2));
        aktuellPanel.add(aktuellBeschreibung);
        aktuellPanel.add(refreshButton);
        aktuellPanel.add(aktuellesDatum);
        aktuellPanel.add(aktuelleUhrzeit);
        aktuellPanel.add(saveDateButton);
        aktuellPanel.add(saveTimeButton);

        JLabel savedBeschreibung = new JLabel("gespeicherte Daten:");

        JPanel DateNTimeSave = new JPanel();
        DateNTimeSave.setLayout(new GridLayout(3,1));
        DateNTimeSave.add(savedBeschreibung); DateNTimeSave.add(savedDate); DateNTimeSave.add(savedTime);

        JPanel buttonList = new JPanel();
        JButton buttonAusgabeListe = new JButton("Ausgabeliste");
        JButton buttonChangeTime = new JButton("Change Time");
        JButton buttonChangeDate = new JButton("Change Date");
        JButton buttonCombine = new JButton("Combine to Datetime");

        buttonList.setLayout(new GridLayout(2,2));
        buttonList.add(buttonChangeDate);buttonList.add(buttonChangeTime);
        buttonList.add(buttonCombine);buttonList.add(buttonAusgabeListe);



        this.setLayout(new GridLayout(3,1));
        this.add(aktuellPanel); this.add(DateNTimeSave);this.add(buttonList);
        this.setSize(300,450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        refreshButton.addActionListener(e -> {refreshButton.setText("Refresh");C1.refreshDate();C1.refreshTime();});
        saveTimeButton.addActionListener(e -> {
            if(C1.nullPointerEvasion())C1.saveTime();
        });
        saveDateButton.addActionListener(e -> {
            if(C1.nullPointerEvasion())C1.saveDate();
        });
        buttonAusgabeListe.addActionListener(e -> {
            if(C1.nullPointerEvasion())new ListAusgabeRahmen(C1);
        });
        buttonChangeDate.addActionListener(e -> {
            if(C1.nullPointerEvasion())new ChangeDateRahmen(C1);
        });
        buttonChangeTime.addActionListener(e -> {
            if(C1.nullPointerEvasion())new ChangeTimeRahmen(C1);
        });
        buttonCombine.addActionListener(e ->{
            if(C1.nullPointerEvasion())new CombineRahmen(C1);
        } );


    }

    public void setAktuellesDatum(String s) {
        this.aktuellesDatum.setText(s);
    }
    public void setAktuelleUhrzeit(String s) {
        this.aktuelleUhrzeit.setText(s);
    }
    public void setSavedDate(String s) {
        this.savedDate.setText(s);
    }
    public void setSavedTime(String s) {
        this.savedTime.setText(s);
    }

}

class ListAusgabeRahmen extends JFrame{
    JList<String> ausgabeListe1;
    JList<String> ausgabeListe2;

    public ListAusgabeRahmen(Controller C1){

        ausgabeListe1 = C1.ausgabeListe1();
        ausgabeListe2 = C1.ausgabeListe2();
        this.setLayout(new GridLayout(1,2));
        this.add(ausgabeListe1);this.add(ausgabeListe2);
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
class ChangeDateRahmen extends JFrame{
    public ChangeDateRahmen(Controller C1){

        String[] comboBoxListe = {"minusDays", "minusWeeks", "minusMonths", "minusYears",
                                  "plusDays", "plusWeeks", "plusMonths", "plusYears"};
        JComboBox comboBox = new JComboBox(comboBoxListe);
        JTextField zahlenEingabe = new JTextField();
        JButton buttonOK = new JButton("ok");

        this.setLayout(new GridLayout(1,3));
        this.add(comboBox);this.add(zahlenEingabe);this.add(buttonOK);
        this.setSize(400,80);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        buttonOK.addActionListener(e -> {
            int i = 0;
            int j = -1;
            try{ j = Integer.parseInt(zahlenEingabe.getText());}catch (Exception E){zahlenEingabe.setText("Wrong value!");}
            if(j != -1) {
                switch (comboBox.getSelectedItem().toString()) {
                    case "minusDays": i = 1;break;
                    case "minusWeeks": i = 2;break;
                    case "minusMonths": i = 3;break;
                    case "minusYears": i = 4;break;
                    case "plusDays": i = 5;break;
                    case "plusWeeks": i = 6;break;
                    case "plusMonths": i = 7;break;
                    case "plusYears": i = 8;break;
                }
                C1.changeDate(i,j);
                dispose();
            }
        });


    }
}
class ChangeTimeRahmen extends JFrame{
    public ChangeTimeRahmen(Controller C1){

        String[] comboBoxListe = {"minusNanos", "minusSeconds", "minusMinutes", "minusHours",
                "plusNanos", "plusSeconds", "plusMinutes", "plusHours"};
        JComboBox comboBox = new JComboBox(comboBoxListe);
        JTextField zahlenEingabe = new JTextField();
        JButton buttonOK = new JButton("ok");

        this.setLayout(new GridLayout(1,3));
        this.add(comboBox);this.add(zahlenEingabe);this.add(buttonOK);
        this.setSize(400,80);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        buttonOK.addActionListener(e -> {
            int i = 0;
            int j = -1;
            try{ j = Integer.parseInt(zahlenEingabe.getText());}catch (Exception E){zahlenEingabe.setText("Wrong value!");}
            if(j != -1) {
                switch (comboBox.getSelectedItem().toString()) {
                    case "minusNanos": i = 1;break;
                    case "minusSeconds": i = 2;break;
                    case "minusMinutes": i = 3;break;
                    case "minusHours": i = 4;break;
                    case "plusNanos": i = 5;break;
                    case "plusSeconds": i = 6;break;
                    case "plusMinutes": i = 7;break;
                    case "plusHours": i = 8;break;
                }
                C1.changeTime(i,j);
                dispose();
            }
        });


    }
}
class CombineRahmen extends JFrame{
    public CombineRahmen(Controller C1){


        JButton buttonOK = new JButton("OK");
        JButton buttonAbbruch = new JButton("Abbruch");

        JLabel beschreibung = new JLabel("wurde kombiniert zu:");
        JLabel dateTimeObjekt = new JLabel(C1.combineDateTime());

        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new GridLayout(1,2));
        buttonRow.add(buttonOK); buttonRow.add(buttonAbbruch);

        this.setLayout(new GridLayout(3,1));
        this.add(beschreibung);this.add(dateTimeObjekt);this.add(buttonRow);
        this.setSize(250,250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        buttonAbbruch.addActionListener(e -> dispose());
        buttonOK.addActionListener(e -> {
            C1.dateTimeinArrayList();
            dispose();
        });


    }
}

