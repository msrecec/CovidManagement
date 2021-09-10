package hr.java.covidportal.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Osoba {

    public static class Builder {

        private String ime;
        private String prezime;
        private Integer starost;
        private Zupanija zupanija;
        private Bolest zarazenBolescu;
        private Osoba[] kontaktiraneOsobe;

        public Builder() {}

        public Builder ime(String ime) {
            this.ime = ime;
            return this;
        }
        public Builder prezime(String prezime) {
            this.prezime = prezime;
            return this;
        }
        public Builder starost(Integer starost) {
            this.starost = starost;
            return this;
        }
        public Builder zupanija(Zupanija zupanija) {
            this.zupanija = zupanija;
            return this;
        }
        public Builder zarazenBolescu(Bolest zarazenBolescu) {
            this.zarazenBolescu = zarazenBolescu;
            return this;
        }
        public Builder kontaktiraneOsobe(Osoba[] kontaktiraneOsobe) {
            this.kontaktiraneOsobe = kontaktiraneOsobe;
            return this;
        }

        public Osoba build() {

            Osoba osoba = new Osoba();

            osoba.setIme(ime);
            osoba.setPrezime(prezime);
            osoba.setStarost(starost);
            osoba.setZupanija(zupanija);
            osoba.setZarazenBolescu(zarazenBolescu);
            osoba.setKontaktiraneOsobe(kontaktiraneOsobe);

            if(zarazenBolescu instanceof Virus virus) {
                if(kontaktiraneOsobe != null) {
                    for(Osoba kontaktiranaOsoba: osoba.kontaktiraneOsobe) {
                        virus.prelazakZarazeNaOsobu(kontaktiranaOsoba);
                    }
                }
            }

            return osoba;

        }

    }

    private String ime;
    private String prezime;
    private Integer starost;
    private Zupanija zupanija;
    private Bolest zarazenBolescu;
    private Osoba[] kontaktiraneOsobe;

    public Osoba() {}

    public Osoba(String ime, String prezime, Integer starost, Zupanija zupanija, Bolest zarazenBolescu, Osoba[] kontaktiraneOsobe) {
        this.ime = ime;
        this.prezime = prezime;
        this.starost = starost;
        this.zupanija = zupanija;
        this.zarazenBolescu = zarazenBolescu;
        this.kontaktiraneOsobe = kontaktiraneOsobe;

        if(zarazenBolescu instanceof Virus virus) {
            if(kontaktiraneOsobe != null) {
                for(Osoba kontaktiranaOsoba: this.kontaktiraneOsobe) {
                    virus.prelazakZarazeNaOsobu(kontaktiranaOsoba);
                }
            }
        }
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Integer getStarost() {
        return starost;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }

    public Zupanija getZupanija() {
        return zupanija;
    }

    public void setZupanija(Zupanija zupanija) {
        this.zupanija = zupanija;
    }

    public Bolest getZarazenBolescu() {
        return zarazenBolescu;
    }

    public void setZarazenBolescu(Bolest zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }

    public Osoba[] getKontaktiraneOsobe() {
        return kontaktiraneOsobe;
    }

    public void setKontaktiraneOsobe(Osoba[] kontaktiraneOsobe) {
        this.kontaktiraneOsobe = kontaktiraneOsobe;
    }


    @Override
    public String toString() {
        return "Osoba{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", starost=" + starost +
                ", zupanija=" + zupanija +
                ", zarazenBolescu=" + zarazenBolescu +
                ", kontaktiraneOsobe=" + Arrays.toString(kontaktiraneOsobe) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return Objects.equals(ime, osoba.ime) && Objects.equals(prezime, osoba.prezime) && Objects.equals(starost, osoba.starost) && Objects.equals(zupanija, osoba.zupanija) && Objects.equals(zarazenBolescu, osoba.zarazenBolescu) && Arrays.equals(kontaktiraneOsobe, osoba.kontaktiraneOsobe);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ime, prezime, starost, zupanija, zarazenBolescu);
        result = 31 * result + Arrays.hashCode(kontaktiraneOsobe);
        return result;
    }
}
