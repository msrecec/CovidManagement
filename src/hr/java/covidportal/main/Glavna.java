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
        Zupanija[] zupanije = new Zupanija[BROJ_ZUPANIJA];
        Simptom[] simptomi = new Simptom[BROJ_SIMPTOMA];
        Bolest[] bolesti = new Bolest[BROJ_BOLESTI];
        Osoba[] osobe = new Osoba[BROJ_OSOBA];

        unosZupanija(zupanije, scanner, BROJ_ZUPANIJA);

        unosSimptoma(simptomi, scanner, BROJ_SIMPTOMA);

        unosBolesti(bolesti, simptomi, scanner, BROJ_BOLESTI);

        unosOsoba(osobe, zupanije, bolesti, scanner, BROJ_OSOBA);

        ispisOsoba(osobe);

        scanner.close();


    }

    private static void unosZupanija(Zupanija[] zupanije, Scanner scanner, final int limit) {
        String naziv;
        Integer brojStanovnika;

        System.out.println("Unesite podatke o " + limit + " zupanije:");

        for(int i = 0; i < limit; ++i) {
            System.out.println("Unesite naziv zupanije: ");

            naziv = scanner.nextLine();

            System.out.println("Unesite broj stanovnika: ");

            brojStanovnika = Integer.parseInt(scanner.nextLine());

            if(brojStanovnika < 0) {
                System.out.println("Broj stanovnika ne smije biti manji od 0");
                i--;
                continue;
            }
            zupanije[i] = new Zupanija(naziv, brojStanovnika);
        }
    }

    private static void unosSimptoma(Simptom[] simptomi, Scanner scanner, final int limit) {
        String naziv;
        String vrijednostSimptoma;

        System.out.println("Unesite podatke o " + BROJ_SIMPTOMA + " simptoma:");

        for(int i = 0; i < limit; ++i) {
            System.out.println("Unesite naziv simptoma: ");

            naziv = scanner.nextLine();

            System.out.println(
                    "Unesite vrijednost simptoma (" +
                    VrijednostSimptoma.RIJETKO.getVrijednost() + ", " +
                    VrijednostSimptoma.SREDNJE.getVrijednost() + " ili " +
                    VrijednostSimptoma.CESTO.getVrijednost() + "): ");

            vrijednostSimptoma = scanner.nextLine();

            System.out.println(vrijednostSimptoma);

            if(
                    vrijednostSimptoma.compareTo(VrijednostSimptoma.RIJETKO.getVrijednost()) != 0 &&
                    vrijednostSimptoma.compareTo(VrijednostSimptoma.SREDNJE.getVrijednost()) != 0 &&
                    vrijednostSimptoma.compareTo(VrijednostSimptoma.CESTO.getVrijednost()) != 0
            ) {
                System.out.println("Vrijednost simtpoma nije odgovarajuca, unesite ponovno");
                i--;
                continue;
            }

            simptomi[i] = new Simptom(naziv, vrijednostSimptoma);
        }
    }

    private static void unosBolesti(Bolest[] bolesti, Simptom[] simptomi, Scanner scanner, final int limit) {
        String naziv;
        Integer ukupanBrojSimptoma;
        Integer brojSimptoma;
        Simptom[] odabraniSimptomi;

        System.out.println("Unesite podatke o " + limit + " bolesti:");

        for(int i = 0; i < limit; ++i) {
            System.out.println("Unesite naziv bolesti: ");
            naziv = scanner.nextLine();

            System.out.println("Unesite broj simptoma: ");
            ukupanBrojSimptoma = Integer.parseInt(scanner.nextLine());

            if(ukupanBrojSimptoma <= 0) {
                System.out.println("Broj simptoma mora biti veci od 0.");
                i--;
                continue;
            }

            odabraniSimptomi = new Simptom[ukupanBrojSimptoma];

            for(int j = 0; j < ukupanBrojSimptoma; ++j) {
                System.out.println("Odaberite " + (j+1) + ". simptom:");

                for(int k = 0; k < simptomi.length; ++k) {
                    System.out.println(k+1 + ". " + simptomi[k].getNaziv() + " " + simptomi[k].getVrijednost());
                }

                brojSimptoma = Integer.parseInt(scanner.nextLine())-1;

                if(brojSimptoma < 0 || brojSimptoma >= simptomi.length) {
                    System.out.println("Neispravan unos, molim pokusajte ponovno!");
                    j--;
                    continue;
                }

                odabraniSimptomi[j] = simptomi[brojSimptoma];
            }
            bolesti[i] = new Bolest(naziv, odabraniSimptomi);
        }
    }

    private static void unosOsoba(Osoba[] osobe, Zupanija[] zupanije, Bolest[] bolesti, Scanner scanner, final int limit) {
        String ime;
        String prezime;
        int starost;
        int brojZupanije;
        Zupanija zupanija;
        int brojBolesti;
        Bolest bolest;
        int ukupanBrojKontaktiranihOsoba;
        int brojKontaktiraneOsobe;
        Osoba[] kontaktiraneOsobe;

        System.out.println("Unesite podatke o " + limit + " osobe");

        for(int i = 0; i < limit; ++i) {
            System.out.println("Unesite ime " + (i+1) + ". osobe");
            ime = scanner.nextLine();

            System.out.println("Unesite prezime osobe: ");
            prezime = scanner.nextLine();

            System.out.println("Unesite starost osobe: ");
            starost = Integer.parseInt(scanner.nextLine());

            if(starost <= 0) {
                System.out.println("Starost osobe mora biti veca od 0");
                i--;
                continue;
            }

            System.out.println("Unesite zupaniju prebivalista osobe");

            for(int j = 0; j < zupanije.length; ++j) {
                System.out.println((j+1) + ". " + zupanije[j].getNaziv());
            }

            brojZupanije = Integer.parseInt(scanner.nextLine());
            brojZupanije--;

            if(brojZupanije < 0 || brojZupanije >= zupanije.length) {
                System.out.println("Unesli ste neispravan broj zupanije. Unesite ponovno");
                i--;
                continue;
            }

            zupanija = zupanije[brojZupanije];

            System.out.println("Unesite bolest osobe: ");

            for(int j = 0; j < bolesti.length; ++j) {
                System.out.println((j+1) + ". " + bolesti[j].getNaziv());
            }

            brojBolesti = Integer.parseInt(scanner.nextLine());
            brojBolesti--;

            if(brojBolesti < 0 || brojBolesti >= bolesti.length) {
                System.out.println("Unesli ste neispravan broj bolesti. Unesite ponovno.");
                i--;
                continue;
            }

            bolest = bolesti[brojBolesti];

            if(i == 0) {
                osobe[i] = new Osoba.Builder()
                        .ime(ime)
                        .prezime(prezime)
                        .starost(starost)
                        .zupanija(zupanija)
                        .zarazenBolescu(bolest)
                        .build();

            } else {
                while(true) {
                    System.out.println("Unesite broj osoba koje su bile u kontaktu s tom osobom");
                    ukupanBrojKontaktiranihOsoba = Integer.parseInt(scanner.nextLine());
                    if(ukupanBrojKontaktiranihOsoba < 0 || ukupanBrojKontaktiranihOsoba > i) {
                        System.out.println("Neispravan unos kontaktiranih osoba, unesite ponovno");
                        continue;
                    }
                    break;
                }

                if(ukupanBrojKontaktiranihOsoba > 0) {

                    kontaktiraneOsobe = new Osoba[ukupanBrojKontaktiranihOsoba];

                    for(int j = 0; j < ukupanBrojKontaktiranihOsoba; ++j) {
                        for(int k = 0; k < i; ++k) {
                            System.out.println((k+1) + ". " + osobe[k].getIme() + " " + osobe[k].getPrezime());
                        }
                        brojKontaktiraneOsobe = Integer.parseInt(scanner.nextLine());
                        brojKontaktiraneOsobe--;

                        if(brojKontaktiraneOsobe < 0 || brojKontaktiraneOsobe >= i) {
                            System.out.println("Neispravan unos broja kontaktirane osobe. Unesite ponovno.");
                            j--;
                            continue;
                        }

                        if(j > 0) {
                            boolean samePersonFlag = false;
                            for(int k = 0; k < j; ++k) {
                                if(osobe[brojKontaktiraneOsobe].equals(kontaktiraneOsobe[k])) {
                                    samePersonFlag = true;
                                }
                            }
                            if(samePersonFlag) {
                                System.out.println("Osoba koju ste unesli je duplikat. Molim Vas unesite ponovno.");
                                j--;
                                continue;
                            }
                        }
                        kontaktiraneOsobe[j] = osobe[brojKontaktiraneOsobe];
                    }
                    osobe[i] = new Osoba.Builder()
                            .ime(ime)
                            .prezime(prezime)
                            .starost(starost)
                            .zupanija(zupanija)
                            .zarazenBolescu(bolest)
                            .kontaktiraneOsobe(kontaktiraneOsobe)
                            .build();
                } else {
                    osobe[i] = new Osoba.Builder()
                            .ime(ime)
                            .prezime(prezime)
                            .starost(starost)
                            .zupanija(zupanija)
                            .zarazenBolescu(bolest)
                            .build();
                }

            }
        }
    }

    private static void ispisOsoba(Osoba[] osobe) {
        for(int i = 0; i < osobe.length; ++i) {
            ispisOsobe(osobe[i]);
        }
    }

    private static void ispisOsobe(Osoba osoba) {
        System.out.println("Ime i prezime: " + osoba.getIme() + " " + osoba.getPrezime());
        System.out.println("Starost: " + osoba.getStarost());
        System.out.println("Zupanija prebivalista: " + osoba.getZupanija().getNaziv());
        System.out.println("Zarazen bolescu: " + osoba.getZarazenBolescu().getNaziv());
        if(osoba.getKontaktiraneOsobe() == null) {
            System.out.println("Nema kontaktiranih osoba.");
        } else {
            for(int i = 0; i < osoba.getKontaktiraneOsobe().length; ++i) {
                ispisKontaktiraneOsobe(osoba.getKontaktiraneOsobe()[i]);
            }

        }
    }

    private static void ispisKontaktiraneOsobe(Osoba osoba) {
        System.out.println(osoba.getIme() + " " + osoba.getPrezime());
    }


}
