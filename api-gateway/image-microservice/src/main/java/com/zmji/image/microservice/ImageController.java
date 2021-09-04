package com.zmji.image.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/4 下午8:15
 **/
@Slf4j
@RestController
public class ImageController {

  @GetMapping("/image-path")
  public String getImagePath() {
    log.info("Successfully found image path");
    return "/product-image.png";
  }

}
