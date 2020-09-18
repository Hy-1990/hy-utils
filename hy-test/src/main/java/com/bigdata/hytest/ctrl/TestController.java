package com.bigdata.hytest.ctrl;

import com.bigdata.hytest.service.TestRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** @Author huyi @Date 2020/9/18 12:44 @Description: */
@RestController
@CrossOrigin
public class TestController {
  @Autowired TestRetryService testRetryService;

  @GetMapping("/test")
  void test() throws Exception {
    testRetryService.printMsg();
  }
}
