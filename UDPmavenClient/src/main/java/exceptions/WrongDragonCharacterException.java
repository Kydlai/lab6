package exceptions;

/**
 * Ошибка некорректного ввода DragonCharacter
 */
public class WrongDragonCharacterException extends InputException {
    public WrongDragonCharacterException(){
        System.err.println("character может принимать только значения fickle, wise и good");
        this.getNewInput();
    }
}
