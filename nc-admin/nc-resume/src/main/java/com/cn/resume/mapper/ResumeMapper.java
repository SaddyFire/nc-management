package com.cn.resume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.model.pojo.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:11:12
 */
@Mapper
public interface ResumeMapper extends BaseMapper<Resume> {
    @Select("select * from nc_resume  ORDER BY #{type} #{rule} limit #{page} ,#{pagesize}")
    List<Resume> findByCondition(@Param("page") Integer page,@Param("pagesize") Integer pagesize,@Param("type") String type, @Param("rule")String rule);

}
