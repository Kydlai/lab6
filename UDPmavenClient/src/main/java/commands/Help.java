package commands;

import items.*;
import exceptions.*;
import client.*;
import commands.*;


/**
 * Класс команды help
 */
public class Help extends Comand {

    protected ComandTypes comandType = ComandTypes.HELP;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "help : вывести справку по доступным командам";
    }
}
