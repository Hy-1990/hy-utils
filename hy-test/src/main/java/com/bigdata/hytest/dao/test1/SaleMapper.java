package com.bigdata.hytest.dao.test1;

import com.bigdata.hytest.entity.test1.Sale;
import com.bigdata.hytest.entity.test1.SaleExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
*@Program: hy-utils
*@ClassName: SaleMapper
*@Author: huyi
*@Date: 2020-09-19 22:48
*@Description: ${todo}
*@Version: V1.0
*/
@Mapper
public interface SaleMapper {
    long countByExample(SaleExample example);

    int deleteByExample(SaleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sale record);

    int insertOrUpdate(Sale record);

    int insertOrUpdateSelective(Sale record);

    int insertSelective(Sale record);

    List<Sale> selectByExample(SaleExample example);

    Sale selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByExample(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);

    int updateBatch(List<Sale> list);

    int updateBatchSelective(List<Sale> list);

    int batchInsert(@Param("list") List<Sale> list);
}