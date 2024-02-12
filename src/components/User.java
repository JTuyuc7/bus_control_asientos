package components;
import constants.Constants;
import utils.Utils;

import java.io.*;
import java.util.Date;

public class User {
    private final String name;
    private final String seatCode;
    private final boolean isWindowSeat;
    private double price;
    private String dateOfPurchase;
    private String busName;

    public User(String name, String seatCode, double price, String dateOfPurchase, boolean isWindowSeat, String busName) {
        this.name = name;
        this.seatCode = seatCode;
        this.price = price;
        this.dateOfPurchase = dateOfPurchase;
        this.isWindowSeat = isWindowSeat;
        this.busName = busName;
    }

    public void createTicketFile(){
        String currentDir = System.getProperty("user.dir"); // Get the current working directory
        String ticketsDirectory = currentDir + "/src/tickets";
        File folder = new File(ticketsDirectory);
        Utils.validateFilePath(folder);

//        Utils.checkIfFileNmaeExists(folder, "Ingrese un nombre nuevo", this.name + "-"+this.seatCode);

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(this.name+"-"+this.seatCode + ".txt")) {
                    Utils.formatMsg("Ingresa un nombre distinto", Constants.BOX_NUMBER, true);
                    return;
                }
            }
        }

        File newFile = new File(ticketsDirectory + "/" + this.name.replace(" ", "-") + "-" +this.seatCode + ".txt");

        try {
            if (newFile.createNewFile()) {
                // TODO: here xD
                addTicketContent(newFile);
                System.out.println("Ticket creado con exito!!");
            } else {
                System.out.println("No se pudo crear el bus.");
            }
        } catch (IOException e) {
            System.out.println("Algo salio mal al crear el bus.");
            e.printStackTrace();
        }
    }

    public void addTicketContent(File file) throws IOException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Pasajero: " + this.name + "\n");
            writer.write("Tipo asiento: " + (this.isWindowSeat ? "Ventana" : "Pasillo") + "\n");
            writer.write("Precio: " + this.price + "\n");
            writer.write("Fecha de compra: " + this.dateOfPurchase + "\n");
            writer.write("Numero de asiento: " + this.seatCode + "\n");
            writer.write("Bus designado: " + this.busName + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Algo salio mal al crear el ticket.");
            e.printStackTrace();
        }
    }

//    public void updateSeatsValue(String filePath, String seatCode, boolean valueToUpdate){
//        try {
//            File inputFile = new File(filePath);
//            File tempFile = new File("temp.txt");
//
//            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (line.startsWith(seatCode)) {
//                    // Update the line with the new value
//                    line = seatCode + " taken: " + valueToUpdate;
//                }
//                writer.write(line + System.getProperty("line.separator"));
//            }
//            reader.close();
//            writer.close();
//
//            // Delete the original file
//            if (!inputFile.delete()) {
//                System.out.println("Could not delete file");
//                return;
//            }
//
//            // Rename the new file to the filename the original file had
//            if (!tempFile.renameTo(inputFile)) {
//                System.out.println("Could not rename file");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // Getters and setters (not shown for brevity)
}