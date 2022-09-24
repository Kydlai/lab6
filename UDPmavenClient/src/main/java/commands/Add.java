package commands;
import java.util.Locale;

import items.*;
import exceptions.*;
import client.*;


/**
 * Класс команды add
 */
public class Add extends Comand {

    protected ComandTypes comandType = ComandTypes.ADD;
    private Dragon dragon = new Dragon();

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "add {element} : добавить новый элемент в коллекцию";
    }

    public Dragon getDragon() {
        return dragon;
    }

    public String getName(){
        return this.comandType.toString();
    }

    public void execute(String arg){
        Dragon dragon = new Dragon();
        if(arg.toLowerCase(Locale.ROOT).equals("dragon")){

            if(!Client.isReadFromFile)
                dragon = Dragon.consoleRead();
            else
                dragon = Dragon.fileRead();
            this.dragon = dragon;
        }
        else {
            try {
                throw new WrongArgumentsException();
            } catch (WrongArgumentsException e) {
                toTransport = false;
            }
        }
    }
}
