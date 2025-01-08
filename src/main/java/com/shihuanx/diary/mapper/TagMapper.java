package com.shihuanx.diary.mapper;

import com.shihuanx.diary.entity.Tag;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface TagMapper {

    List<Tag> getTagsByDiaryId(Integer diaryId, Integer userId);

    Integer addTag(Tag tag);

    void deleteTagsByTagIds(List<Integer> tagIds, Integer userId);

    @Update("update tag set status = 0 where id = #{tagId} and user_id=#{userId};")
    void deleteTag(Integer tagId, Integer userId);

    @Update("update tag set content = #{content} where id=#{id} and user_id = #{userId};")
    void updateTag(Tag tag);
}
