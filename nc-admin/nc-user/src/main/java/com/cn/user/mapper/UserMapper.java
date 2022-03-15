package com.cn.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:9:02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
