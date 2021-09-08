package com.zmji.arrangeactassert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/8 下午2:04
 **/
public class CashAntiAAATest {

  @Test
  void testCash() {
    //initialize
    Cash cash = new Cash(3);
    //test plus
    cash.plus(4);
    assertEquals(7, cash.count());
    //test minus
    cash = new Cash(8);
    assertTrue(cash.minus(5));
    assertEquals(3, cash.count());
    assertFalse(cash.minus(6));
    assertEquals(3, cash.count());
    //test update
    cash.plus(5);
    assertTrue(cash.minus(5));
    assertEquals(3, cash.count());
  }
}
