package com.scoder.vin.web.api.domain.basic;

import java.util.Date;

public class Note {
    private Long id;

    private Long userId;

    private Long notebookId;

    private Long noteId;

    private Integer draftVersion;

    private Integer publishVersion;

    private Boolean isComment;

    private Boolean isShow;

    private Boolean isPublic;

    private Long pageVisitor;

    private Long uniqueVisitor;

    private Integer status;

    private Date firstPublishTime;

    private Date latestEditTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Long notebookId) {
        this.notebookId = notebookId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Integer getDraftVersion() {
        return draftVersion;
    }

    public void setDraftVersion(Integer draftVersion) {
        this.draftVersion = draftVersion;
    }

    public Integer getPublishVersion() {
        return publishVersion;
    }

    public void setPublishVersion(Integer publishVersion) {
        this.publishVersion = publishVersion;
    }

    public Boolean getIsComment() {
        return isComment;
    }

    public void setIsComment(Boolean isComment) {
        this.isComment = isComment;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Long getPageVisitor() {
        return pageVisitor;
    }

    public void setPageVisitor(Long pageVisitor) {
        this.pageVisitor = pageVisitor;
    }

    public Long getUniqueVisitor() {
        return uniqueVisitor;
    }

    public void setUniqueVisitor(Long uniqueVisitor) {
        this.uniqueVisitor = uniqueVisitor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFirstPublishTime() {
        return firstPublishTime;
    }

    public void setFirstPublishTime(Date firstPublishTime) {
        this.firstPublishTime = firstPublishTime;
    }

    public Date getLatestEditTime() {
        return latestEditTime;
    }

    public void setLatestEditTime(Date latestEditTime) {
        this.latestEditTime = latestEditTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}