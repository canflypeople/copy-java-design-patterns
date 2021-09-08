package com.zmji.api.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/4 下午8:44
 **/
@Slf4j
@Component
public class PriceClientImpl implements PriceClient {
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public String getPrice() {
    // 设置请求头
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // 构造请求的对象
    HttpEntity httpEntity = new HttpEntity(headers);
    try {
      // 通过spring提供的restTemplate进行调用
      ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:50006/price", HttpMethod.GET, httpEntity, String.class);
      logResponse(responseEntity);
      return responseEntity.getBody();
    } catch (RestClientException e) {
      // 调用异常日志打印
      log.error("Failure occurred while getting price info", e);
    }
    return null;
  }

  /**
   * 打印返回的信息处理
   * @param responseEntity
   */
  private void logResponse(ResponseEntity responseEntity) {
    if (isSuccessResponse(responseEntity.getStatusCode().value())) {
      log.info("Price info received successfully");
    } else {
      log.warn("Price info request failed");
    }
  }

  /**
   * 根据响应的码值来判断请求是否处理成功
   * 认为 200-299都是处理成功
   * @param responseCode
   * @return
   */
  private boolean isSuccessResponse(int responseCode) {
    return responseCode >= 200 && responseCode <= 299;
  }
}
