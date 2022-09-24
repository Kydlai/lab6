package exceptions;

/**
 * Ошибка неверного ввода параметра
 * Происходит только в случае, если параметр не может быть null, а на вход передана пустая строка
 */
public class CanNotBeNullException extends InputException {
    public CanNotBeNullException(){
        System.err.println("Переменная не может принимать значение null");
        this.getNewInput();
    }
}
