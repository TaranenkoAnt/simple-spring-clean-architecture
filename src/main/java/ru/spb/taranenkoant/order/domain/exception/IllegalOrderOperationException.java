package ru.spb.taranenkoant.order.domain.exception;


/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public class IllegalOrderOperationException extends RuntimeException {

    public IllegalOrderOperationException(String message) {
        super(message);
    }

    public IllegalOrderOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOrderOperationException(String message, Object... args) {
        super(String.format(message, args));
    }
}
