package com.zmji.arrangeactassert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/8 上午10:04
 **/
public class CashAAATest {

  @Test
  void testPlus() {
    // Arrange 初始化
    Cash cash = new Cash(3);
    //Act 操作
    cash.plus(4);
    //Assert 断言， 判断处理是否正确
    assertEquals(7, cash.count());
  }

  @Test
  void testMinus() {
    //Arrange 初始化
    Cash cash = new Cash(8);
    //Act 操作
    Boolean result = cash.minus(5);
    //Assert 断言， 判断处理是否正确
    assertTrue(result);
    assertEquals(3, cash.count());
  }

  @Test
  void testInsufficientMinus() {
    //Arrange 初始化
    Cash cash = new Cash(1);
    //Act 操作
    boolean result = cash.minus(6);
    //Assert 断言， 判断处理是否正确
    assertFalse(result);
    assertEquals(1, cash.count());
  }

  @Test
  void testUpdate() {
    //Arrange 初始化
    Cash cash = new Cash(5);
    //Act 操作
    cash.plus(6);
    boolean result = cash.minus(3);
    //Assert 断言， 判断处理是否正确
    assertTrue(result);
    assertEquals(8, cash.count());

  }

}
