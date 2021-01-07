package hz.util.idgenerater;

/**
 * @author hz
 * Id生成器工厂
 */
public interface IdGeneraterFactory {
    
    /**
     * 获取id生成器
     * @return id生成器
     */
    IdGenerater getIdGenerater();
    
    /**
     * 添加id生成器
     * 
     * @return true: 成功，false：失败
     * @param idGenerater 实现了生成器接口的实现类
     */
    Boolean setIdGenerater(IdGenerater idGenerater);

    /**
     * 获取id
     * @return id
     */
    Long nextId();
}
