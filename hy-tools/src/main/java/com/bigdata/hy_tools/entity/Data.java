package com.bigdata.hy_tools.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


/**
 * @Program: hy-utils @ClassName: Data @Author: huyi @Date: 2020-08-22 16:18 @Description: @Version:
 * V1.0
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {
  private String name;
  private Integer money;
}
