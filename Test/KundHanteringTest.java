import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KundHanteringTest {

    @Test
    void läsaFrånFil() {

        // Testar metoden läsaFrånFil i klassen KundHantering

        KundHantering kundHantering = new KundHantering();

        List<String> lines = kundHantering.läsaFrånFil("C:\\Users\\samii\\Downloads\\GymMember.txt");
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
    }

    @Test
    void sparaMedlemsGymBesök() {

        // Testar om det går att läsa innehållet från en fil och kontrollera att listan inte är tom

        KundHantering kundHantering = new KundHantering();

        LocalDate senastBetaldMedlemskap = LocalDate.now();
        LocalDate dagensDatum = LocalDate.now();
        String namnPåPtFil = "C:\\Users\\samii\\OneDrive\\Skrivbord\\javascript läxor\\IdeaProjects\\Best Gym Ever\\Test\\TestFil";
        String gymBesök = "123456789, Test Kund, " + LocalDate.now() + "\n";
        kundHantering.sparaMedlemsGymBesök(namnPåPtFil, gymBesök, senastBetaldMedlemskap, dagensDatum);

        // Läsa från filen och kontrollera om innehållet inte är tomt
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(namnPåPtFil))) {
            String line;
            line = bufferedReader.readLine();
            assertNotNull(line, "Innehållet i filen är tomt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getHanteraKundKategori() {

        // Testar metoden getHanteraKundKategori i klassen KundHantering

        KundHantering kundHantering = new KundHantering();
        LocalDate senastBetaldMedlemskapsDatum = LocalDate.now().minusYears(1).plusDays(1);
        LocalDate dagensDatum = LocalDate.now();
        String kategori = kundHantering.getHanteraKundKategori(senastBetaldMedlemskapsDatum, dagensDatum);
        assertEquals("Nuvarande medlem", kategori);

        senastBetaldMedlemskapsDatum = LocalDate.now().minusYears(2);
        kategori = kundHantering.getHanteraKundKategori(senastBetaldMedlemskapsDatum, dagensDatum);
        assertEquals("Föredetta medlem", kategori);
    }
}
