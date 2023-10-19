import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class SökaKund {

    // Metod för att söka och skriva ut kundinformation samt spara gymbesöksdata om kunden är aktiv.
    public void sökKundOchSkrivUt(KundHantering kundHantering, String input) {

        //  Sökvägen till filen som innehåller kundinformation.
        File filNamn = new File("C:\\Users\\samii\\Downloads\\GymMember.txt");

        // Läsa från filen och hantera kundinformationen.
        List<String> lines = kundHantering.läsaFrånFil(String.valueOf(filNamn));
        boolean hittaKund = false;

        // Går igenom kundinformationen och jämför med inputen
        for (int i = 0; i < lines.size(); i += 2) {
            String kundInfo = lines.get(i);
            String senastBetaldMedlemskap = lines.get(i + 1);
            String[] kundDetaljer = kundInfo.split(", ");
            String namnPåKund = kundDetaljer[1];
            String personNummerKund = kundDetaljer[0];

            LocalDate datumPåSenastBetaldMedlemskap = LocalDate.parse(senastBetaldMedlemskap);
            LocalDate datumIdag = LocalDate.now();

            // Om kunden hittas, skriv ut kundinformation
            if (namnPåKund.equals(input) || personNummerKund.equals(input)) {
                String kategori = kundHantering.getHanteraKundKategori(datumPåSenastBetaldMedlemskap, datumIdag);
                System.out.println("Namn: " + namnPåKund);
                System.out.println("Kategori: " + kategori);

                // Spara gymbesöksdata för kunden om kunden är en nuvarande medlem.
                String sparaGymBesök = personNummerKund + ", " + namnPåKund + ", " + LocalDate.now() + "\n";
                kundHantering.sparaMedlemsGymBesök("C:\\Users\\samii\\OneDrive\\Dokument\\PtTracker.txt", sparaGymBesök, datumPåSenastBetaldMedlemskap, datumIdag);

                hittaKund = true;
                break;
            }
        }
        if (!hittaKund) {
            System.out.println("Obehörig person");
        }
    }
}
