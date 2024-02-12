package components;

import constants.Constants;
import utils.Utils;

import java.util.Scanner;

public class MainBus {
    private String busNameR;

    public void mainBusOptions() {
        String currentDir = System.getProperty("user.dir"); // Get the current working directory
        String folderPath = currentDir + "/src/buses";
        String ticketPath = currentDir + "/src/tickets";
        Bus newBus = new Bus(folderPath);
        Ticket ticket = new Ticket(ticketPath);

        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            Utils.boxFormating("_", Constants.BOX_NUMBER);
            Utils.formatMsg(Constants.OPCIONES_TITULO, Constants.BOX_NUMBER, true );
            Utils.formatMsg(Constants.MOSTRAR_BUSES, Constants.BOX_NUMBER, true );
            Utils.formatMsg(Constants.CREAR_BUS, Constants.BOX_NUMBER, true );
            Utils.formatMsg(Constants.AVAILABLE_SEATS_BY_BUS, Constants.BOX_NUMBER, true );
            Utils.formatMsg(Constants.SELL_NEW_SEAT, Constants.BOX_NUMBER, true );
            Utils.formatMsg(Constants.SHOW_TICKET_INFO, Constants.BOX_NUMBER, true);
            Utils.formatMsg(Constants.CANCEL_TICKET, Constants.BOX_NUMBER, true);
            Utils.formatMsg(Constants.MENU_PRINCIPAL, Constants.BOX_NUMBER, true);
            Utils.boxFormating("_", Constants.BOX_NUMBER);
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Utils.showAllBuses(folderPath);
                    break;
                case "2":
                    newBus.createNewBus();
                    break;
                case "3":
                    newBus.showBusContent("buscar:");
                    Utils.boxFormating("_", Constants.BOX_NUMBER);
                    break;
                case "4":
                    this.busNameR = newBus.sellNewBusSeat();
                    break;
                case "5":
                    boolean isValid = ticket.searchTicketByName();
                    if(isValid){
                        Utils.formatMsg("| Ticket no encontrado o nombre invalido", Constants.BOX_NUMBER, true);
                    }else {
                        ticket.showTicketInformation();
                        Utils.boxFormating("_", Constants.BOX_NUMBER);
                    }
                    break;
                case "6":
                    boolean isValidDelete = ticket.searchTicketByName();
                    if(isValidDelete){
                        Utils.formatMsg("| Ticket no encontrado o nombre invalido", Constants.BOX_NUMBER, true);
                    }else {
                        ticket.deleteTicketInformation(folderPath, this.busNameR);
                        Utils.boxFormating("_", Constants.BOX_NUMBER);
                    }
                    break;
                case "0":
                    System.out.println("...");
//                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Opcion incorrecta, ingresa una opcion valida.");
                    break;
            }
        }
    }
}
