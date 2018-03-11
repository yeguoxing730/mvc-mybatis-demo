package com.mvc.service;

import com.mvc.entity.Student;

import java.util.List;

/**
 * Created by yeguo on 2018/3/11.
 */
public interface IStudentService {
    int deleteByPrimaryKey(byte[] uid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(byte[] uid);

    List<Student> selectByCondition(Student record);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

}
