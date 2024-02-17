package components;

import constants.Constants;
import utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Ticket {
    // Attributes
    private final String folderPath;
    private String ticketname;
    private String ticketNameToShow;
    private String seatCodeToUpdate;
    private String lastLine;
    // Constructor
    public Ticket(String folderPath) {
        this.folderPath = folderPath;
    }

    // Getters and setters


    public CustomReturn searchTicketByName(){
        Scanner scanner = new Scanner(System.in);
        File folder = new File(this.folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return new CustomReturn(true, 0, "| Aun no hay tickets registrados.");
        }
        Utils.formatMsg("| Ingrese el primer nombre del usuario: ", Constants.BOX_NUMBER, true);
        Utils.boxFormating("_", Constants.BOX_NUMBER);
        String ticketName = scanner.nextLine();

        boolean isTicketNotFound = true; // Initialize as true
        assert files != null;
        for (File file : files) {
            String tempFileName = file.getName();
            String[] parts = tempFileName.split("-");
            if (parts.length > 0) {
                if (Objects.equals(parts[0], ticketName)) {
                    this.ticketNameToShow = tempFileName;
                    this.seatCodeToUpdate = parts[1];
                    isTicketNotFound = false; // Set to false if a matching ticket is found
                    break; // Exit the loop early once a match is found
                }
            }
        }
//        return isTicketNotFound;
        return new CustomReturn(isTicketNotFound, 1, "");
    }

    public void deleteTicketInformation( String busFilePath, String busName){
        Scanner scanner = new Scanner(System.in);
        Utils.formatMsg("| Una vez eliminado no se puede recuperar: ", Constants.BOX_NUMBER, true);
        Utils.formatMsg("| Si [1] No [0]: ", Constants.BOX_NUMBER, true);
        Utils.boxFormating("_", Constants.BOX_NUMBER);
        String ticketName = scanner.nextLine();
        switch (ticketName) {
            case "1":
                File folder = new File(this.folderPath);
                File[] files = folder.listFiles();
                File fileToDelete = new File(folderPath, ticketNameToShow);
                String[] codeToUpdate = this.seatCodeToUpdate.split("\\.");
                CustomReturn data = showTicketInfoToGetBusName();
                String[] findBusData = data.line.split(" ");
                Utils.updateSeatsValue(busFilePath + "/" + findBusData[2] + ".txt", codeToUpdate[0], false);
                if (fileToDelete.delete()) {
                    Utils.formatMsg("| Ticket eliminado con exito!", Constants.BOX_NUMBER, true);

                } else {
                    System.out.println();
                    Utils.formatMsg("Ooops algo salio mal.", Constants.BOX_NUMBER, true);
                }
                break;
            case "0":
                // No action needed, user chose not to delete
                break;
            default:
                // Invalid input, handle it accordingly (e.g., display a message)
                Utils.formatMsg("| Entrada no válida. Por favor, ingrese 1 para eliminar o 0 para cancelar.", Constants.BOX_NUMBER, true);
                break;
        }
    }

    public CustomReturn showTicketInfoToGetBusName(){
        File folder = new File(this.folderPath);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            return new CustomReturn(true, 0, "");
        }
        String lastLineLocal = null;
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(this.ticketNameToShow)) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;

                        while ((line = reader.readLine()) != null) {
//                            Utils.formatMsg( "| " +line, Constants.BOX_NUMBER, true);
                            lastLineLocal = line;
                        }
                        if (lastLineLocal != null) {
                            this.lastLine = lastLineLocal;
//                            Utils.formatMsg("| Last line: " + lastLine, Constants.BOX_NUMBER, true);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//        return lastLineLocal;
        return new CustomReturn(true, 1, lastLine);
    }

    public void showTicketInformation(){
        File folder = new File(this.folderPath);
        File[] files = folder.listFiles();


        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(this.ticketNameToShow)) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        String lastLineLocal = null;
                        while ((line = reader.readLine()) != null) {
                            Utils.formatMsg( "| " +line, Constants.BOX_NUMBER, true);
                            lastLineLocal = line;
                        }
                        if (lastLineLocal != null) {
                            this.lastLine = lastLineLocal;
//                            Utils.formatMsg("| Last line: " + lastLine, Constants.BOX_NUMBER, true);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
