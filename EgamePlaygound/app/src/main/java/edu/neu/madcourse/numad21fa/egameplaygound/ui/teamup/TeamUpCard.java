package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class TeamUpCard {
    private final String uuid;
    private final UserInfoDTO userInfo;
    private final String description;
    private final String timestamp;
    private final String location;

    public TeamUpCard(String uuid, UserInfoDTO userInfo, String description, String timestamp, String location) {
        this.uuid = uuid;
        this.userInfo = userInfo;
        this.description = description;
        this.timestamp = timestamp;
        this.location = location;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public String getUserName() {
        return userInfo.getName();
    }

    public String getUserUuid() {
        return userInfo.getUuid();
    }

    public String getUserEmail() {
        return userInfo.getEmail();
    }

    public String getUserAvatar() {
        return userInfo.getAvatarURI();
    }

    public UserLevelEnum getUserLevel() {
        return userInfo.getLevel();
    }

    public UserGenderEnum getUserGender() {
        return userInfo.getGender();
    }
}
