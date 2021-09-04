package com.zmji.api.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/4 下午8:46
 **/
@RestController
public class ApiGateway {

  @Autowired
  private ImageClient imageClient;

  @Autowired
  private PriceClient priceClient;

  @GetMapping("/desktop")
  public DesktopProduct getProductDesktop() {
    DesktopProduct desktopProduct = new DesktopProduct();
    desktopProduct.setImagePath(imageClient.getImagePath());
    desktopProduct.setPrice(priceClient.getPrice());
    return desktopProduct;
  }

  @GetMapping("/mobile")
  public MobileProduct getProductMobile() {
    MobileProduct mobileProduct = new MobileProduct();
    mobileProduct.setPrice(priceClient.getPrice());
    return mobileProduct;
  }
}
