package items;

import exceptions.WrongNumberException;
import server.InputOutputStream;
import server.Server;

import java.util.Scanner;


/**
 * Является одной из хранимых вещей класса Dragon
 */
public class DragonHead extends Item {
    private Long size = null; //Поле не может быть null
    private Float eyesCount; //Поле может быть null

    public DragonHead(Long size, Float eyesCount) {
        if(size != null && eyesCount != null) {
            this.size = size;
            this.eyesCount = eyesCount;
        }
        else{
            if(size == null)
                System.err.println("У объекта должен быть размер");
            if(eyesCount == null)
                System.err.println("У объекта должно быть указано количество глаз");
            System.err.println("Объект не был добавлен в коллекцию");
        }
    }

    public DragonHead(){
        new DragonHead(Long.valueOf(0) , Float.valueOf("0"));
    }

    /**
     * getter для size
     * @return size
     */
    public Long getSize() {
        return size;
    }

    /**
     * getter для eyesCount
     * @return eyesCount
     */
    public Float getEyesCount() {
        return eyesCount;
    }

    @Override
    public String getInfo(int count) {
        String output = "";
        output += manager.addTabs(count, "size = " + size) + "\n";
        output += manager.addTabs(count, "eyesCount = " + eyesCount) + "\n";
        return output;
    }


    public static DragonHead fileRead(){
        InputOutputStream stream = new InputOutputStream(Server.inputFile);
        Long size = 0L;
        Float eyesCount = 0F;

        boolean flag = true;
        while (flag)
            try {
                size = Long.valueOf(CorrectedInput.correctedFileInput(stream));
                flag = false;
            } catch (NumberFormatException e){
                try{
                    throw new WrongNumberException();
                } catch (WrongNumberException ex){}
            }

        flag = true;
        while (flag)
            try {
                eyesCount = Float.valueOf(CorrectedInput.floatFileInput(stream));
                flag = false;
            } catch (NumberFormatException e){
                try{
                    throw new WrongNumberException();
                } catch (WrongNumberException ex){}
            }

        return new DragonHead(size, eyesCount);
    }
}
