package hz.util.idgenerater;

/**
 * @author hz
 */
public interface IdGenerater {
    /**
     * 获取id
     * @return 生成的id
     */
    Long nextId();
    /**
     * 获取生成器等级
     * @return 生成器等级
     */
    Integer getLevel();
}
