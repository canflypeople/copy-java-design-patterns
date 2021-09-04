package com.zmji.ambassador;

import lombok.extern.slf4j.Slf4j;

import static com.zmji.ambassador.RemoteServiceStatus.FAILURE;

/**
 * @Description:
 * 调用接口具体的处理类
 *
 * ServiceAmbassador为（{@link Client}）访问（{@link RemoteService}）提供了一个接口。
 *
 * 该接口以一种安全的方式添加了日志记录、延迟测试和服务的使用，而这种方式不会
 *
 * 出现连接问题时，会给远程服务增加压力
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:15
 **/
@Slf4j
public class ServiceAmbassador implements RemoteServiceInterface {

  // 重试次数
  private static final int RETRIES = 3;
  // 线程睡眠时间
  private static final int DELAY_MS = 3000;

  ServiceAmbassador() {
  }

  @Override
  public long doRemoteFunction(int value) {
    return safeCall(value);
  }

  private long checkLatency(int value) {
    long startTime = System.currentTimeMillis();
    long result = RemoteService.getRemoteService().doRemoteFunction(value);
    long timeTaken = System.currentTimeMillis() - startTime;

    log.info("Time taken (ms): {}", timeTaken);
    return result;
  }

  /**
   * 安全的调用，如果调用失败就重试n次，
   * @param value 调用接口的参数
   * @return 结果
   */
  private long safeCall(int value) {
    long retries = 0;
    long result = FAILURE.getRemoteServiceStatusValue();

    for (int i = 0; i < RETRIES; i++) {
      if (retries >= RETRIES) {
        return FAILURE.getRemoteServiceStatusValue();
      }

      if ((result = checkLatency(value)) == FAILURE.getRemoteServiceStatusValue()) {
        log.info("Failed to reach remote: ({})", i + 1);
        retries++;
        try {
          Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
          log.error("Thread sleep state interrupted", e);
          Thread.currentThread().interrupt();
        }
      } else {
        break;
      }
    }
    return result;
  }

}
