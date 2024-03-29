package utils;

import constants.Constants;

import java.io.*;
import java.util.Scanner;

/**
 * This class provides utility methods for various tasks in the program.
 */
public class Utils {

    /**
     * Validates if the provided file path is a directory.
     * @param folder The file object representing the directory.
     */
    public static void validateFilePath(File folder){
        if (!folder.isDirectory()) {
            System.out.println("Ruta Invalida");
        }
    }

    /**
     * Checks if a file with the provided name exists in the given directory.
     * @param folder The directory where to search for the file.
     * @param msg The message to display if the file exists.
     * @param fileName The name of the file to search for.
     */
    public static void checkIfFileNmaeExists(File folder, String msg, String fileName){
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName + ".txt")) {
                    System.out.println(msg);
                    return; // Return to stop further execution
                }
            }
        }
    }

    /**
     * Displays a list of all buses in the specified directory.
     * @param folderPath The path to the directory containing bus files.
     */
    public static void showAllBuses(String folderPath) {
        File folder = new File(folderPath);
        validateFilePath(folder);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            boxFormating("_", Constants.BOX_NUMBER);
            formatMsg("| Aun no hay buses creados", Constants.BOX_NUMBER, true);
            boxFormating("_", Constants.BOX_NUMBER);
            return;
        }
        boxFormating("_", Constants.BOX_NUMBER);
        formatMsg("| Listado de buses", Constants.BOX_NUMBER, true);
        boxFormating("_", Constants.BOX_NUMBER);
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                formatMsg("| " + file.getName(), Constants.BOX_NUMBER, true);
            }
        }
        boxFormating("_", Constants.BOX_NUMBER);
    }

    /**
     * Checks if a file with the provided name exists in the given directory.
     * @param folderPath The path to the directory where to search for the file.
     */
    public static void checIfFileExists(String folderPath){
        Scanner scanner = new Scanner(System.in);
        File folder = new File(folderPath);
        validateFilePath(folder);
        boxFormating("_", Constants.BOX_NUMBER);
        formatMsg("| Ingrese el nombre del bus a buscar:", Constants.BOX_NUMBER, true);

        String newBusName = scanner.nextLine();
        String cleanedInput = newBusName.replaceAll("[^a-zA-Z0-9]", "");
        cleanedInput = cleanedInput.toLowerCase();

        File busFile = new File(folderPath + "/" + cleanedInput + ".txt");
        if (!busFile.exists()) {
            formatMsg("| El bus con ese nombre no existe.", Constants.BOX_NUMBER, true);
        } else {
            formatMsg("| si existe el archivo.", Constants.BOX_NUMBER, true);
            formatMsg("| *", Constants.BOX_NUMBER, true);
        }
        boxFormating("_", Constants.BOX_NUMBER);
    }

    /**
     * Builds a container for displaying buses.
     */
    public static void buildBusContainer(){
        boxFormating("+", Constants.BUS_NUMBER);
        formatBusContainer("* ", Constants.BUS_NUMBER, false, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, false, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, false, true);
        formatBusContainer("* ", Constants.BUS_NUMBER, true, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, true, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, true, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, true, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, true, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, false, true);
        formatBusContainer("* ", Constants.BUS_NUMBER, false, false);
        formatBusContainer("* ", Constants.BUS_NUMBER, false, false);
        boxFormating("+", Constants.BUS_NUMBER);

    }

    /**
     * Formats a bus container for display.
     * @param text The text to display in the container.
     * @param spaces The number of spaces for formatting.
     * @param isLastOne Indicates if it's the last container.
     * @param isHeader Indicates if it's a header container.
     */
    public static void formatBusContainer(String text, Integer spaces, Boolean isLastOne, Boolean isHeader ) {
        int blank_spaces_count;
        blank_spaces_count = spaces - text.length();
        String blank_spaces = " ", repeated, final_bus;
        repeated = new String( new char[blank_spaces_count]).replace("\0", blank_spaces);
        final_bus = new String( new char[8]).replace("\0", blank_spaces);

        String repeatedText = text+repeated + "*";
        if( isHeader ){
            repeatedText += new String( new char[9]).replace("\0", "-");
        }
        if( isLastOne){
            repeatedText = repeatedText + final_bus + "*";
        }
        System.out.println(repeatedText);
    }


    /**
     * Formats a box for display.
     * @param letter The character to repeat for formatting.
     * @param repeatedTimes The number of times to repeat the character.
     */
    public static void boxFormating(String letter, Integer repeatedTimes){
        String repeatedCharacter;
        repeatedCharacter = new String( new char[repeatedTimes]).replace("\0", letter);
        System.out.println(repeatedCharacter);
    }

    /**
     * Formats a message for display.
     * @param text The text to display.
     * @param spaces The number of spaces for formatting.
     * @param isLastOne Indicates if it's the last message to display.
     */
    public static void formatMsg(String text, Integer spaces, Boolean isLastOne ) {
        int blank_spaces_count;
        blank_spaces_count = spaces - text.length();
        String blank_spaces = " ", repeated;
        repeated = new String( new char[blank_spaces_count]).replace("\0", blank_spaces);

        if( isLastOne ){
            System.out.println(text+repeated + "|");
        }else{
            System.out.print(text+repeated + "|");
        }
    }

    /**
     * Updates the value of a seat in the specified file.
     * @param filePath The path to the file containing seat information.
     * @param seatCode The code of the seat to update.
     * @param valueToUpdate The new value for the seat.
     */
    public static void updateSeatsValue(String filePath, String seatCode, boolean valueToUpdate){
        try {
            File inputFile = new File(filePath);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(seatCode)) {
                    // Update the line with the new value
                    line = seatCode + " taken: " + valueToUpdate;
                }
                writer.write(line + System.getProperty("line.separator"));
            }
            reader.close();
            writer.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the new file to the filename the original file had
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
