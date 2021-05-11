package com.bigdata.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/** @Author huyi @Date 2021/3/22 14:51 @Description: */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestEntity {
  private Map<Integer, String> map;
  private String name;
}
