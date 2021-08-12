package hr.java.covidportal.main;

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

        zupanije = unosZupanija(zupanije, scanner, BROJ_ZUPANIJA);

        zupanije.stream().forEach(System.out::println);

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

}
