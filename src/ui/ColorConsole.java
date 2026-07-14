package ui;

/**
 * Provides utilities for applying colored and styled output
 * to console text.
 */
public class ColorConsole {

    /** ANSI code to reset console text formatting. */
    public static final String RESET = "\u001B[0m";

    /** ANSI code for red console text. */
    public static final String RED = "\u001B[31m";

    /** ANSI code for green console text. */
    public static final String GREEN = "\u001B[32m";

    /** ANSI code for yellow console text. */
    public static final String YELLOW = "\u001B[33m";

    /** ANSI code for blue console text. */
    public static final String BLUE = "\u001B[34m";

    /** ANSI code for cyan console text. */
    public static final String CYAN = "\u001B[36m";

    /** ANSI code for purple console text. */
    public static final String PURPLE = "\u001B[35m";

    /** ANSI code for white console text. */
    public static final String WHITE = "\u001B[37m";

    /**
     * Prints a success message to the console in green.
     *
     * @param message the message to display
     */
    public static void printSuccess(String message) {
        System.out.println(GREEN + message + RESET);
    }

    /**
     * Prints an error message to the console in red.
     *
     * @param message the message to display
     */
    public static void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    /**
     * Prints a warning message to the console in yellow.
     *
     * @param message the message to display
     */
    public static void printWarning(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    /**
     * Prints an informational message to the console in cyan.
     *
     * @param message the message to display
     */
    public static void printInfo(String message) {
        System.out.println(CYAN + message + RESET);
    }
}
