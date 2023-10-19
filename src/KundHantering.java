import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KundHantering {

    // Metod för att läsa från en  fil och returnera innehållet som en lista av strängar.
    public List<String> läsaFrånFil(String namnPåFil) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(namnPåFil))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Metod för att skriva till fil och spara gymbesök om medlemskapet är aktivt.
    public void sparaMedlemsGymBesök(String namnPåPtFil, String gymBesök, LocalDate senastBetaldMedlemskap, LocalDate dagensDatum) {
        if (dagensDatum.minusYears(1).isBefore(senastBetaldMedlemskap)) {
            try (FileWriter fileWriter = new FileWriter(namnPåPtFil,true)) {
                fileWriter.write(gymBesök);
                System.out.println("Medlemens gymbesök sparad: " + gymBesök);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Metod för att hantera kundens medlemskapskategori baserat på datum.
    public String getHanteraKundKategori(LocalDate senasteBetalningsDatum, LocalDate dagensDatum) {
        if (dagensDatum.minusYears(1).isBefore(senasteBetalningsDatum)) {
            return "Nuvarande medlem";
        } else {
            return "Föredetta medlem";
        }
    }
}