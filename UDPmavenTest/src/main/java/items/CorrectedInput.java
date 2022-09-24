package items;

import exceptions.CanNotBeNullException;
import exceptions.WrongArgumentsException;
import server.InputOutputStream;
import server.Server;

import java.util.Scanner;


/**
 * Класс только со статическими методами работы с вводом, которые сообщают об ошибках ввода, если они есть
 * Объекты данного класса не создаются
 */
public class CorrectedInput {

    public static String correctedFileInput(InputOutputStream stream){
        String fullInputString = stream.read();
        String[] inputString = fullInputString.split("\r\n");
        boolean flag = true;
        String result = null;
        while(flag) {
            result = inputString[Server.fileReaderCounter++];
            if(result.equals("") || result == null){
                try {
                    throw new CanNotBeNullException();
                } catch (CanNotBeNullException e) {}
            }
            else
                flag = false;
        }
        return result;
    }

    /**
     * Оболочка для correctedFileInput, проверяющая правильность ввода целочисленного значения
     * В случае ошибки ввода требует повторного ввода, пока не будет произведён корректный ввод
     * @param stream Stream, через который осуществляется ввод
     * @return
     */
    public static Integer integerFileInput(InputOutputStream stream){
        Integer integer = null;
        while(integer == null) {
            try {
                integer = Integer.valueOf(CorrectedInput.correctedFileInput(stream));
            } catch (NumberFormatException exception) {
                if(integer == null)
                try {
                    throw new CanNotBeNullException();
                } catch (CanNotBeNullException e) {}
            }
        }
        return integer;
    }


    /**
     * Оболочка для correctedFileInput, проверяющая правильность ввода целочисленного значения
     * В случае ошибки ввода требует повторного ввода, пока не будет произведён корректный ввод
     * @param stream Stream, через который осуществляется ввод
     * @return
     */
    public static Float floatFileInput(InputOutputStream stream){
        Float integer = null;
        while(integer == null) {
            try {
                integer = Float.valueOf(CorrectedInput.correctedFileInput(stream));
            } catch (NumberFormatException exception) {
                try {
                    throw new CanNotBeNullException();
                } catch (CanNotBeNullException e) {}
            }
        }
        return integer;
    }
}
