package commands;


/**
 * Класс команды print_descending
 */
public class PrintDescending extends Comand {

    protected ComandTypes comandType = ComandTypes.PRINT_DESCENDING;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "print_descending : вывести элементы коллекции в порядке убывания";
    }
}
