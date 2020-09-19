package com.bigdata.hytest.service;

import com.bigdata.hytest.dao.test.StudentMapper;
import com.bigdata.hytest.dao.test1.SaleMapper;
import com.bigdata.hytest.entity.test.Student;
import com.bigdata.hytest.entity.test.StudentExample;
import com.bigdata.hytest.entity.test1.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: hy-utils @ClassName: SaleService @Author: huyi @Date: 2020-09-19
 * 22:51 @Description: @Version: V1.0
 */
@Service
public class SaleService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private SaleMapper saleMapper;
  @Autowired private StudentMapper studentMapper;

  public void saveSale(Double money) {
    saleMapper.insert(Sale.builder().money(money).build());
  }

  public List<Student> findStudents() {
    return studentMapper.selectByExample(new StudentExample());
  }
}
