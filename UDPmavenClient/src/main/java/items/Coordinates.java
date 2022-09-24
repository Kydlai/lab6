package items;

import java.util.Scanner;

import client.*;
import exceptions.WrongNumberException;


/**
 * Является одной из хранимых вещей класса Dragon
 */
public class Coordinates extends Item {
    private double x;
    private double y;

    /**
     * Базовый конструктор для задания значений x и y
     * @param x x
     * @param y y
     */
    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор для значений по умолчанию
     * Используется тольк для дебага или значения по умолчанию, которое после должно смениться
     */
    public Coordinates(){
        new Coordinates(0, 0);
    }

    /**
     * getter для x
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * getter для y
     * @return y
     */
    public double getY() {
        return y;
    }

    @Override
    public String getInfo(int count) {
        String output = "";
        output += manager.addTabs(count, "coordinate x = " + x) + "\n";
        output += manager.addTabs(count, "coordinate y = " + y) + "\n";
        return output;
    }

    /**
     * Ввод данных для Coordinates из консоли
     * @return Полученный объект класса Coordinates
     */
    public static Coordinates consoleRead(){

        Scanner input;
        input = new Scanner(System.in);

        double x = 0, y = 0;
        System.out.println("Введите данные для Coordinates:");
        boolean flag = true;
        while (flag)
            try {
                x = Double.valueOf(CorrectedInput.correctedConsoleInput("координату x", input));
                flag = false;
            } catch (NumberFormatException e){
                try{
                    throw new WrongNumberException();
                } catch (WrongNumberException ex){}
            }

        flag = true;
        while (flag)
            try {
                y = Double.valueOf(CorrectedInput.correctedConsoleInput("координату y", input));
                flag = false;
            } catch (NumberFormatException e){
                try{
                    throw new WrongNumberException();
                } catch (WrongNumberException ex){}
            }

        return new Coordinates(x, y);
    }

    /**
     * Ввод данных для Coordinates из файла
     * @return Полученный объект класса Coordinates
     */
    public static Coordinates fileRead(){
        InputOutputStream stream = new InputOutputStream(Client.inputFile);
        String fullInputString = stream.read();

        double x = 0, y = 0;
        System.out.println("Введите данные для Coordinates:");
        boolean flag = true;
        while (flag)
            try {
                x = Double.valueOf(CorrectedInput.correctedFileInput(stream));
                flag = false;
            } catch (NumberFormatException e){
                try{
                    throw new WrongNumberException();
                } catch (WrongNumberException ex){}
            }

        flag = true;
        while (flag)
            try {
                y = Double.valueOf(CorrectedInput.correctedFileInput(stream));
                flag = false;
            } catch (NumberFormatException e){
                try{
                    throw new WrongNumberException();
                } catch (WrongNumberException ex){}
            }

        return new Coordinates(x, y);
    }

}