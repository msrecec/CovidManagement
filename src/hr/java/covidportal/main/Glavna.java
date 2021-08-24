package hr.java.covidportal.main;

import hr.java.covidportal.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Glavna {
    private static final int BROJ_ZUPANIJA = 3;
    private static final int BROJ_SIMPTOMA = 3;
    private static final int BROJ_BOLESTI = 3;
    private static final int BROJ_OSOBA = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Zupanija> zupanije = new ArrayList<>();
        List<Simptom> simptomi = new ArrayList<>();
        List<Bolest> bolesti = new ArrayList<>();
        List<Osoba> osobe = new ArrayList<>();

        unosZupanija(zupanije, scanner, BROJ_ZUPANIJA);

        unosSimptoma(simptomi, scanner, BROJ_SIMPTOMA);

        unosBolesti(bolesti, simptomi, scanner, BROJ_BOLESTI);

        unosOsoba(osobe, zupanije, bolesti, scanner, BROJ_OSOBA);

        osobe.stream().forEach(Glavna::ispisOsobe);

        scanner.close();
    }

    private static void unosZupanija(List<Zupanija> zupanije, Scanner scanner, final int limit) {
        String naziv;
        Integer brojStanovnika;

        System.out.println("Unesite podatke o " + limit + " zupanije:");

        for(int i = 0; i < limit; ++i) {
            System.out.println("Unesite naziv zupanije: ");

            naziv = scanner.nextLine();

            System.out.println("Unesite broj stanovnika: ");

            brojStanovnika = Integer.parseInt(scanner.nextLine());

            zupanije.add(new Zupanija(naziv, brojStanovnika));
        }
    }

    private static void unosSimptoma(List<Simptom> simptomi, Scanner scanner, final int limit) {
        String naziv;
        String vrijednostSimptoma;

        System.out.println("Unesite podatke o " + BROJ_SIMPTOMA + " simptoma:");

        for(int i = 0; i < limit; ++i) {
            System.out.println("Unesite naziv simptoma: ");

            naziv = scanner.nextLine();

            System.out.println(
                    "Unesite vrijednost simptoma +(" +
                    VrijednostSimptoma.RIJETKO.getVrijednost() + ", " +
                    VrijednostSimptoma.SREDNJE.getVrijednost() + " ili " +
                    VrijednostSimptoma.CESTO.getVrijednost() + "): ");

            vrijednostSimptoma = scanner.nextLine();

            if(
                    vrijednostSimptoma.compareTo(VrijednostSimptoma.RIJETKO.getVrijednost()) != 0 ||
                    vrijednostSimptoma.compareTo(VrijednostSimptoma.SREDNJE.getVrijednost()) != 0 ||
                    vrijednostSimptoma.compareTo(VrijednostSimptoma.CESTO.getVrijednost()) != 0
            ) {
                System.out.println("Vrijednost simtpoma nije odgovarajuca, unesite ponovno");
                i--;
                continue;
            }

            simptomi.add(new Simptom(naziv, vrijednostSimptoma));
        }
    }

    private static void unosBolesti(List<Bolest> bolesti, List<Simptom> simptomi, Scanner scanner, final int limit) {
        String naziv;
        Integer ukupanBrojSimptoma;
        Integer brojSimptoma;
        List<Simptom> odabraniSimptomi;

        System.out.println("Unesite podatke o " + limit + " bolesti:");

        for(int i = 0; i < limit; ++i) {

            odabraniSimptomi = new ArrayList<>();
            System.out.println("Unesite naziv bolesti: ");
            naziv = scanner.nextLine();
            System.out.println("Unesite broj simptoma: ");
            ukupanBrojSimptoma = Integer.parseInt(scanner.nextLine());
            for(int j = 0; j < ukupanBrojSimptoma; ++j) {
                System.out.println("Odaberite " + (j+1) + ". simptom:");
                for(int k = 0; k < simptomi.size(); ++k) {
                    System.out.println(k+1 + ". " + simptomi.get(k).getNaziv() + " " + simptomi.get(k).getVrijednost());
                }
                brojSimptoma = Integer.parseInt(scanner.nextLine())-1;
                if(brojSimptoma < 0 || brojSimptoma > ukupanBrojSimptoma) {
                    System.out.println("Neispravan unos, molim pokusajte ponovno!");
                    j--;
                    continue;
                }
                odabraniSimptomi.add(simptomi.get(brojSimptoma));
            }
            bolesti.add(new Bolest(naziv, odabraniSimptomi));
        }
    }

    private static void unosOsoba(List<Osoba> osobe, List<Zupanija> zupanije, List<Bolest> bolesti, Scanner scanner, final int limit) {
        String ime;
        String prezime;
        int starost;
        int brojZupanije;
        Zupanija zupanija;
        int brojBolesti;
        Bolest bolest;
        int ukupanBrojKontaktiranihOsoba;
        int brojKontaktiraneOsobe;
        List<Osoba> kontaktiraneOsobe;
        System.out.println("Unesite podatke o " + limit + " osobe");
        for(int i = 0; i < limit; ++i) {
            System.out.println("Unesite ime " + (i+1) + " osobe");
            ime = scanner.nextLine();
            System.out.println("Unesite prezime " + (i+1) + " osobe");
            prezime = scanner.nextLine();
            System.out.println("Unesite starost osobe: ");
            starost = Integer.parseInt(scanner.nextLine());
            System.out.println("Unesite zupaniju prebivalista osobe");
            for(int j = 0; j < zupanije.size(); ++j) {
                System.out.println((j+1) + ". " + zupanije.get(j).getNaziv());
            }
            brojZupanije = Integer.parseInt(scanner.nextLine());
            brojZupanije--;
            zupanija = zupanije.get(brojZupanije);
            System.out.println("Unesite bolest osobe: ");
            for(int j = 0; j < bolesti.size(); ++j) {
                System.out.println((j+1) + ". " + bolesti.get(j));
            }
            brojBolesti = Integer.parseInt(scanner.nextLine());
            bolest = bolesti.get(brojBolesti);
            if(osobe.size() <= 0) {
                osobe.add(new Osoba(ime, prezime, starost, zupanija, bolest, new ArrayList<>()));
            } else {
                kontaktiraneOsobe = new ArrayList<>();
                System.out.println("Unesite broj osoba koje su bile u kontaktu s tom osobom");
                ukupanBrojKontaktiranihOsoba = Integer.parseInt(scanner.nextLine());
                for(int j = 0; j < ukupanBrojKontaktiranihOsoba; ++j) {
                    for(int k = 0; k < osobe.size(); ++k) {
                        System.out.println((k+1) + ". " + osobe.get(k).getIme() + " " + osobe.get(k).getPrezime());
                    }
                    brojKontaktiraneOsobe = Integer.parseInt(scanner.nextLine());
                    kontaktiraneOsobe.add(osobe.get(brojKontaktiraneOsobe-1));
                }
                osobe.add(new Osoba(ime, prezime, starost, zupanija, bolest, kontaktiraneOsobe));
            }
        }
    }

    private static void ispisOsobe(Osoba osoba) {
        System.out.println("Ime i prezime: " + osoba.getIme() + " " + osoba.getPrezime());
        System.out.println("Starost: " + osoba.getStarost());
        System.out.println("Zupanija prebivalista: " + osoba.getZupanija().getNaziv());
        System.out.println("Zarazen bolescu: " + osoba.getZarazenBolescu().getNaziv());
        if(osoba.getKontaktiraneOsobe().size() > 0) {
            System.out.println("Nema kontaktiranih osoba.");
        } else {
            osoba.getKontaktiraneOsobe().stream().forEach(Glavna::ispisKontaktiraneOsobe);
        }
    }

    private static void ispisKontaktiraneOsobe(Osoba osoba) {
        System.out.println(osoba.getIme() + " " + osoba.getPrezime());
    }


}
