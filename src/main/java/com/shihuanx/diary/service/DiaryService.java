package com.shihuanx.diary.service;

import com.shihuanx.diary.entity.Diary;
import com.shihuanx.diary.response.Result;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface DiaryService {
    void addDiary(Diary diary);

    void deleteDiaryById(List<Integer> diaryIds);

    void updateDiary(Diary diary);

    List<Diary> getDiary(Integer pageSize, Integer pageNumber, String content, String tag, LocalDateTime start, LocalDateTime end, String order, String sortBy);

}
