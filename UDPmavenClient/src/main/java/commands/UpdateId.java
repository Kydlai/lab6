package commands;

import items.*;
import exceptions.*;
import client.*;
import commands.*;

/**
 * Класс команды update_id
 */
public class UpdateId extends Comand {

    protected ComandTypes comandType = ComandTypes.UPDATE_ID;
    private Long id = -1L;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "update_id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    public void execute(String arg){
        Long id = 0L;
        try {
            id = Long.parseLong(arg);
        } catch (NumberFormatException e) {
            try{
                toTransport = false;
                throw new WrongArgumentsException();
            } catch (WrongArgumentsException ex) {}
        }

        this.id = id;
    }
}
