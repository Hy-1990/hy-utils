package com.bigdata.research;

import com.alibaba.fastjson.JSON;

import java.util.function.Function;

/**
 * @Author huyi
 * @Date 2021/2/6 15:38
 * @Description:
 */
public class ResearchDispatch extends Dispatch<StudentParam>{
    private static final String LABEL = "火焰";
    @Override
    public String get(Function<String, StudentParam> function) {
        StudentParam studentParam = function.apply(LABEL);
        return JSON.toJSONString(studentParam);
    }

  public static void main(String[] args) {
    // 测试
    System.out.println(new ResearchDispatch().get(x -> {
        StudentParam studentParam = StudentParam.builder().label(x+"hahahah").teacher("张老师").build();
        studentParam.setAge(100);
        studentParam.setCode("asdasdas123123asd");
        studentParam.setName("团团");
        return studentParam;
    }));
  }
}
