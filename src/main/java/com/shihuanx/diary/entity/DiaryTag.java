package com.shihuanx.diary.entity;

public class DiaryTag {
    private Integer id;
    private Integer diaryId;
    private Integer tagId;
    private Integer userId;
    private Integer status;


    public DiaryTag() {}
    public DiaryTag(Integer id, Integer diaryId, Integer tagId, Integer status, Integer userId) {
        this.id = id;
        this.diaryId = diaryId;
        this.tagId = tagId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
