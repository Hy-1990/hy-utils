package com.bigdata.hytest.service;

import com.bigdata.hytest.TestFather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: hy-utils @ClassName: SaleServiceTest @Author: huyi @Date: 2020-09-19
 * 22:58 @Description: @Version: V1.0
 */
class SaleServiceTest extends TestFather {

  @Autowired SaleService saleService;

  @Test
  void saveSale() {
    saleService.saveSale(0.93);
  }

  @Test
  void findStudents() {
    System.out.println(saleService.findStudents());
  }
}
