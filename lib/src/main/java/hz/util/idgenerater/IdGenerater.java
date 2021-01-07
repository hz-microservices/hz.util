package hz.util.idgenerater;

/**
 * @author hz
 * 
 * 注意需要注册为单例
 */
public interface IdGenerater {
    /**
     * 获取id
     * @return 生成的id
     * 
     */
    long nextId();
    /**
     * 获取生成器等级
     * @return 生成器等级
     */
    Integer getLevel();
}
