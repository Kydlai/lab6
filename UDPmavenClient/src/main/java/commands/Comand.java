package commands;

//import main.*;
import com.sun.security.ntlm.Server;
import items.*;
import exceptions.*;
import client.*;
import commands.*;


/**
 * Универсальный класс, который является родителем для всех классов команд
 */
public abstract class Comand {
    private transient ComandTypes comandType;
    private int port;
    protected transient boolean toTransport = true;

    public boolean isThisComand(Comand comand){
        return this.getComandType().equals(comand.getComandType());
    }

    public boolean isToTransport() {
        return toTransport;
    }

    public void setPort() {
        this.port = Client.clientPort;
    }

    public abstract ComandTypes getComandType();

    /**
     * Возвращает информацию о том, что делает команда
     * @return Информация о команде
     */
    public String getInfo(){
        return "";
    }

    /**
     * Выполняет команду
     * @param arg Аргумент команды, если он есть
     * @return Информация пользователю о выполнении команды
     */
    public void execute(String arg){}
}
