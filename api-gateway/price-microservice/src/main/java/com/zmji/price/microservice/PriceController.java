package com.zmji.price.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/4 下午8:23
 **/
@Slf4j
@RestController
public class PriceController {

  @GetMapping("/price")
  public String getPrice() {
    log.info("Successful found price info");
    return "20";
  }
}
