package hr.java.covidportal.model;

import java.util.List;

public class Bolest {
    private String naziv;
    private List<Simptom> simptomi;

    public Bolest(String naziv, List<Simptom> simptomi) {
        this.naziv = naziv;
        this.simptomi = simptomi;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Simptom> getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(List<Simptom> simptomi) {
        this.simptomi = simptomi;
    }

    @Override
    public String toString() {
        return "Bolest{" +
                "naziv='" + naziv + '\'' +
                ", simptomi=" + simptomi +
                '}';
    }
}
