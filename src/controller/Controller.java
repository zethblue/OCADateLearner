package controller;

import viewing.Rahmen;

import javax.swing.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    private Rahmen R1;
    private ArrayList<LocalDate> dates;
    private ArrayList<LocalTime> times;
    private ArrayList<LocalDateTime> combinedDateTime;
    private LocalDate d1;
    private LocalTime t1;
    private LocalDateTime dt1;

    public Controller(){
        R1 = new Rahmen(this);
        dates = new ArrayList<>();
        times = new ArrayList<>();
        combinedDateTime = new ArrayList<>();
    }

    public void refreshDate(){
        d1 = LocalDate.now();
        R1.setAktuellesDatum(d1.toString());
    }
    public void refreshTime(){
        t1 = LocalTime.now();
        R1.setAktuelleUhrzeit(t1.toString());
    }
    public void saveTime(){
        times.add(t1);
        int i = times.size();
        R1.setSavedTime(times.get(i-1).toString());
    }
    public void saveDate(){
        dates.add(d1);
        int i = dates.size();
        R1.setSavedDate(dates.get(i-1).toString());
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
        R1.setAktuellesDatum(d1.toString());
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

        R1.setAktuelleUhrzeit(t1.toString());



    }
    public String combineDateTime(){
        dt1 = LocalDateTime.of(d1,t1);
        return dt1.toString();

    }
    public void dateTimeinArrayList(){
        combinedDateTime.add(dt1);
    }

    public JList ausgabeListe1(){
        ArrayList<String> liste = new ArrayList<>();
        for (LocalDate d : dates){
            liste.add(d.toString());
        }
        for (LocalTime t : times){
            liste.add(t.toString());
        }
        String[] ausgabeArray = new String[liste.size()+1];
        liste.toArray(ausgabeArray);
        ausgabeArray[ausgabeArray.length-1] = "END OF LIST FOR DATES AND TIMES";

        return new JList(ausgabeArray);
    }
    public JList ausgabeListe2(){
        ArrayList<String> liste = new ArrayList<>();
        for (LocalDateTime dt : combinedDateTime){
            liste.add(dt.toString());
        }
        String[] ausgabeArray = new String[liste.size()+1];
        liste.toArray(ausgabeArray);
        ausgabeArray[ausgabeArray.length-1] = "END OF LIST FOR COMBINED DATES & TIMES";

        return new JList(ausgabeArray);
    }

    public boolean nullPointerEvasion(){
        return d1 != null;
    }






}
