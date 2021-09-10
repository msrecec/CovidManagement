package hr.java.covidportal.model;

import java.util.Arrays;
import java.util.List;

public class Bolest extends ImenovaniEntitet{
    private Simptom[] simptomi;

    public Bolest(String naziv, Simptom[] simptomi) {
        super(naziv);
        this.simptomi = simptomi;
    }

    public Simptom[] getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(Simptom[] simptomi) {
        this.simptomi = simptomi;
    }

    @Override
    public String toString() {
        return "Bolest{" +
                "naziv='" + getNaziv() + '\'' +
                ", simptomi=" + Arrays.toString(simptomi) +
                '}';
    }
}
