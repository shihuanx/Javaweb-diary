package com.shihuanx.diary.request;

import com.shihuanx.diary.entity.Tag;

import java.util.List;

public class AddTagRequest {
    private List<Tag> tags;
    private Integer diaryId;

    public AddTagRequest() {}
    public AddTagRequest(List<Tag> tags, Integer diaryId) {
        this.tags = tags;
        this.diaryId = diaryId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }
}
