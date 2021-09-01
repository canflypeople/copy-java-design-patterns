package com.zmji.ambassador;

/**
 * @Description: 由（{@link-RemoteService}）和（{@link-servicemanarder}）共享的接口。
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:15
 **/
interface RemoteServiceInterface {

  long doRemoteFunction(int value);
}
