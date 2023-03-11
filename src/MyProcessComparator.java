import java.util.Comparator;

public class MyProcessComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return Math.round(Math.signum(((MyProcess)o1).processDuration()-((MyProcess) o2).processDuration()));
    }
}
