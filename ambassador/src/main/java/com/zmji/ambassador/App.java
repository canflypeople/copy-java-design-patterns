package com.zmji.ambassador;

/**
 * @Description: 简单的示例
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:13
 **/
public class App {

  public static void main(String[] args) {
    Client host1 = new Client();
    Client host2 = new Client();
    host1.useService(12);
    host2.useService(73);
  }

}
