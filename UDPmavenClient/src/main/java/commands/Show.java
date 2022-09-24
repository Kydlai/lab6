package commands;

//import main.*;
import items.*;
import exceptions.*;
import client.*;
import commands.*;


/**
 * Класс команды show
 */
public class Show extends Comand {

    protected ComandTypes comandType = ComandTypes.SHOW;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
