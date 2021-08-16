package hr.java.covidportal.main;

import hr.java.covidportal.model.Bolest;
import hr.java.covidportal.model.Simptom;
import hr.java.covidportal.model.VrijednostSimptoma;
import hr.java.covidportal.model.Zupanija;

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

        zupanije = unosZupanija(zupanije, scanner, BROJ_ZUPANIJA);

        simptomi = unosSimptoma(simptomi, scanner, BROJ_SIMPTOMA);

        bolesti = unosBolesti(bolesti, simptomi, scanner, BROJ_BOLESTI);

        bolesti.stream().forEach(System.out::println);

        scanner.close();
    }

    private static List<Zupanija> unosZupanija(List<Zupanija> zupanije, Scanner scanner, final int limit) {
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
        return zupanije;
    }

    private static List<Simptom> unosSimptoma(List<Simptom> simptomi, Scanner scanner, final int limit) {
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

            simptomi.add(new Simptom(naziv, vrijednostSimptoma));
        }

        return simptomi;
    }

    private static List<Bolest> unosBolesti(List<Bolest> bolesti, List<Simptom> simptomi, Scanner scanner, final int limit) {
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

        return bolesti;
    }

}
