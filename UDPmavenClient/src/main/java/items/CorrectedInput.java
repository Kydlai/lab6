package items;

import exceptions.CanNotBeNullException;

import java.util.Scanner;

import client.*;
import exceptions.WrongArgumentsException;


/**
 * Класс только со статическими методами работы с вводом, которые сообщают об ошибках ввода, если они есть
 * Объекты данного класса не создаются
 */
public class CorrectedInput {
    /**
     * Получает аргумент из консоли и сообщает об ошибке ввода, если она есть
     * Требует повторного ввода, пока не будет осуществлён корректный ввод
     * Обрабатывается только ошибка ввода пустой строки (если параметр не может быть null)
     * @param field Параметр, запрашиваемый от пользователя
     * @param input Scanner, через который осуществляется ввод
     * @return Значение искомого параметра
     */
    public static String correctedConsoleInput(String field, Scanner input){
        System.out.println("Введите " + field);
        boolean flag = true;
        String result = null;
        while(flag) {
            result = input.nextLine();
            if(result == ""){
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
     * Получает аргумент из файла и сообщает об ошибке ввода, если она есть
     * Требует повторного ввода, пока не будет осуществлён корректный ввод
     * Обрабатывается только ошибка ввода пустой строки (если параметр не может быть null)
     * @param stream Stream, через который осуществляется ввод
     * @return Значение искомого параметра
     */
    public static String correctedFileInput(InputOutputStream stream){
        String fullInputString = stream.read();
        String[] inputString = fullInputString.split("\r\n");
        boolean flag = true;
        String result = null;
        while(flag) {
            result = inputString[Client.fileReaderCounter++];
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
     * Оболочка для correctedConsoleInput, проверяющая правильность ввода целочисленного значения
     * В случае ошибки ввода требует повторного ввода, пока не будет произведён корректный ввод
     * @param field Параметр, запрашиваемый от пользователя
     * @param input Scanner, через который осуществляется ввод
     * @return
     */
    public static Integer integerConsoleInput(String field, Scanner input){
        Integer integer = null;
        while(integer == null) {
            try {
                integer = Integer.valueOf(CorrectedInput.correctedConsoleInput(field, input));
            } catch (NumberFormatException exception) {
                if(integer == null)
                try {
                    throw new WrongArgumentsException();
                } catch (WrongArgumentsException e) {}
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

    public static Float floatConsoleInput(String field, Scanner input){
        Float integer = null;
        while(integer == null) {
            try {
                integer = Float.valueOf(CorrectedInput.correctedConsoleInput(field, input));
            } catch (NumberFormatException exception) {
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
