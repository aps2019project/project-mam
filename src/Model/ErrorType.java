package Model;

public enum ErrorType {
    ;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorType(String message) {
        this.message = message;
    }


}
