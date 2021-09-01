package com.zmji.ambassador;

import com.zmji.ambassador.util.RandomProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 由单例实现表示的远程遗留应用程序。
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:14
 **/
@Slf4j
public class RemoteService implements RemoteServiceInterface {
  private static final int THRESHOLD = 200;
  private static RemoteService service = null;
  private final RandomProvider randomProvider;

  static synchronized RemoteService getRemoteService() {
    if (service == null) {
      service = new RemoteService();
    }
    return service;
  }

  /**
   * 此构造函数仅用于测试目的。
   */
  private RemoteService() {
    this(Math::random);
  }

  RemoteService(RandomProvider randomProvider) {
    this.randomProvider = randomProvider;
  }


  /**
   * 远程函数取一个值，然后用随机时间乘以10。意志
   *
   * 有时返回-1。这模拟了客户端可能必须考虑的连接问题。
   *
   * @param value 要乘以的整数值。
   *
   * @return 如果waitTime小于{@link RemoteService#THRESHOLD}，则返回值*10，
   * 否则{@link RemoteServiceStatus#FAILURE}。
   */
  @Override
  public long doRemoteFunction(int value) {

    long waitTime = (long) Math.floor(randomProvider.random() * 1000);

    try {
      Thread.sleep(waitTime);
    } catch (InterruptedException e) {
      log.error("Thread sleep state interrupted", e);
      Thread.currentThread().interrupt();
    }
    return waitTime <= THRESHOLD ? value * 10
      : RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
  }
}
