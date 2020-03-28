package problem.utils;

public enum LogMessage {
    // Difference method messages
    DIFF_METHOD_INACCURACY("A high probability risk of calculation error!"),
    DIFF_METHOD_DONE("Difference method applied!"),
    DIFF_METHOD_CALCULATING("Calculating difference..."),

    // Data viewers messages
    DATA_SERIALIZER_DONE("Serialization data process is complete!"),

    // Program complete message
    DONE("Calculations complete!");



    private String messageString;

    LogMessage(String messageString) {
        this.messageString = messageString;
    }

    public String getMessageString() {
        return messageString;
    }

}
