package com.zmji.ambassador;

/**
 * @Description:
 * 保存有关远程服务状态的信息。
 * <p>此枚举替换以前的整数值
 *
 * 存储在{@link RemoteServiceInterface}中，因为SonarCloud正在识别
 *
 * 这是一个问题。所有测试用例都在更改后进行了检查，
 *
 * 没有失败</p>
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:15
 **/
public enum RemoteServiceStatus {
  FAILURE(-1);

  long remoteServiceStatusValue;

  RemoteServiceStatus(long remoteServiceStatusValue) {
    this.remoteServiceStatusValue = remoteServiceStatusValue;
  }

  public long getRemoteServiceStatusValue() {
    return remoteServiceStatusValue;
  }
}
