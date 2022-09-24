package commands;

import com.google.gson.Gson;
import exceptions.BadInput;
import items.Dragon;
import items.ItemComparator;
import server.InputOutputStream;
import server.ProjectCollection;
import server.Server;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;


/**
 * Класс команды add
 */
public class ExecuteScript extends Comand {

    private ComandTypes comandType = ComandTypes.EXECUTE_SCRIPT;
    private String data;


    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    public String execute(){
        Server.toSend = true;
        Server.inputFile = data;
        Server.isReadFromFile = true;
        InputOutputStream stream = new InputOutputStream(Server.inputFile);
        String fullInputString = stream.read();
        if(fullInputString.equals("Ошибка чтения"))
            return fullInputString;
        ComandManager comandManager = new ComandManager();
        Server.fileReaderCounter = 0;
        String[] inputString = fullInputString.replace("\r", "").split("\n");
        while (Server.fileReaderCounter < inputString.length) {
            Server.fileReaderCounter++;
            try {
                comandManager.excecute(inputString[Server.fileReaderCounter - 1]);
                Comand comand = comandManager.excecute(inputString[Server.fileReaderCounter - 1]);
                if (comand != null) {
                    String message = comand.getName() + ":\n " + comand.execute() + "\n";
                    if(Server.toSend) {
                        Server.collection.sort(new ItemComparator());
                        Server.sendMessage(DatagramChannel.open(), new Gson().toJson(message), getPort());
                    }
                }
            } catch (NoSuchElementException ex) {
                try {
                    throw new BadInput();
                } catch (BadInput exc) {
                }
            } catch (IOException e) {
            }

            Server.inputFile = null;
            Server.isReadFromFile = false;
        }
        return "Скрипт выполнен";
    }

    public String execute(String argument){
        data = argument;
        if(!data.equals(Server.inputFile))
            return this.execute();
        else
            return "Скрипт не может вызывать сам себя";
    }
}
