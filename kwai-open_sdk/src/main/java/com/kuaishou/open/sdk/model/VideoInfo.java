package com.kuaishou.open.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wuge <wuge@kuaishou.com>
 * Created on 2020-12-11
 */
public class VideoInfo {

    @JsonProperty("photo_id")
    private String photoId;

    private String caption;

    private String cover;

    @JsonProperty("play_url")
    private String playUrl;

    @JsonProperty("create_time")
    private Long createTime;

    @JsonProperty("like_count")
    private Long likeCount;

    @JsonProperty("comment_count")
    private Long commentCount;

    @JsonProperty("view_count")
    private Long viewCount;

    private Boolean pending;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VideoInfo{");
        sb.append("photoId='").append(photoId).append('\'');
        sb.append(", caption='").append(caption).append('\'');
        sb.append(", cover='").append(cover).append('\'');
        sb.append(", playUrl='").append(playUrl).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", pending=").append(pending);
        sb.append('}');
        return sb.toString();
    }
}
