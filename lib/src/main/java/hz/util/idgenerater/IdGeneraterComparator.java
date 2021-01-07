package hz.util.idgenerater;

import java.util.Comparator;

/**
 * @author hz
 * id生成器优先级判断
 */
public class IdGeneraterComparator implements Comparator<IdGenerater> {
    @Override
    public int compare(IdGenerater o1, IdGenerater o2) {
        return o1.getLevel().compareTo(o2.getLevel());
    }
}
