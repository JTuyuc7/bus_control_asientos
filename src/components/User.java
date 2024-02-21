package components;

import constants.Constants;
import utils.Utils;

import java.io.*;
import java.util.Date;

/**
 * Represents a user who purchases a ticket.
 */
public class User {
    private final String name;
    private final String seatCode;
    private final boolean isWindowSeat;
    private double price;
    private String dateOfPurchase;
    private String busName;

    /**
     * Constructs a User object with the provided information.
     * @param name The name of the user.
     * @param seatCode The code of the seat.
     * @param price The price of the ticket.
     * @param dateOfPurchase The date of purchase.
     * @param isWindowSeat Indicates if the seat is a window seat.
     * @param busName The name of the bus.
     */
    public User(String name, String seatCode, double price, String dateOfPurchase, boolean isWindowSeat, String busName) {
        this.name = name;
        this.seatCode = seatCode;
        this.price = price;
        this.dateOfPurchase = dateOfPurchase;
        this.isWindowSeat = isWindowSeat;
        this.busName = busName;
    }

    /**
     * Creates a ticket file for the user.
     */
    public void createTicketFile(){
        String currentDir = System.getProperty("user.dir"); // Get the current working directory
        String ticketsDirectory = currentDir + "/src/tickets";
        File folder = new File(ticketsDirectory);
        Utils.validateFilePath(folder);

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

    /**
     * Adds content to the ticket file.
     * @param file The file to write the ticket content to.
     * @throws IOException If an I/O error occurs.
     */
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
}
