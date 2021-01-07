package hz.util.idgenerater;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author hz
 * id生成器工厂
 */
public class IdGeneraterFactoryImp implements IdGeneraterFactory {

    private Queue<IdGenerater> idGeneraters = new PriorityQueue<>(new IdGeneraterComparator());

    @Override
    public IdGenerater getIdGenerater() {
        // todo 判断队列中是否有生成器
        return idGeneraters.peek();
    }

    @Override
    public Boolean setIdGenerater(IdGenerater idGenerater) {
        return idGeneraters.add(idGenerater);
    }

    @Override
    public long nextId() {
        IdGenerater generater = getIdGenerater();
        return generater.nextId();
    }
}
