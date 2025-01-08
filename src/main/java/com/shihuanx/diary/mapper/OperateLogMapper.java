package com.shihuanx.diary.mapper;

import com.shihuanx.diary.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    @Insert("insert into diary.operate_log (id, user_id, operate_time, class_name, method_name, param, return_value, cost_time)" +
            " values (#{id},#{userId},#{operateTime},#{className},#{methodName},#{params},#{returnValue},#{costTime});")
    void insert(OperateLog operateLog);
}
