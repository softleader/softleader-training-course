package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

  @Autowired SampleTxService sampleTxService;
  @Autowired SampleNoTxService sampleNoTxService;
  @Autowired SampleTxNestService sampleTxNestService;

  @Test
  void contextLoads() {
  }

  @Test
  void noTxTest1() {
    sampleNoTxService.saveOne();
  }

  @Test
  void txTest1() {
    sampleTxService.saveOne();
  }

  @Test
  void txTest2() {
    sampleTxService.saveTwo();
  }

  @Test
  void txTest3() {
    sampleTxService.saveTwoError();
  }

  @Test
  void noTxTest2() {
    sampleNoTxService.saveTwo();
  }

  @Test
  void noTxTest3() {
    sampleNoTxService.saveTwoError();
  }

  @Test
  void txNestTest1() {
    sampleTxNestService.saveTxTwoX2();
  }

  @Test
  void txNestTest2() {
    sampleTxNestService.saveNoTxTwoX2();
  }

  @Test
  void txNestTest3() {
    sampleTxNestService.saveOneX2Complex();
  }

}
