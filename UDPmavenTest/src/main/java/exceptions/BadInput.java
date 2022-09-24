package exceptions;

import java.util.NoSuchElementException;

public class BadInput extends NoSuchElementException {

    public static boolean flag = true;

    public BadInput() {
        if(flag) {
            System.err.println("Не вводите такие плохие вещи\nПерезапустите клиент");
            flag = false;
        }
    }
}
