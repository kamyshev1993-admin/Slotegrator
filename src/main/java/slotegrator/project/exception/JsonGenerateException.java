package slotegrator.project.exception;

public class JsonGenerateException extends RuntimeException {
    private static final long serialVersionUID = 957461934728591057L;
    private static final String message = "Ошибка при попытке преобразовать json из класса %s";

    public JsonGenerateException(Class<?> tClass) {
        super(String.format(message, tClass));
    }
}
