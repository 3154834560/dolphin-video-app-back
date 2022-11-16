package com.example.dolphin;


import java.math.BigDecimal;

/**
 * @author 王景阳
 * @date 2022/10/27 15:16
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("11111110000").stripTrailingZeros().toString());
        System.out.println(new BigDecimal("11111110010").toPlainString());
        System.out.println(new BigDecimal("11111110010").toString());
        System.out.println(new BigDecimal("11111110010").toEngineeringString());
    }
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
}