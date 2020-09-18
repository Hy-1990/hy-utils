package com.bigdata.hytest.service;

import com.bigdata.hytest.TestFather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** @Author huyi @Date 2020/9/18 12:38 @Description: */
class TestRetryServiceTest extends TestFather {
  @Autowired TestRetryService testRetryService;

  @Test
  void printMsg() throws Exception {
    testRetryService.printMsg();
  }
}
