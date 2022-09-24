package server;

import items.Dragon;
import items.ItemComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Работает с коллекцией объектов класса Dragon
 */
public class ProjectCollection {
    private static ArrayList<Dragon> collection = new ArrayList<Dragon>(0);

    private LocalDate creationDate;

    /**
     * Базовый конструтор, генерирующий дату создания
     */
    public ProjectCollection() {
        this.creationDate = LocalDate.now();
    }

    /**
     * getter для коллекции
     * @return Коллекция
     */
    public ArrayList<Dragon> getCollection(){
        return new ArrayList<Dragon>(Arrays.asList((Dragon[]) this.collection.stream().sorted(new ItemComparator(false)).toArray(Dragon[]::new)));
    }

    /**
     * setter для коллекции
     * @param collection Новая коллекция
     */
    public void setCollection(ArrayList<Dragon> collection) {
        this.collection = collection;
    }

    public void execute(Stream stream){
        collection = new ArrayList<Dragon>(Arrays.asList((Dragon[]) stream.toArray(Dragon[]::new)));
    }

    /**
     * getter для даты создания
     * @return Дата создания
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Сортирует коллекцию
     * @param comparator Компаратор, согласно которому происходит сортировка
     */
    public void sort(Comparator<Dragon> comparator){
        collection.sort(comparator);
    }

    /**
     * Добавляет элемент в коллекцию
     * @param dragon Новый элемент коллекции
     */
    public void add(Dragon dragon){
        collection.add(dragon);
    }

    public String getInfo(){
        String result = "Collection: ";
        for(int i = 0; i < collection.size(); ++i){
            result += "Dragon: " + collection.get(i).getInfo() + "\n";
        }
        return result;
    }
}
