package exceptions;

import java.io.IOException;

public class ConnectionException extends IOException {
    @Override
    public void printStackTrace(){
        System.err.println("Ошибка отправки подтверждения подключения");
    }
}
