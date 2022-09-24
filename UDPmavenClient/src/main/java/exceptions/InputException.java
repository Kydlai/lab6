package exceptions;

/**
 * Ошибка некорректного ввода
 * Является наследником для всех ошибок некорректного ввода
 */
public abstract class InputException extends Exception{
    /**
     * Метод, вызываемый в каждом конструкторе наследника
     * Требует от пользователя повторить попытку ввода
     */
    protected void getNewInput(){
        System.err.println("Повторите попытку ввода");
    }
}
