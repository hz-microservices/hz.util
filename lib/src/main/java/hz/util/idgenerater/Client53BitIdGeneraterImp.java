package hz.util.idgenerater;

/**
 * @author hz
 * 53bit id生成器，只依赖客户端
 */
public class Client53BitIdGeneraterImp implements IdGenerater {

    private Integer level = 0;
    public Client53BitIdGeneraterImp(Integer level)
    {
        this.level = level;
    }

    @Override
    public Long nextId() {
        // TODO Auto-generated method stub
        return Long.MIN_VALUE;
    }

    @Override
    public Integer getLevel() {
        return this.level;
    }
}
