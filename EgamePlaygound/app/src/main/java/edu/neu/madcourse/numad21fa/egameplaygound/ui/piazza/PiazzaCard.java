package edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza;

import android.icu.text.SimpleDateFormat;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class PiazzaCard {
    private String uuid;
    private String title;
    private String content;
    private UserInfoDTO userInfo;
    private String timestamp;

    public PiazzaCard() {
    }

    public String getUuid() {
        return uuid;
    }

    public PiazzaCard setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PiazzaCard setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PiazzaCard setContent(String content) {
        this.content = content;
        return this;
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

    public PiazzaCard setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public PiazzaCard setTimestamp(long timestamp) {
        SimpleDateFormat fmt= new SimpleDateFormat("yy-MM-dd HH:mm");
        this.timestamp = fmt.format(timestamp);
        return this;
    }
}
