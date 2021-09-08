模式: Arrange/Act/Assert 布局-操作-断言
---
标签: 测试  
通常被认为: Given/When/Then

### 意图
Arrange/Act/Assert (AAA) 是一种组织单元测试的模式

它将测试分为三个明确的步骤:
1. 安排: 执行测试所需要的设置和初始化
2. 行动: 采取测试所需的行动
3. 断言: 验证测试结果

### 解释
这种模式有几个显著的好处。它在测试设置、操作和结果中创建了一个很清晰的界限。
这种结构使得代码更容易阅读和理解
如果你将步骤按顺序排列并格式化代码， 将它们分开，你可以扫描测试并可以很快理解它的功能
 
在编写测试时， 它还强制执行一定的程度的规程。你必须思考清楚你的测试需要执行的三个步骤。
同时它使测试更自然地编写的，因为你已经有了大纲。

**ps: 有点像现在流行的TDD测试驱动开发，但是实际开发的过程中很少写单元测试，以后工作中可以试着写下，好处还是很多的😊**

现实生活中的例子
> 我们需要为一个类编写全面而清晰的单元测试

简单地说
> Arrange/Act/Assert 是一种测试模式， 它将测试组织为三个清晰的步骤， 以便于维护

维基百科说
> Arrange/Act/Assert是用于在单元测试中排列和格式化代码的模式

**编程示例**

让我们首先介绍一下要进行单元测试的'Cash'类

```java
public class Cash {

  private int amount;

  Cash(int amount) {
    this.amount = amount;
  }

  void plus(int addend) {
    amount += addend;
  }

  boolean minus(int subtrahend) {
    if (amount >= subtrahend) {
      amount -= subtrahend;
      return true;
    } else {
      return false;
    }
  }

  int count() {
    return amount;
  }
}
```

然后我们根据 Arrange/Act/Assert模式编写单元测试。注意每个单元测试的单独步骤

```java
public class CashAAATest {

  @Test
  void testPlus() {
    // Arrange 初始化
    Cash cash = new Cash(3);
    //Act 操作
    cash.plus(4);
    //Assert 断言， 判断处理是否正确
    assertEquals(7, cash.count());
  }

  @Test
  void testMinus() {
    //Arrange 初始化
    Cash cash = new Cash(8);
    //Act 操作
    Boolean result = cash.minus(5);
    //Assert 断言， 判断处理是否正确
    assertTrue(result);
    assertEquals(3, cash.count());
  }

  @Test
  void testInsufficientMinus() {
    //Arrange 初始化
    Cash cash = new Cash(1);
    //Act 操作
    boolean result = cash.minus(6);
    //Assert 断言， 判断处理是否正确
    assertFalse(result);
    assertEquals(1, cash.count());
  }

  /*
  * 结合多个操作测试
  */
  @Test
  void testUpdate() {
    //Arrange 初始化
    Cash cash = new Cash(5);
    //Act 操作
    cash.plus(6);
    boolean result = cash.minus(3);
    //Assert 断言， 判断处理是否正确
    assertTrue(result);
    assertEquals(8, cash.count());

  }

}
```

### 适用性
在以下情况下使用Arrange/Act/Assert
* 你需要构建单元测试， 以便它们更易于阅读、维护和迭代

### 总结
Arrange/Act/Assert模式 可以让我们更容易编写单元测试。
笔者在实际项目开发中，很少很少写单元测试，同时通过postman发请求进行测试， 可能会导致改了一些东西而影响到其他部分， 出现bug。
如果单元测试完备的话， 能够有效较少bug的数量， 减轻开发人员单元测试的时间， 对后续维护迭升级帮助都很大

### 题外话
我们开发的系统一般都是和数据库和其他系统进行交互，而单元测试执行中可能没有这些依赖的条件


那这样如何编写单元测试呢？  
可以通过借助工具如mockito来mock(模拟)这些交互，其实步骤也是类似的


如下所示
```java
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
  private PriceClient priceClient;

  /**
   * 设置要进行mock的类， 一般都是当前的类即this
   */
  @BeforeEach
  public void setup(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetProductMobile() {
    String price = "20";
    // 设置mock对象的方法的输出结果
    when(priceClient.getPrice()).thenReturn(price);
    // 在apiGateway调用到 priceClient里的getPrice方法时，就会直接返回 事先预定好的值
    MobileProduct mobileProduct =  apiGateway.getProductMobile();
    // 执行方法并判断结果是否正确
    assertEquals(price, mobileProduct.getPrice());
  }

}

```




