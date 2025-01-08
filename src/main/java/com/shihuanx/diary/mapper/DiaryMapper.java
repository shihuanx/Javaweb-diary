package com.shihuanx.diary.mapper;

import com.shihuanx.diary.entity.Diary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DiaryMapper {
    @Insert("insert into diary (content,create_time,update_time,user_id,status) values " +
            "(#{content},#{createTime},#{updateTime},#{userId},#{status});")
    void addDiary(Diary diary);

    void deleteDiaryByDiaryIds(List<Integer> diaryIds, Integer userId);

    @Update("update diary set content = #{content} where diary.id=#{id} and user_id=#{userId};")
    void updateDiary(Diary diary);

    List<Diary> getDiaryListByCondition(Integer pageSize, Integer startIndex, String content, String tag,LocalDateTime start, LocalDateTime end, String order, String sortBy, Integer userId);

//    @Select("select * from diary.diary where id=#{diaryId} and user_id=#{userId};")
//    Diary getDiaryByDiaryId(Integer diaryId,Integer userId);
}
