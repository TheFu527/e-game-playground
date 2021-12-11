package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.icu.text.SimpleDateFormat;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class TeamUpCard {
    private String uuid;
    private UserInfoDTO userInfo;
    private String description;
    private String timestamp;
    private String location;

    public TeamUpCard() {
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

    public TeamUpCard setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public TeamUpCard setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public TeamUpCard setDescription(String description) {
        this.description = description;
        return this;
    }

    public TeamUpCard setTimestamp(long timestamp) {
        SimpleDateFormat fmt= new SimpleDateFormat("yy-MM-dd HH:mm");
        this.timestamp = fmt.format(timestamp);
        return this;
    }

    public TeamUpCard setLocation(String location) {
        this.location = location;
        return this;
    }
}
