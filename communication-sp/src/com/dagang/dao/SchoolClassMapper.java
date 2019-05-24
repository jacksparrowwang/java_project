package com.dagang.dao;

import com.dagang.model.SchoolClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolClassMapper {

    public int insert(SchoolClass schoolClass);

    public List<SchoolClass> queryClassInfo(@Param("schoolAddress")String schoolAddress,
                                            @Param("schoolName")String schoolName,
                                            @Param("className")String className);

    public List<Integer> isExistClass(@Param("schoolAddress")String schoolAddress,
                                @Param("schoolName")String schoolName,
                                @Param("className")String className);

    public String queryClassNameById(Integer classId);

    public SchoolClass findClassInfoByClassId(Integer classId);
}
