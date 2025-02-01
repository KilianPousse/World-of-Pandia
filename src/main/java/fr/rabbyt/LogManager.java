package fr.rabbyt;


public class LogManager {
    /* ============== CLASS CONSTANTS ============== */

    /** Reset color */
    public static final String COLOR_RESET   = "\u001B[0m";

    /** Red color */
    public static final String COLOR_RED     = "\u001B[31m";

    /** Green color */
    public static final String COLOR_GREEN   = "\u001B[32m";

    /** Yellow color */
    public static final String COLOR_YELLOW  = "\u001B[33m";

    /** Blue color */
    public static final String COLOR_BLUE    = "\u001B[34m";

    /** Magenta color */
    public static final String COLOR_MAGENTA = "\u001B[35m";

    /** Cyan color */
    public static final String COLOR_CYAN    = "\u001B[36m";


    /* ============== CLASS METHODS ============== */
    /**
     * Prints a log message with a specific type and color.
     *
     * @param type The type for the log message (e.g., INFO, WARNING, ERROR).
     * @param color The ANSI color code to format the label.
     * @param msg  The message content to be printed.
     */
    private static void addLogWithType(String type, String color, String msg) {
        String text = COLOR_RESET + "[" + color + type + COLOR_RESET  + "]: " + msg;
        System.out.println(text); 
    }

    /**
     * Prints a default informational log message with a blue "INFO" label.
     *
     * @param msg The message content to be printed.
     */
    public static void add(String msg) {
        addLogWithType("INFO", COLOR_BLUE, msg);
    }

    /**
     * Prints an informational log message with a blue "INFO" label.
     *
     * @param msg The message content to be printed.
     */
    public static void addInfo(String msg) {
        add(msg);
    }

    /**
     * Prints a warning log message with a yellow "WARNING" label.
     *
     * @param msg The message content to be printed.
     */
    public static void addWarning(String msg) {
        addLogWithType("WARNING", COLOR_YELLOW, msg);
    }

    /**
     * Prints an error log message with a red "ERROR" label.
     *
     * @param msg The message content to be printed.
     */
    public static void addError(String msg) {
        addLogWithType("ERROR", COLOR_RED, msg);
    }
}
