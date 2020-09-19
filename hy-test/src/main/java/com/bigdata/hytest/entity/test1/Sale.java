package com.bigdata.hytest.entity.test1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@Program: hy-utils
*@ClassName: Sale
*@Author: huyi
*@Date: 2020-09-19 22:48
*@Description: ${todo}
*@Version: V1.0
*/
@ApiModel(value="com-bigdata-hytest-entity-test1-Sale")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sale implements Serializable {
    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="")
    private Double money;

    @ApiModelProperty(value="")
    private String remark;

    private static final long serialVersionUID = 1L;
}