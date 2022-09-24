package items;

/**
 * Класс-наследник для всех хранимых объектов коллекции
 */
public abstract class Item{
    protected transient StringManager manager = new StringManager();

    /**
     * Возвращает информацию об объекте в текстовом предствавлении без отступов
     * @return Текстовая информация об объекте
     */
    public String getInfo(){
        return this.getInfo(0);
    }

    /**
     * Возвращает информацию об объекте в текстовом предствавлении с определенным количеством отступов
     * @param count Количество отступов
     * @return Текстовая информация об объекте с отступами
     */
    public abstract String getInfo(int count);

    /**
     * Внутренний класс для работы с отступами при выводе информации о содержании коллекции
     */
    protected class StringManager{

        /**
         * Добавляет к строке определенное количество отступов
         * @param count Количество отступов, котрое нужно добавить
         * @param string Текст, к которому добавляются отступы
         * @return Тект с дополнительными отступами
         */
        public String addTabs(int count, String string){
            String output = "";
            for(int i = 0; i < count; i++)
                output += "\t";
            output += string;
            return output;
        }
    }
}
