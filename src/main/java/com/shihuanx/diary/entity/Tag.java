package com.shihuanx.diary.entity;

import java.time.LocalDateTime;

public class Tag {
    private Integer id;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer userId;
    private Integer status;

    public Tag() {}
    public Tag(Integer id, String content, LocalDateTime createTime, LocalDateTime updateTime, Integer userId, Integer status) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public LocalDateTime getCreateTime() {return createTime;}

    public void setCreateTime(LocalDateTime createTime) {this.createTime = createTime;}

    public LocalDateTime getUpdateTime() {return updateTime;}

    public void setUpdateTime(LocalDateTime updateTime) {this.updateTime = updateTime;}

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public Integer getStatus() {return status;}

    public void setStatus(Integer status) {this.status = status;}
}
