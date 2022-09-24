package client;

import java.io.*;

/**
 * Класс для работы с файлами
 */
public class InputOutputStream {

    private InputStream inputstream;
    private OutputStream outputStream;
    private String path;

    public InputOutputStream(String path) {
        this.path = path;
    }

    /**
     * Получает содержание файла по адресу, указанному при создании класса
     * @return Строковое представление информации из файла
     */
    public String read(){
        String input = "";

        try{
            inputstream = new FileInputStream(path);
            int data = inputstream.read();

            while(data != -1) {
                input += (char) data;
                data = inputstream.read();
            }

            inputstream.close();
        }
        catch (IOException exception){
            exception.printStackTrace();
            System.err.println("Ошибка чтения");
        }
        return input;
    }

    /**
     * Записывает информацию в файл по адресу, указанному при создании класса
     * @param output Текст, который необходимо записать
     */
    public void write(String output){
        try{
        outputStream = new FileOutputStream(path);
        outputStream.write(output.getBytes());
        outputStream.close();
        }
        catch (IOException exception){
            System.err.println("Ошибка записи");
        }
    }

}