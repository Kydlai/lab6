package exceptions;

/**
 * Ошибка некорректного ввода числа
 */
public class WrongNumberException extends NumberFormatException{
    public WrongNumberException(){
        System.err.println("Неправильный формат ввода числа");
        System.err.println("Повторите попытку ввода");
    }
}
