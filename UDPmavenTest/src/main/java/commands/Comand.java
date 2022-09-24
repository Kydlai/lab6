package commands;

import server.ProjectCollection;


/**
 * Универсальный класс, который является родителем для всех классов команд
 */
public abstract class Comand {
    private int port;
    public boolean isThisComand(Comand comand){
        return this.getName().equals(comand.getName());
    }

    /**
     * getter для названия команды, который нужен для определения какая именно команда вызывается пользователем
     * @return Название команды
     */
    public abstract String getName();

    public int getPort() {
        return port;
    }

    /**
     * Возвращает информацию о том, что делает команда
     * @return Информация о команде
     */
    public String getInfo(){
        return "";
    }

    /**
     * Выполняет команду
     * @return Информация пользователю о выполнении команды
     */
    public abstract String execute();
    public abstract String execute(String argument);
}
