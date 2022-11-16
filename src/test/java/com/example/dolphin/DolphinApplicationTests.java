package com.example.dolphin;

import com.example.dolphin.infrastructure.tool.CalculateTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class DolphinApplicationTests {

    public static int work(int x, int y, int z) {
        int k = 0;
        int j = 0;

        if ((x > 3) && (z < 10)) {
            k = x * y - 1;
            j = k - z;
        }
        if ((x == 4) | !(y > 5)) {
            j = x * y + 10;
            j = j % 3;
        }
        return j;
    }

    @Test
    void contextLoads() {

    }

    @Test
    void test1() {
        Assertions.assertEquals(CalculateTool.getAnswer("1+1").stripTrailingZeros(), new BigDecimal("2"));
        Assertions.assertEquals(CalculateTool.getAnswer("1x1").stripTrailingZeros(), new BigDecimal("1"));
        Assertions.assertEquals(CalculateTool.getAnswer("4÷2").stripTrailingZeros(), new BigDecimal("2"));
        Assertions.assertEquals(CalculateTool.getAnswer("4-2").stripTrailingZeros(), new BigDecimal("2"));
        Assertions.assertNotEquals(CalculateTool.getAnswer("1+1").stripTrailingZeros(), new BigDecimal("1"));
        Assertions.assertNotEquals(CalculateTool.getAnswer("1x1").stripTrailingZeros(), new BigDecimal("2"));
        Assertions.assertNotEquals(CalculateTool.getAnswer("4÷2").stripTrailingZeros(), new BigDecimal("1"));
        Assertions.assertNotEquals(CalculateTool.getAnswer("4-2").stripTrailingZeros(), new BigDecimal("1"));
    }

    @Test
    void test2() {
        Assertions.assertEquals(work(1, 1, 1), 2);
        Assertions.assertEquals(work(1, 1, 3), 2);
        Assertions.assertEquals(work(3, 5, 10), 1);
        Assertions.assertEquals(work(4, 5, 10), 0);
        Assertions.assertEquals(work(5, 5, 10), 2);
        Assertions.assertEquals(work(3, 5, 9), 1);
        Assertions.assertEquals(work(3, 5, 8), 1);
        Assertions.assertEquals(work(3, 5, 10), 1);
        Assertions.assertEquals(work(3, 4, 10), 1);
        Assertions.assertEquals(work(3, 5, 10), 1);
        Assertions.assertEquals(work(3, 6, 10), 0);
    }

}
