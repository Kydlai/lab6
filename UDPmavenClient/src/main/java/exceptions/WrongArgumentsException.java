package exceptions;

/**
 * Ошибка некорректного ввода аргументов
 */
public class WrongArgumentsException extends InputException {
    public WrongArgumentsException(){
        System.err.println("Введенные аргументы неверны");
    }
}
