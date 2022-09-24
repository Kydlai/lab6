package items;

import server.Server;

import java.util.Comparator;


/**
 * Компаратор для сортировки коллекции
 */
public class ItemComparator implements Comparator<Dragon> {

    private boolean isCompareReverse = false;

    public ItemComparator() {}

    public ItemComparator(boolean isCompareReverse){
        this.isCompareReverse = isCompareReverse;
    }

    @Override
    public int compare(Dragon dragon1, Dragon dragon2) {
        int numberOne = dragon1.getNumber();
        int numberTwo = dragon2.getNumber();

        if(numberOne == numberTwo)
            return 0;
        else if(numberOne < numberTwo)
            return (Server.isCompareReverse ^ this.isCompareReverse ? 1 : -1);
        else
            return (Server.isCompareReverse ^ this.isCompareReverse ? -1 : 1);
    }
}
