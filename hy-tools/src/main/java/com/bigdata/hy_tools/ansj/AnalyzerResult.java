package com.bigdata.hy_tools.ansj;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Desc:
 * @Author: zhouguangsheng
 * @Date: create by 2020/5/19 16:21
 * @Modify By:
 */
@Setter
@Getter
@ToString
public class AnalyzerResult implements Serializable {

    private String name;

    private String nature;

    private Integer offe;

    private String realName;

    private String[] synonyms;
}
