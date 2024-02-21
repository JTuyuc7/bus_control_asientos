import components.MainBus;
import constants.Constants;
import utils.Utils;

import java.util.Scanner;

/**
 * This class represents the main entry point of the program.
 */
public class Main {

    /**
     * The main method of the program.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {

        MainBus busoptions = new MainBus(); // Creating an instance of MainBus to handle bus options

        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to read user input
        String choice; // Variable to store user choice

        do {
            Utils.boxFormating("_", Constants.BOX_NUMBER); // Formatting box
            Utils.formatMsg(Constants.OPCIONES_TITULO, Constants.BOX_NUMBER, true); // Formatting options title
            Utils.formatMsg(Constants.ESTADO_ASIENTOS, Constants.BOX_NUMBER, true); // Formatting seat status
            Utils.formatMsg(Constants.SALIR,Constants.BOX_NUMBER, true); // Formatting exit option
            Utils.boxFormating("_", Constants.BOX_NUMBER); // Formatting box

            choice = scanner.nextLine(); // Reading user input

            // Handling user choice
            switch (choice) {
                case "1":
                    busoptions.mainBusOptions(); // Invoking method to handle main bus options
                    break;
                case "2":
                    System.out.println("Otras opciones del menu"); // Placeholder for other menu options
                    break;
                case "0":
                    System.out.println("Exiting the program..."); // Exiting message
                    break;
                default:
                    System.out.println("Invalid choice, please try again."); // Error message for invalid input
                    break;
            }
        } while (!choice.equalsIgnoreCase("0")); // Loop until user chooses to exit

        scanner.close(); // Closing the scanner object
    }
}
