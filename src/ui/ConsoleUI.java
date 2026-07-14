package ui;

/**
 * Manages the console-based user interface and interaction flow
 * for the trading platform.
 */
public class ConsoleUI {

    /** Table printer used to render headers within the UI. */
    private TablePrinter tablePrinter;

    /**
     * Constructs a new ConsoleUI and initializes its table printer.
     */
    public ConsoleUI() {
        this.tablePrinter = new TablePrinter();
    }

    /**
     * Displays the main menu of the Stock Trading Platform,
     * listing all available options.
     */
    public void displayMainMenu() {
        tablePrinter.printHeader("STOCK TRADING PLATFORM");
        System.out.println(ColorConsole.WHITE + " 1 View Market" + ColorConsole.RESET);
        System.out.println(ColorConsole.WHITE + " 2 Buy Stock" + ColorConsole.RESET);
        System.out.println(ColorConsole.WHITE + " 3 Sell Stock" + ColorConsole.RESET);
        System.out.println(ColorConsole.WHITE + " 4 View Portfolio" + ColorConsole.RESET);
        System.out.println(ColorConsole.WHITE + " 5 View Transactions" + ColorConsole.RESET);
        System.out.println(ColorConsole.WHITE + " 6 Save Data" + ColorConsole.RESET);
        System.out.println(ColorConsole.WHITE + " 7 Exit" + ColorConsole.RESET);
        tablePrinter.printBoxBorder();
    }

    /**
     * Displays a farewell message when the user exits the
     * application.
     */
    public void displayExitMessage() {
        ColorConsole.printInfo("Thank you for using the Stock Trading Platform. Goodbye!");
    }

    /**
     * Displays a message indicating that the user's menu selection
     * was not valid.
     */
    public void displayInvalidChoice() {
        ColorConsole.printError("Invalid choice. Please select a valid menu option.");
    }
}