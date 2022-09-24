package exceptions;

/**
 * Ошибка неверного ввода команды
 */
public class DontFindCommandException extends InputException {
    public DontFindCommandException(){
        System.err.println("Команда не была найдена");
        this.getNewInput();
    }
}
