package hz.util.idgenerater;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class Client53BitIdGeneraterImpTest {
    @Test public void testNextId()
    {
        // arrange
        Integer level = 1;
        IdGenerater idGenerater = new Client53BitIdGeneraterImp(level);

        // act
        Long id = idGenerater.nextId();

        // assert
        assertTrue(id >= Long.MIN_VALUE && id <= Long.MAX_VALUE);
    }

    @Test public void testGetLevel()
    {
        // arrange
        Integer level = 1;
        IdGenerater idGenerater = new Client53BitIdGeneraterImp(level);

        // act
        Integer actualLevel = idGenerater.getLevel();

        // assert
        assertTrue(level == actualLevel);
    }
}
