package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

public class PiazzaCardDTO {
    private String uuid;
    private String content;
    private Integer numOfLikes;
    private UserInfoDTO creatorUser;
    private String timestamp;

    public PiazzaCardDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public PiazzaCardDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PiazzaCardDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getNumOfLikes() {
        return numOfLikes;
    }

    public PiazzaCardDTO setNumOfLikes(Integer numOfLikes) {
        this.numOfLikes = numOfLikes;
        return this;
    }

    public UserInfoDTO getCreatorUser() {
        return creatorUser;
    }

    public PiazzaCardDTO setCreatorUser(UserInfoDTO creatorUser) {
        this.creatorUser = creatorUser;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public PiazzaCardDTO setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
