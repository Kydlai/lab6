package exceptions;

/**
 * Ошибка некорректного ввода DragonType
 */
public class WrongDragonTypeException extends InputException {
    public WrongDragonTypeException(){
        System.err.println("type может принимать только значения air, underground, fire и water");
        this.getNewInput();
    }
}
