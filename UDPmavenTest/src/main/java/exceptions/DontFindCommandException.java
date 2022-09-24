package exceptions;

import java.io.IOException;

/**
 * Ошибка неверного ввода команды
 */
public class DontFindCommandException extends IOException {

    @Override
    public void printStackTrace() {
        System.err.println("Команда не была найдена");
    }
}
