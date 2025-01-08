package com.shihuanx.diary.service.impl;

import com.shihuanx.diary.entity.DiaryTag;
import com.shihuanx.diary.entity.Tag;
import com.shihuanx.diary.mapper.DiaryMapper;
import com.shihuanx.diary.mapper.DiaryTagMapper;
import com.shihuanx.diary.mapper.TagMapper;
import com.shihuanx.diary.service.TagService;
import com.shihuanx.diary.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private DiaryMapper diaryMapper;
    @Autowired
    private DiaryTagMapper diaryTagMapper;

    //向对应的日记添加标签 支持批量操作
    @Override
    @Transactional
    public void addTagsByDiaryId(List<Tag> tags, Integer diaryId) {
        DiaryTag diaryTag = new DiaryTag();
        Integer userId = UserHolder.getUser().getId();
//        Diary diary = diaryMapper.getDiaryByDiaryId(diaryId,userId);
//        if(diary!=null&&diary.getStatus()==1){
            for (Tag tag : tags) {
                tag.setCreateTime(LocalDateTime.now());
                tag.setUpdateTime(LocalDateTime.now());
                tag.setStatus(1);
                tag.setUserId(userId);
                tagMapper.addTag(tag);
                diaryTag.setDiaryId(diaryId);
                diaryTag.setTagId(tag.getId());
                diaryTag.setStatus(1);
                diaryTag.setUserId(userId);
                diaryTagMapper.add(diaryTag);
            }
//        }
        }

    //删除日记下的标签 支持批量操作
    @Override
    @Transactional
    public void deleteTagsByTagIds(List<Integer> tagIds,Integer diaryId) {
        Integer userId = UserHolder.getUser().getId();
        tagMapper.deleteTagsByTagIds(tagIds,userId);
        diaryTagMapper.delete(tagIds,diaryId,userId);
    }

    //批量删除标签下的所有日记
    @Override
    @Transactional
    public void deleteDiariesByTags(List<Integer> tagIds) {
        Integer userId = UserHolder.getUser().getId();
        List<Integer> diaryIdsToBeDel =new ArrayList<>();
        for (Integer tagId : tagIds) {
            List<DiaryTag> getDiaryTagsByTagId = diaryTagMapper.getDiaryTagsByTagId(tagId,userId);

            //通过tagId得到所有的中间表关系 把这所有中间表关系中的diaryId添加到待删除的diaryId中
            for (DiaryTag diaryTag : getDiaryTagsByTagId) {
                diaryIdsToBeDel.add(diaryTag.getDiaryId());
            }

            //删除所有待删除的日记
            diaryMapper.deleteDiaryByDiaryIds(diaryIdsToBeDel,userId);

            //先把待删除的中间表关系封装到集合中 再删除
            List<DiaryTag> diaryTagsToBeDel= diaryTagMapper.getDiaryTagsByDiaryIds(diaryIdsToBeDel,userId);

            for(Integer diaryId : diaryIdsToBeDel){
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
    }

    //修改标签
    @Override
    public void updateTag(Tag tag) {
        tag.setUpdateTime(LocalDateTime.now());
        tag.setUserId(UserHolder.getUser().getId());
        tagMapper.updateTag(tag);
    }

    //根据输入的日记id判断日记是否存在 用户id是否相符 感觉没必要 查询日记时就已经做了判断
    //    @Override
    //    public Result ifDiaryExist(Integer diaryId) {
    //        Integer userId = UserHolder.getUser().getId();
    //        Diary diary = diaryMapper.getDiaryByDiaryId(diaryId,userId);
    //        if(diary!=null&&diary.getStatus()==1){
    //            return Result.success();
    //        }else {return Result.error("日记不存在");}
    //    }
}
