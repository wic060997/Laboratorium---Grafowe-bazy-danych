
package neo4j;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.swing.plaf.IconUIResource;
import java.util.Map;
import java.util.Scanner;


public class KinoMain {

    static Scanner scanner;
    static boolean koniec = false;
    public static SeansService seansService;
    public static SalaService salaService;

    private static void addSala(){
        System.out.println("Podaj nazwę sali:");
        String nazwa = scanner.nextLine();
        Sala sala = new Sala(nazwa);
        salaService.createOrUpdate(sala);
    }

    private static void addSeans(){
        System.out.println("Podaj nazwę seansu:");
        String nazwa = scanner.nextLine();
        Seans seans = new Seans(nazwa);
        seansService.createOrUpdate(seans);
    }

    private static void SeansSala(){
        System.out.println("Podaj id seansu:");
        Long id =  Long.parseLong(scanner.nextLine());
        Seans s = seansService.read(id);
        System.out.println("Podaj id sali:");
        Long ids =  Long.parseLong(scanner.nextLine());
        Sala ss = salaService.read(ids);
        ss.addSeans(s);
    }

    private static  void ReadAllSeans(){
        for(Seans s: seansService.readAll()){
            System.out.println(s);
        }
    }

    private static  void ReadRelationSeans(){
        for(Map<String, Object> map : seansService.getSeansRelationships())
            System.out.println(map);
    }

    private static void UpdateSeans(){
        System.out.println("Podaj id seansu:");
        Long id =  Long.parseLong(scanner.nextLine());
        Seans s = seansService.read(id);
        System.out.println(s);
        s.setNazwaSeans(scanner.nextLine());
        seansService.createOrUpdate(s);
    }

    private static void UpdateSala(){
        System.out.println("Podaj id sali:");
        Long id =  Long.parseLong(scanner.nextLine());
        Sala s = salaService.read(id);
        System.out.println(s);
        s.setNazwaSali(scanner.nextLine());
        salaService.createOrUpdate(s);
    }

    private static void DeleteSeans(){
        System.out.println("Podaj id seansu:");
        Long id =  Long.parseLong(scanner.nextLine());
        seansService.delete(id);
    }

    private static void DeleteSala(){
        System.out.println("Podaj id sala:");
        Long id =  Long.parseLong(scanner.nextLine());
        salaService.delete(id);
    }

    static private void showMenu() {
        System.out.println("\n**************  MENU:  ***************");
        System.out.println("1 - Dodaj sale ");
        System.out.println("2 - Dodaj seans ");
        System.out.println("3 - Przypisz seans do sali ");
        System.out.println("4 - Wyświetl wszystkie seanse ");
        System.out.println("5 - Wyświetl występujace relacje seansu ");
        System.out.println("6 - Aktualizuj sale ");
        System.out.println("7 - Aktualizuj seans ");
        System.out.println("8 - Usuń seans ");
        System.out.println("9 - Usuń sala ");
        System.out.println("0 - WYJŚCIE\n");
    }

    static private void getWyborMenu() {
        int choice = -1;
        do {
            System.out.println("Podaj wybór:");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 0 || choice > 9) {
                System.out.println("Brak takiej opcji!");
            }
        } while (choice < 0 || choice > 9);
        akcja(choice);
    }

    static private void akcja(int wybor) {
        switch (wybor) {
            case 1:
                addSala();
                break;
            case 2:
                addSeans();
                break;
            case 3:
                SeansSala();
                break;
            case 4:
                ReadAllSeans();
                break;
            case 5:
                ReadRelationSeans();
                break;
            case 6:
                UpdateSala();
                break;
            case 7:
                UpdateSeans();
                break;
            case 8:
                DeleteSeans();
                break;
            case 9:
                DeleteSala();
                break;
            case 0:
                koniec = true;
                break;
            default:
                System.out.println("BŁĄD");
        }
    }

    static void menu() {
        while (!koniec) {
            showMenu();
            getWyborMenu();
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "neo4jpassword").build();
        SessionFactory sessionFactory = new SessionFactory(configuration,"neo4j");

        scanner = new Scanner(System.in);
        Session session = sessionFactory.openSession();
        session.purgeDatabase();

         seansService = new SeansService(session);
         salaService = new SalaService(session);
         menu();

         sessionFactory.close();
    }
}
