package com.bigdata.hytest.entity.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@Program: hy-utils
*@ClassName: Student
*@Author: huyi
*@Date: 2020-09-19 22:42
*@Description: ${todo}
*@Version: V1.0
*/
@ApiModel(value="com-bigdata-hytest-entity-test-Student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Integer age;

    @ApiModelProperty(value="")
    private String desc;

    private static final long serialVersionUID = 1L;
}