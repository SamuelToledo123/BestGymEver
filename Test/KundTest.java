import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class KundTest {

    // Testar om filen faktiskt existerar på den givna sökvägen.

    @Test
    void testFinnsDetEnFil() {

        File filePath = new File("C:\\Users\\samii\\Downloads\\GymMember.txt");


        assertTrue(filePath.exists(), "Filen existerar inte!!");
    }

    @Test
    void testSkrivaTillFil() {

        // Testar att skriva till en temporär fil och kontrollera antalet rader.

        try {
            String testText = "Test:7502031234,Samuel Toledo, 2023-05-03 \n 8505132345, Johanna Toledo, 2019-12-29";
            Path tempFile = Files.createTempFile("SkrivaTillFilTest", ".txt");
            Files.writeString(tempFile, testText);

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile.toFile()))) {
                List<String> lines = new ArrayList<>();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                }
                assertEquals(2, lines.size());
                System.out.println(tempFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testKundKategori() {

        // Funktion för att få kundens kategori beroende på betalningsdatumet för medlemskapet.

        LocalDate testFastDatum = LocalDate.of(2023,10,18);
        LocalDate betaltMedlemskapInomEttÅr = LocalDate.of(2023, 5, 3);
        LocalDate betaltMedlemskapÖverEttÅrSedan = LocalDate.of(2019, 12, 29);


        String kategoriInomEttÅr = getTestKategori(betaltMedlemskapInomEttÅr,testFastDatum);
        String kategoriÖverEttÅrSedan = getTestKategori(betaltMedlemskapÖverEttÅrSedan,testFastDatum);


        assertEquals("Nuvarande medlem", kategoriInomEttÅr);
        assertEquals("Föredetta medlem", kategoriÖverEttÅrSedan);
    }

    String getTestKategori(LocalDate datumManBetalatMedlemskap, LocalDate testDatum) {
        if (testDatum.minusYears(1).isBefore(datumManBetalatMedlemskap)) {
            return "Nuvarande medlem";
        } else {
            return "Föredetta medlem";
        }
    }
}




