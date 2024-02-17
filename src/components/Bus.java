package components;

import constants.Constants;
import utils.Utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bus {
    private String name;
    private final String folderPath;
    private String busFileName;
    private final List<String> seatsTaken = new ArrayList<>();

    // Constructor with name parameter
    public Bus(String folderPath) {
        this.folderPath = folderPath;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    public String sellNewBusSeat(){
        File folder = new File(this.folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            Utils.formatMsg("| Aun no hay buses registrados.", Constants.BOX_NUMBER, true);
            return null; // Exit the method
        }

        Scanner scanner = new Scanner(System.in);
        askFileName("vender");
        File busFile = new File(this.folderPath + "/" + this.busFileName + ".txt");
        if (!busFile.exists()) {
            Utils.formatMsg("El nombre del bus es invalido o no existe.", Constants.BOX_NUMBER, true);
            return null;
        }

        boolean isInvalidInput = true;
        while (isInvalidInput) {
            showBusContentData(busFile);
            Utils.boxFormating("_", Constants.BOX_NUMBER);
            Utils.formatMsg("| Ingrese el numero de asiento", Constants.BOX_NUMBER, true);
            String seatName = scanner.nextLine();
            boolean notIncluded = true; // Set notIncluded to true initially
            for (String value : Constants.DEFAULT_VALUES) {
                if (seatName.equals(value)) { // If the input matches any value in DEFAULT_VALUES
                    notIncluded = false; // Set notIncluded to false and break out of the loop
                    break;
                }
            }

            if (notIncluded) {
                Utils.formatMsg("| Por favor ingresa un asiento valido", Constants.BOX_NUMBER, true);
            } else { // If the input is valid
                //! Validar que sea un asiento que este disponible
                boolean isSeatTaken = false;
                for (String validInput : this.seatsTaken) {
                    if (seatName.equals(validInput)) {
                        isSeatTaken = true;
                        break;
                    }
                }
                if (isSeatTaken) {
                    Utils.formatMsg("| Por favor selecciona un asiento disponible", Constants.BOX_NUMBER, true);
                } else {
                    createTicket(seatName);
                    isInvalidInput = false; // Exit the loop

                }
            }
        }
        return this.busFileName;
    }
    public void createTicket(String seatCode){
        Scanner scanner = new Scanner(System.in);
        Utils.formatMsg("| Por favor ingrese los siguientes datos", Constants.BOX_NUMBER, true);
        Utils.formatMsg("| Nombre: ", Constants.BOX_NUMBER, true);
        String userName = scanner.nextLine();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        boolean isWindowSeat = false;
        float ticketPrice = 0f;

        for (String value : Constants.WINDOW_SEATS) {
            if (seatCode.equals(value)) { // If the input matches any value in DEFAULT_VALUES
                isWindowSeat = true; // Set notIncluded to false and break out of the loop
                break;
            }
        }
        if(isWindowSeat) {
            ticketPrice = Constants.WINDOW_PRICE;
        }else {
            ticketPrice = Constants.REGULAR_PRICE;
        }

        User newTicketUser = new User(userName, seatCode, ticketPrice, formattedDate, isWindowSeat, this.busFileName);
        newTicketUser.createTicketFile();
        Utils.updateSeatsValue(this.folderPath + "/" + this.busFileName + ".txt", seatCode, true);
    }

    public void askFileName(String type){
        Scanner scanner = new Scanner(System.in);
        Utils.formatMsg("| Ingrese el nombre del bus a "+ type, Constants.BOX_NUMBER, true);
        String newBusName = scanner.nextLine();
        this.busFileName = newBusName.replaceAll("[^a-zA-Z0-9]", "");
        this.busFileName = this.busFileName.toLowerCase();
    }

    public void showBusContent(String type){
        File folder = new File(this.folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            Utils.formatMsg("| Aun no hay buses registrados.", Constants.BOX_NUMBER, true);
            return; // Exit the method
        }
        askFileName(type);
        Utils.validateFilePath(folder);
        Utils.boxFormating("_", Constants.BOX_NUMBER);

        File busFile = new File(this.folderPath + "/" + this.busFileName + ".txt");
        if (!busFile.exists()) {
            Utils.formatMsg("| El bus con ese nombre no existe.", Constants.BOX_NUMBER, true);
        }else {
            showBusContentData(busFile);
        }
    }

    private void showBusContentData(File busFile){
        this.seatsTaken.clear();
        Utils.formatMsg("| ", Constants.BOX_NUMBER, true);
        Utils.boxFormating("+", Constants.BUS_NUMBER);
        displayFileContent(busFile);
        Utils.boxFormating("+", Constants.BUS_NUMBER);
//        System.out.println("Los asientos disponibles estan marcados con Verde, los NO disponibles con Rojo");
        Utils.formatMsg("| Los asientos disponibles estan marcados con Verde, los NO disponibles con Rojo", Constants.BOX_NUMBER, true);
        Utils.formatMsg("| ", Constants.BOX_NUMBER, true);
    }

    private void displayFileContent(File folder) {
        try (BufferedReader reader = new BufferedReader(new FileReader(folder))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String backgroundColorCode = null;
                if (parts.length > 0) {
                    if(count == 0 || count == 10 || count == 30){
                        System.out.print("|     ");
                    }
                    if( count == 20){
                        Utils.formatMsg("|", Constants.BUS_NUMBER, true);
                    }
                    if( count == 20){
                        System.out.print("|     ");
                    }
                    if (parts[2].equals("false")) {
                        backgroundColorCode = "\u001B[42;1m"; // Green background
                    }
                    if( parts[2].equals("true")){
                        this.seatsTaken.add(parts[0]);
                        backgroundColorCode = "\u001B[41;1m";
                    }
                    System.out.print(backgroundColorCode + "  |" + parts[0] + "|  \u001B[0m");

                    count++;
                    if (count % 10 == 0) {
                        System.out.println("              |");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addContentToFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        int rows = 4;
        int columns = 10;
        String[][] matrix = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            char rowLabel = (char) ('a' + i);
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = rowLabel + "" + j;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                writer.write(matrix[i][j] + " taken: " + false + "\n");
            }
        }
        writer.close();
    }
    public void createNewBus(){
        Scanner scanner = new Scanner(System.in);
        File folder = new File(this.folderPath);
        Utils.validateFilePath(folder);

        System.out.println("Ingrese el nombre del bus:");
        String newBusName = scanner.nextLine();

        String cleanedInput = newBusName.replaceAll("[^a-zA-Z0-9]", "");
        cleanedInput = cleanedInput.toLowerCase();
        setName(cleanedInput);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(cleanedInput + ".txt")) {
                    Utils.formatMsg("Un bus con ese nombre ya existe, ingrese uno nuevo.", Constants.BOX_NUMBER, true);
                    return;
                }
            }
        }
        File newFile = new File(this.folderPath + "/" + cleanedInput + ".txt");
        try {
            if (newFile.createNewFile()) {
                // TODO: here xD
                addContentToFile(newFile);
                System.out.println("Bus creado con exito!!");
            } else {
                System.out.println("No se pudo crear el bus.");
            }
        } catch (IOException e) {
            System.out.println("Algo salio mal al crear el bus.");
            e.printStackTrace();
        }
    }
}
