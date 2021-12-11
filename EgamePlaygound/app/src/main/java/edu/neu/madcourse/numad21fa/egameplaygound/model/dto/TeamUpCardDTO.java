package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

public class TeamUpCardDTO {
    private String uuid;
    private String location;
    private String description;
    private UserInfoDTO creatorUser;
    private long timestamp;

    public TeamUpCardDTO() {
        uuid = "";
        location = "";
        description = "";
        creatorUser = new UserInfoDTO();
        timestamp = 0;
    }

    public String getUuid() {
        return uuid;
    }

    public TeamUpCardDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public TeamUpCardDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TeamUpCardDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public TeamUpCardDTO setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public UserInfoDTO getCreatorUser() {
        return creatorUser;
    }

    public TeamUpCardDTO setCreatorUser(UserInfoDTO creatorUser) {
        this.creatorUser = creatorUser;
        return this;
    }
}
