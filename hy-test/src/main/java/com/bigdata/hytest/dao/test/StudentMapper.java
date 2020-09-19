package com.bigdata.hytest.dao.test;

import com.bigdata.hytest.entity.test.Student;
import com.bigdata.hytest.entity.test.StudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
*@Program: hy-utils
*@ClassName: StudentMapper
*@Author: huyi
*@Date: 2020-09-19 22:42
*@Description: ${todo}
*@Version: V1.0
*/
@Mapper
public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertOrUpdate(Student record);

    int insertOrUpdateSelective(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    int updateBatch(List<Student> list);

    int updateBatchSelective(List<Student> list);

    int batchInsert(@Param("list") List<Student> list);
}