package com.zmji.arrangeactassert;

import lombok.AllArgsConstructor;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/8 上午9:54
 **/
@AllArgsConstructor
public class Cash {

  private int amount;

  //plus 相加
  void plus(int addend) {
    amount += addend;
  }

  /*
  进行减法， 如果当前数值大于被减数值， 则进行减法， 返回true
  否则 不做处理，返回false;
   */
  boolean minus(int subtrahend) {
    if (amount >= subtrahend) {
      amount -= subtrahend;
      return true;
    } else {
      return false;
    }
  }

  // count 返回数量
  int count() {
    return amount;
  }


}
