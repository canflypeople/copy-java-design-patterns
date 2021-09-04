package com.zmji.ambassador;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 一个简单的服务器
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:14
 **/
@Slf4j
public class Client {

  private final ServiceAmbassador serviceAmbassador = new ServiceAmbassador();

  /**
   * 供其他方 调用的方法
   * @param value 参数
   * @return 返回值
   */
  long useService(int value) {
    long result = serviceAmbassador.doRemoteFunction(value);
    log.info("Service result: {}", result);
    return result;
  }
}
