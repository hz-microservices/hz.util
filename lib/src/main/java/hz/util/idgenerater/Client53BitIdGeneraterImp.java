package hz.util.idgenerater;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hz
 * 53位id生成
 * |--------|--------|--------|--------|--------|--------|--------|--------|
 * |00000000|00011111|11111111|11111111|11111111|11111111|11111111|11111111| 53位id
 * |--------|---xxxxx|xxxxxxxx|xxxxxxxx|xxxxxxxx|xxx-----|--------|--------| 时间戳32位
 * |--------|--------|--------|--------|--------|---xxxxx|xxxxxxxx|--------| 自增13位，支持每秒生成8192个id
 * |--------|--------|--------|--------|--------|--------|--------|xxxxxxxx| 机器码8位，支持256台机器
 * 
 * 如果觉得自增id不够用，可以将时间戳的位数再减少，给自增id使用
 * 
 */
public class Client53BitIdGeneraterImp implements IdGenerater {

    //private static final Logger logger = LoggerFactory.getLogger(Client53BitIdGeneraterImp.class);
    /**
     * 计算2000-01-01的时间戳（秒）
    */ 
    private static final long OFFSET = LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.of("Z")).toEpochSecond();
    private static final long MAX_NEXT = 0b11111_11111111L;

    private long offset = 0;
    private long lastEpoch = 0;

    private Integer level = 0;

    public Client53BitIdGeneraterImp(Integer level)
    {
        this.level = level;
    }

    @Override
    public long nextId() {
        return nextId(System.currentTimeMillis()/1000);
    }

    /**
     * 生成id
     * @param epochSecond
     * @return
     */
    private synchronized long nextId(long epochSecond) {

        // 处理时钟回拨问题
        if (epochSecond < lastEpoch) {
            // clock is turn back
            //logger.warn("clock is back: " + epochSecond + " from previous:" + lastEpoch);
            epochSecond = lastEpoch;
        }
        // 如果当前时间（秒）不等于最后获取id时间
        if (lastEpoch != epochSecond) {
            // 更新最后时间为当前时间
            lastEpoch = epochSecond;
            // 自增从0开始
            reset();
        }

        offset++;
        
        // 防止自增的值超过最大自增值
        long next = offset & MAX_NEXT;

        if(next == 0) {
            //logger.warn("当前时间的自增id已经到达最大值：" + epochSecond);
            // 向下一秒借id
            return nextId(epochSecond + 1);
        }

        return generateId(epochSecond, next);
    }

    @Override
    public Integer getLevel() {
        return this.level;
    }

    private void reset() {
        this.offset = 0;
    }

    /**
     * 生成id
     * @param epochSecond 时间戳
     * @param next 自增id
     * @return id
     */
    private long generateId(long epochSecond, long next)
    {
        try {
            String address = InetAddress.getLocalHost().getHostAddress();
            String[] addresses = address.split("\\.");
            String lastAddress = addresses[addresses.length - 1];
            long machineId = Long.valueOf(lastAddress);

            return ((epochSecond - OFFSET) << 21) | next << 8 | machineId ;

        } catch (UnknownHostException e) {
            //logger.warn("unable to get host name. set server id = 0.");
        }

        return 0;
    }
}
