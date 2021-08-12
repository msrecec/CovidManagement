package hr.java.covidportal.model;

public class Simptom {
    String naziv;
    String vrijednost;

    public Simptom(String naziv, String vrijednost) {
        this.naziv = naziv;
        this.vrijednost = vrijednost;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    @Override
    public String toString() {
        return "Simptom{" +
                "naziv='" + naziv + '\'' +
                ", vrijednost='" + vrijednost + '\'' +
                '}';
    }
}
