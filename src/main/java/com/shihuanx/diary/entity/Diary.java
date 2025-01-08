package com.shihuanx.diary.entity;

import java.time.LocalDateTime;
import java.util.List;


public class Diary {
    private Integer id;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer userId;
    private Integer status;
    private List<Tag> tags;

    public Diary() {}
    public Diary(String content, LocalDateTime createTime, LocalDateTime updateTime, Integer userId, Integer status) {
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
