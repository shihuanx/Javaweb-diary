package com.shihuanx.diary.mapper;

import com.shihuanx.diary.entity.DiaryTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DiaryTagMapper {

    @Insert("insert into diary_tag (diary_id, tag_id, status,user_id) values (#{diaryId},#{tagId},#{status},#{userId});")
    void add(DiaryTag diaryTag);

    void delete(List<Integer> tagIds,Integer diaryId,Integer userId);

    @Select("select * from diary_tag where tag_id=#{tagId} and user_id=#{userId} and status =1;")
    List<DiaryTag> getDiaryTagsByTagId(Integer tagId,Integer userId);

    @Update("update diary_tag set status = 0 where diary_id = #{diaryId} and user_id=#{userId}")
    void deleteByDiaryId(Integer diaryId,Integer userId);

    @Select("select * from diary_tag where tag_id = #{tagId} and status=1 and user_id=#{userId};")
    List<DiaryTag> selectByTagId(Integer tagId,Integer userId);

    List<DiaryTag> getDiaryTagsByDiaryIds(List<Integer> diaryIds, Integer userId);
}
