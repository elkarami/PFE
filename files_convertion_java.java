import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static String replaceSeparatorsExtract1(String string) {
        string = string.replace(",", "");
        string = string.replace("\"", "");
        string = string.replace("    ", " ");
		String newString = "C-" + string.replaceAll(";$", "").replaceAll(";(?=[^;]*$)", ",");
        newString = newString.replaceAll(";$", "");
        newString = newString.replaceAll(";(?=[^;]*$)", ",");
        newString = newString.replace("                            1                                  ", ",001,");
        return newString.replace("\n", ",     \n");
    }

    public static String replaceSeparatorsExtract2(String string) {
        String newString = "C-" + string.replace(";", ",");
        newString = newString.replace("                            1                                  ", ",001,");
        newString = newString.replaceFirst("                                ", "");
        return newString.replace(";", ",");
    }

    public static String replaceSeparatorsExtract3(String string) {
        String newString = "C-" + string.replace(";", ",");
        newString = newString.replace("                            1                                  ", ",001,");
        newString = newString.replaceFirst("                                ", "");
        newString = newString.replace(";", ",");
        int index = newString.indexOf("MAD") + 3;
        return newString.substring(0, index) ;
    }

    public static void handleFile(String inputFileName, String outputFileName) {
        List<String> modifiedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (inputFileName.equals("EXTRACT1")) {
                    modifiedLines.add(replaceSeparatorsExtract1(line));
                } else if (inputFileName.equals("EXTRACT2")) {
                    modifiedLines.add(replaceSeparatorsExtract2(line));
                } else if (inputFileName.equals("EXTRACT3")) {
                    modifiedLines.add(replaceSeparatorsExtract3(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }
        try (FileWriter writer = new FileWriter(outputFileName)) {
            for (String modifiedLine : modifiedLines) {
                writer.write(modifiedLine + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            return;
        }
        System.out.printf("La conversion du fichier est terminée, %d lignes modifiées. Veuillez consulter le fichier de sortie: %s%n",
                modifiedLines.size(), outputFileName);
    }

    public static void main(String[] args) {
        String inputFileName = "EXTRACT1";
        String outputFileName = "EXTRACT1.csv";
        handleFile(inputFileName, outputFileName);
		
		inputFileName = "EXTRACT2";
        outputFileName = "EXTRACT2.csv";
        handleFile(inputFileName, outputFileName);
		
		inputFileName = "EXTRACT3";
        outputFileName = "EXTRACT3.csv";
        handleFile(inputFileName, outputFileName);
    }
}