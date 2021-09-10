package hr.java.covidportal.model;

public interface Zarazno {
    void prelazakZarazeNaOsobu(Osoba osoba);
    public static void prelazak(Bolest bolest, Osoba[] kontaktiraneOsobe, Osoba osoba) {
        if(bolest instanceof Virus virus) {
            if(kontaktiraneOsobe != null) {
                for(int i = 0; i < kontaktiraneOsobe.length; ++i) {
                    virus.prelazakZarazeNaOsobu(osoba);
                }
            }
        }
    }
}
