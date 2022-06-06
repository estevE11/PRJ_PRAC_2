package pr2;

import java.util.Comparator;

public class AscendingYearComparator implements Comparator<Aircraft> {
    @Override
    public int compare(Aircraft o1, Aircraft o2) {
        if (o1.getYear()> o2.getYear()) return 1;
        return -1;
    }
}