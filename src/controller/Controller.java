package controller;

import viewing.Rahmen;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Controller {

    private Rahmen R1;
    private ArrayList<LocalDate> dates;
    private ArrayList<LocalTime> times;
    private ArrayList<LocalDateTime> combinedDateTime;
    private LocalDate d1;
    private LocalTime t1;
    private LocalDateTime dt1;
    private int whichFormatHasDateNTime;
    private DateTimeFormatter shortD;
    private DateTimeFormatter mediumD;
    private DateTimeFormatter shortT;
    private DateTimeFormatter mediumT;
    private DateTimeFormatter shortDT;
    private DateTimeFormatter mediumDT;
    private int iSaveTime;
    private int iSaveDate;


    public Controller(){
        R1 = new Rahmen(this);
        dates = new ArrayList<>();
        times = new ArrayList<>();
        combinedDateTime = new ArrayList<>();
        shortD = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        mediumD = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        shortT = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        mediumT = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        shortDT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        mediumDT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    }

    public void refreshDate(){
        d1 = LocalDate.now();
        switch (whichFormatHasDateNTime){
            case 1 : R1.setAktuellesDatum(shortD.format(d1));break;
            case 2 : R1.setAktuellesDatum(mediumD.format(d1));break;
            default : R1.setAktuellesDatum(d1.toString());break;
        }

    }
    public void refreshTime(){
        t1 = LocalTime.now();
        switch (whichFormatHasDateNTime){
            case 1 : R1.setAktuelleUhrzeit(shortT.format(t1));break;
            case 2 : R1.setAktuelleUhrzeit(mediumT.format(t1));break;
            default : R1.setAktuelleUhrzeit(t1.toString());break;
        }

    }
    public void saveTime(){
        times.add(t1);
        iSaveTime = times.size();
        switch (whichFormatHasDateNTime){
            case 1 : R1.setSavedTime(shortT.format(times.get(iSaveTime-1)));break;
            case 2 : R1.setSavedTime(mediumT.format(times.get(iSaveTime-1)));break;
            default : R1.setSavedTime(times.get(iSaveTime-1).toString());break;
        }
    }
    public void saveDate(){
        dates.add(d1);
        iSaveDate = dates.size();
        switch (whichFormatHasDateNTime){
            case 1 : R1.setSavedDate(shortD.format(dates.get(iSaveDate-1)));break;
            case 2 : R1.setSavedTime(mediumD.format(dates.get(iSaveDate-1)));break;
            default : R1.setSavedDate(dates.get(iSaveDate-1).toString());break;
        }
    }
    public void changeDate(int i, int j){

        switch(i){
            case 1 : d1 = d1.minusDays(j);break;
            case 2 : d1 = d1.minusWeeks(j);break;
            case 3 : d1 = d1.minusMonths(j);break;
            case 4 : d1 = d1.minusYears(j);break;
            case 5 : d1 = d1.plusDays(j);break;
            case 6 : d1 = d1.plusWeeks(j);break;
            case 7 : d1 = d1.plusMonths(j);break;
            case 8 : d1 = d1.plusYears(j);break;
        }
        switch (whichFormatHasDateNTime){
            case 1 : R1.setAktuellesDatum(shortD.format(d1));break;
            case 2 : R1.setAktuellesDatum(mediumD.format(d1));break;
            default : R1.setAktuellesDatum(d1.toString());break;
        }
    }
    public void changeTime(int i, int j){

        switch(i){
            case 1 : t1 = t1.minusNanos(j);break;
            case 2 : t1 = t1.minusSeconds(j);break;
            case 3 : t1 = t1.minusMinutes(j);break;
            case 4 : t1 = t1.minusHours(j);break;
            case 5 : t1 = t1.plusNanos(j);break;
            case 6 : t1 = t1.plusSeconds(j);break;
            case 7 : t1 = t1.plusMinutes(j);break;
            case 8 : t1 = t1.plusHours(j);break;
        }

        switch (whichFormatHasDateNTime){
            case 1 : R1.setAktuelleUhrzeit(shortT.format(t1));break;
            case 2 : R1.setAktuelleUhrzeit(mediumT.format(t1));break;
            default : R1.setAktuelleUhrzeit(t1.toString());break;
        }



    }
    public String combineDateTime(){
        dt1 = LocalDateTime.of(d1,t1);
        switch (whichFormatHasDateNTime){
            case 1 : return shortDT.format(dt1);
            case 2 : return mediumDT.format(dt1);
            default : return dt1.toString();
        }


    }
    public void dateTimeinArrayList(){
        combinedDateTime.add(dt1);
    }
    public void clearallLists(){
        dates = new ArrayList<>();
        times = new ArrayList<>();
        combinedDateTime = new ArrayList<>();
    }
    public JList ausgabeListe1(){
        ArrayList<String> liste = new ArrayList<>();
        for (LocalDate d : dates){
            switch (whichFormatHasDateNTime){
                case 1 : liste.add(shortD.format(d));break;
                case 2 : liste.add(mediumD.format(d));break;
                default : liste.add(d.toString());break;
            }
        }
        for (LocalTime t : times){
            switch (whichFormatHasDateNTime){
                case 1 : liste.add(shortT.format(t));break;
                case 2 : liste.add(mediumT.format(t));break;
                default : liste.add(t.toString());break;
            }
        }
        String[] ausgabeArray = new String[liste.size()+1];
        liste.toArray(ausgabeArray);
        ausgabeArray[ausgabeArray.length-1] = "END OF LIST FOR DATES AND TIMES";

        return new JList(ausgabeArray);
    }
    public JList ausgabeListe2(){
        ArrayList<String> liste = new ArrayList<>();
        for (LocalDateTime dt : combinedDateTime){
            switch (whichFormatHasDateNTime){
                case 1 : liste.add(shortDT.format(dt));break;
                case 2 : liste.add(mediumDT.format(dt));break;
                default : liste.add(dt.toString());break;
            }
        }
        String[] ausgabeArray = new String[liste.size()+1];
        liste.toArray(ausgabeArray);
        ausgabeArray[ausgabeArray.length-1] = "END OF LIST FOR COMBINED DATES & TIMES";

        return new JList(ausgabeArray);
    }
    public void setWhichFormatHasDate(){
        whichFormatHasDateNTime = (whichFormatHasDateNTime < 2) ? whichFormatHasDateNTime+1 : 0;
        switch (whichFormatHasDateNTime){
            case 1 : R1.setAktuellesDatum(shortD.format(d1));R1.setAktuelleUhrzeit(shortT.format(t1));if(iSaveTime>0){R1.setSavedTime(shortT.format(times.get(iSaveTime-1)));}break;
            case 2 : R1.setAktuellesDatum(mediumD.format(d1));R1.setAktuelleUhrzeit(mediumT.format(t1));if(iSaveTime>0){R1.setSavedTime(mediumT.format(times.get(iSaveTime-1)));}break;
            default : R1.setAktuellesDatum(d1.toString());R1.setAktuelleUhrzeit(t1.toString());if(iSaveTime>0){R1.setSavedTime(times.get(iSaveTime-1).toString());}break;
        }
    }

    public boolean nullPointerEvasion(){
        return d1 != null;
    }






}
