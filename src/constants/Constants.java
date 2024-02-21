package constants;

import java.lang.reflect.Array;

/**
 * This class contains constants used throughout the program.
 */
public class Constants {

    /**
     * Title for the bus menu options.
     */
    public static final String OPCIONES_TITULO = "| Menu de buses: ";

    /**
     * Option to show all buses.
     */
    public static final String MOSTRAR_BUSES = "| 1. Mostrar todos los buses: ";

    /**
     * Option to create a new bus.
     */
    public static final String CREAR_BUS = "| 2. Crear un nuevo Bus:";

    /**
     * Instruction to return to the main menu.
     */
    public static final String MENU_PRINCIPAL = "| Prsiona '0' para regresar al menu principal: ";

    /**
     * Option to view the seat status.
     */
    public static final String ESTADO_ASIENTOS = "| 1 - Ver el estado de los buses: ";

    /**
     * Option to exit the program.
     */
    public static final String SALIR = "| 0 - para salir: ";

    /**
     * Option to view available seats by bus.
     */
    public static final String AVAILABLE_SEATS_BY_BUS = "| 3. Ver asientos disponibles por bus:";

    /**
     * Option to sell a new seat.
     */
    public static final String SELL_NEW_SEAT = "| 4. Generar nueva venta: ";

    /**
     * Option to show ticket information.
     */
    public static final String SHOW_TICKET_INFO = "| 5. Mostrar informacion de ticket: ";

    /**
     * Option to cancel a ticket.
     */
    public static final String CANCEL_TICKET = "| 6. Cancelar ticket: ";

    /**
     * Array containing window seat codes.
     */
    public static final String[] WINDOW_SEATS = {"a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9" };

    /**
     * Array containing default seat codes.
     */
    public static final String[] DEFAULT_VALUES = {"a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9","c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9","d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9"};

    /**
     * The number of characters for box formatting.
     */
    public static final Integer BOX_NUMBER = 150;

    /**
     * The number of characters for bus formatting.
     */
    public static final Integer BUS_NUMBER = 100;

    /**
     * The price for window seats.
     */
    public static final Float WINDOW_PRICE = 35.00f;

    /**
     * The price for regular seats.
     */
    public static final Float REGULAR_PRICE = 25.00f;
}
