package pro.sky.tgbotcatshelter.exception;

public class NotFoundReportException extends RuntimeException {

    public NotFoundReportException() {
        super("Отчетов нет!");
    }

    public NotFoundReportException(String message) {
        super(message);
    }

    public NotFoundReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundReportException(Throwable cause) {
        super(cause);
    }

    public NotFoundReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
