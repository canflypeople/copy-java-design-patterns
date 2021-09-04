package com.zmji.ambassador;

/**
 * @Description: 由（{@link-RemoteService}）和（{@link-servicemanarder}）共享的接口。
 * 通过接口调用远程服务， 类似于 feign
 * @Author: zhongmou.ji
 * @Date: 2021/9/1 下午6:15
 **/
interface RemoteServiceInterface {

  long doRemoteFunction(int value);
}
