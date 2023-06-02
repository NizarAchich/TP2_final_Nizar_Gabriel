package qc.ca.bdeb.Model;

import qc.ca.bdeb.Utilities.Temps;

import java.util.Date;

public class User {
    private String username;
    private Temps tempsecoule;
    private String dateofscore;
    private Integer numberofpaire;
    private Integer score;

    /**
     * Classe de user
     * @param username
     * @param tempsecoule
     * @param dateofscore
     * @param numberofpaire
     */
    public User(String username, Temps tempsecoule, String dateofscore,Integer numberofpaire) {
        this.username = username;
        this.tempsecoule = tempsecoule;
        this.dateofscore = dateofscore;
        this.numberofpaire=numberofpaire;
        this.score =
                (numberofpaire*100)/
                        (tempsecoule.getHeures()*3600+ tempsecoule.getMinutes()*60+tempsecoule.getSecondes());
    }

    public String getUsername() {
        return username;
    }

    public Temps getTempsecoule() {
        return tempsecoule;
    }

    public String getDateofscore() {
        return dateofscore;
    }

    public Integer getNumberofpaire() {
        return numberofpaire;
    }

    public Integer getScore() {
        return score;
    }

    /**
     * Méthode to String
     * @return
     */
    @Override
    public String toString() {
        return   username + ";" +
                 tempsecoule.toString() +";"+
                 dateofscore.toString() +";"+
                numberofpaire +";"+
                score +"\n"
                ;
    }
}
