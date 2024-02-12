import components.MainBus;
import constants.Constants;
import utils.Utils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MainBus busoptions = new MainBus();

        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            Utils.boxFormating("_", Constants.BOX_NUMBER);
            Utils.formatMsg(Constants.OPCIONES_TITULO, Constants.BOX_NUMBER, true);
            Utils.formatMsg(Constants.ESTADO_ASIENTOS, Constants.BOX_NUMBER, true);
            Utils.formatMsg(Constants.SALIR,Constants.BOX_NUMBER, true);
            Utils.boxFormating("_", Constants.BOX_NUMBER);
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    busoptions.mainBusOptions();
//                    Utils.buildBusContainer();
                    break;
                case "2":
                    System.out.println("You chose Two");
                    break;
                case "0":
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (!choice.equalsIgnoreCase("0"));

        scanner.close();
    }
}