package com.bigdata.hy_tools.annotation;

import java.lang.annotation.*;

/**
 * @author huyi
 * @date 2020/5/26 10:21
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String detail() default "";
}
