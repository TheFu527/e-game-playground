package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

public class PiazzaCardDTO {
    private String uuid;
    private String title;
    private String content;
    private UserInfoDTO creatorUser;
    private long timestamp;

    public PiazzaCardDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public PiazzaCardDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PiazzaCardDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PiazzaCardDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public UserInfoDTO getCreatorUser() {
        return creatorUser;
    }

    public PiazzaCardDTO setCreatorUser(UserInfoDTO creatorUser) {
        this.creatorUser = creatorUser;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public PiazzaCardDTO setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
