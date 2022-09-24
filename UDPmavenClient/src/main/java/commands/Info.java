package commands;

//import main.*;
import items.*;
import exceptions.*;
import client.*;
import commands.*;


/**
 * Класс команды info
 */
public class Info extends Comand {

    protected ComandTypes comandType = ComandTypes.INFO;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
