package commands;

import items.Dragon;

import java.util.ArrayList;


/**
 * Класс команды remove_lover
 */
public class    RemoveLover extends Comand {

    protected ComandTypes comandType = ComandTypes.REMOVE_LOVER;
    private Dragon dragon = new Dragon();

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "remove_lover {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    public void execute(String arg){
        Dragon dragon = Dragon.consoleRead();
        this.dragon = dragon;
    }
}
