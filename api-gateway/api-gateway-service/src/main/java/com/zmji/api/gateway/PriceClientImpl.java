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
    HttpHeaders headers = new HttpHeaders();
    HttpEntity httpEntity = new HttpEntity(headers);
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:50006/price", HttpMethod.GET, httpEntity, String.class);
      logResponse(responseEntity);
      return responseEntity.getBody();
    } catch (RestClientException e) {
      log.error("Failure occurred while getting price info", e);
    }
    return null;
  }

  private void logResponse(ResponseEntity responseEntity) {
    if (isSuccessResponse(responseEntity.getStatusCode().value())) {
      log.info("Price info received successfully");
    } else {
      log.warn("Price info request failed");
    }
  }

  private boolean isSuccessResponse(int responseCode) {
    return responseCode >= 200 && responseCode <= 299;
  }
}
