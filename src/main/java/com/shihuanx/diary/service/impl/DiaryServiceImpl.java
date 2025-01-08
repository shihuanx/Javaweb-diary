package com.shihuanx.diary.service.impl;

import com.shihuanx.diary.entity.Diary;
import com.shihuanx.diary.entity.DiaryTag;
import com.shihuanx.diary.mapper.DiaryMapper;
import com.shihuanx.diary.mapper.DiaryTagMapper;
import com.shihuanx.diary.mapper.TagMapper;
import com.shihuanx.diary.service.DiaryService;
import com.shihuanx.diary.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryMapper diaryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private DiaryTagMapper diaryTagMapper;

    //添加日记
    @Override
    public void addDiary(Diary diary) {
        diary.setCreateTime(LocalDateTime.now());
        diary.setUpdateTime(LocalDateTime.now());
        diary.setStatus(1);
        diary.setUserId(UserHolder.getUser().getId());
        diaryMapper.addDiary(diary);
    }

    //根据日记id批量删除日记
    @Transactional
    @Override
    public void deleteDiaryById(List<Integer> diaryIds) {
        Integer userId = UserHolder.getUser().getId();
        //删除日记
        diaryMapper.deleteDiaryByDiaryIds(diaryIds,userId);
        //通过日记的id得到所有待删除的中间表关系
        List<DiaryTag> diaryTagsToBeDel =diaryTagMapper.getDiaryTagsByDiaryIds(diaryIds,userId);
        //删除中间表关系
        for (Integer diaryId : diaryIds) {
            diaryTagMapper.deleteByDiaryId(diaryId,userId);
        }
        //通过已经删除的中间表关系得到所有标签的id 由于使用的假删除 故中间表的实体属性还在 可以用get方法得到tagId
        //并通过标签id删除没有对应日记的标签
        for(DiaryTag diaryTag:diaryTagsToBeDel){
            if(diaryTagMapper.selectByTagId(diaryTag.getTagId(),userId).isEmpty()){
                tagMapper.deleteTag(diaryTag.getTagId(),userId);
            }
        }
    }

    //修改日记
    @Override
    public void updateDiary(Diary diary) {
        diary.setUpdateTime(LocalDateTime.now());
        diary.setUserId(UserHolder.getUser().getId());
        diaryMapper.updateDiary(diary);
    }

    //通过日记内容、标签内容、时间范围查询日记及其标签 支持分页查询和模糊查询
    @Override
    public List<Diary> getDiary(Integer pageSize, Integer pageNumber, String content, String tag, LocalDateTime start, LocalDateTime end, String order, String sortBy) {
        Integer userId = UserHolder.getUser().getId();
        Integer startIndex = pageSize * (pageNumber - 1);  //起始索引
        List<Diary> diaryList =diaryMapper.getDiaryListByCondition(pageSize,startIndex,content,tag,start,end,order,sortBy,userId);
        for(Diary diary:diaryList){
           diary.setTags(tagMapper.getTagsByDiaryId(diary.getId(),userId));
       }
        return diaryList;
    }

}
