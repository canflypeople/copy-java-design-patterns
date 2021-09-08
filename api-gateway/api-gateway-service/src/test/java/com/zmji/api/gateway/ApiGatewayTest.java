package com.zmji.api.gateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @Description:
 * @Author: zhongmou.ji
 * @Date: 2021/9/6 上午11:35
 **/
public class ApiGatewayTest {

  /**
   * 被测试的类
   */
  @InjectMocks
  private ApiGateway apiGateway;

  /**
   * mock的接口，构造输入值和返回值
   */
  @Mock
  private ImageClient imageClient;

  /**
   * mock的接口，构造输入值和返回值
   */
  @Mock
  private PriceClient priceClient;

  /**
   * 设置要进行mock的类， 一般都是当前的类即this
   */
  @BeforeEach
  public void setup(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetProductDesktop() {
    String imagePath = "/product-image.png";
    String price = "20";
    /**
     *
     */
    when(imageClient.getImagePath()).thenReturn(imagePath);
    when(priceClient.getPrice()).thenReturn(price);

    DesktopProduct desktopProduct = apiGateway.getProductDesktop();

    assertEquals(price, desktopProduct.getPrice());
    assertEquals(imagePath, desktopProduct.getImagePath());
  }

  @Test
  void testGetProductMobile() {
    String price = "20";
    when(priceClient.getPrice()).thenReturn(price);

    MobileProduct mobileProduct =  apiGateway.getProductMobile();

    assertEquals(price, mobileProduct.getPrice());
  }


}
