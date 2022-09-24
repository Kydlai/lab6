package commands;

//import main.*;
import items.*;
import exceptions.*;
import client.*;
import commands.*;


/**
 * Класс команды reorder
 */
public class Reorder extends Comand {

    protected ComandTypes comandType = ComandTypes.REORDER;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "reorder : отсортировать коллекцию в порядке, обратном нынешнему";
    }
}
