import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner alternativ = new Scanner(System.in);

        do {

            try {
                SökaKund sökaKund = new SökaKund();
                KundHantering kundHantering = new KundHantering();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Skriv in personnummer eller namn: ");

                String input = scanner.nextLine();
                sökaKund.sökKundOchSkrivUt(kundHantering, input);


            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();

            }
            System.out.println("Vill du söka efter ny person? Skriv Ja/Nej");
        } while (alternativ.next().equalsIgnoreCase("JA"));
        alternativ.close();

    }
}
