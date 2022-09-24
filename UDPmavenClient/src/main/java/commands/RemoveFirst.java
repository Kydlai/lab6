package commands;

import java.util.ArrayList;

import items.*;


/**
 * Класс команды remove_first
 */
public class RemoveFirst extends Comand {

    protected ComandTypes comandType = ComandTypes.REMOVE_FIRST;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "remove_first : удалить первый элемент из коллекции";
    }
}
