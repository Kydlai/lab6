package commands;

//import main.*;
import client.*;
import com.sun.security.ntlm.Server;

import javax.xml.ws.Service;


/**
 * Класс команды exit
 */
public class Exit extends Comand {

    protected ComandTypes comandType = ComandTypes.EXIT;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "exit : сохранить коллекцию и завершить программу";
    }

    public void execute(String arg){
        Client.sendMessage(this);
        System.exit(1);
    }
}
