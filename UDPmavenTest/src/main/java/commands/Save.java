package commands;

import server.InputOutputStream;
import server.ProjectCollection;
import server.Server;
import xml.*;


/**
 * Класс команды save
 */
public class Save extends Comand {

    private ComandTypes comandType = ComandTypes.SAVE;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "save : сохранить коллекцию в файл";
    }

    public String execute(){
        Server.toSend = false;
        InputOutputStream stream = new InputOutputStream("lab6.xml");
        stream.write(Parser.creatorXML(Server.collection));
        return "";
    }

    public String execute(String argument){
        return this.execute();
    }
}
