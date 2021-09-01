package com.zmji.ambassador;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:14
 **/
@Slf4j
public class Client {

  private final RemoteServiceInterface serviceAmbassador = new ServiceAmbassador();

  long useService(int value) {
    long result = serviceAmbassador.doRemoteFunction(value);
    log.info("Service result: {}", result);
    return result;
  }
}
