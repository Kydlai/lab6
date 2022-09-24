package items;

import exceptions.WrongNumberException;
import server.InputOutputStream;
import server.Server;

import java.util.Scanner;


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
     * Ввод данных для Coordinates из файла
     * @return Полученный объект класса Coordinates
     */
    public static Coordinates fileRead(){
        InputOutputStream stream = new InputOutputStream(Server.inputFile);
        String fullInputString = stream.read();

        double x = 0, y = 0;
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