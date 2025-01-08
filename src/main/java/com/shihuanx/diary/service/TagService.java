package com.shihuanx.diary.service;

import com.shihuanx.diary.entity.Tag;
import com.shihuanx.diary.response.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    void addTagsByDiaryId(List<Tag> tags, Integer diaryId);

    void deleteTagsByTagIds(List<Integer> tagIds, Integer diaryId);

    void deleteDiariesByTags(List<Integer> tagIds);

    void updateTag(Tag tag);

    //Result ifDiaryExist(Integer diaryId);
}
