package edu.neu.madcourse.numad21fa.egameplaygound.ui.me;

import java.util.Objects;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class UserInfo {

    private String uuid;
    private String name;
    private String avatarURI;
    private UserGenderEnum gender;
    private UserLevelEnum level;
    private String email;
    private String location;
    private String tokenid;

    public UserInfo() {
        this.uuid = "";
        this.name = "";
        this.avatarURI = "";
        this.gender = UserGenderEnum.UNKNOWN;
        this.level = UserLevelEnum.UNKNOWN;
        this.email = "";
        this.location = "";
        this.tokenid = "";
    }


    public UserInfo setTokenid(String tokenid) {
        this.tokenid = tokenid;
        return this;
    }


    public String getTokenid() {
        return tokenid;
    }


    public String getUuid() {
        return uuid;
    }

    public UserInfo setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }



    public String getName() {
        return name;
    }

    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public UserInfo setAvatarURI(String avatarURI) {
        this.avatarURI = avatarURI;
        return this;
    }

    public UserGenderEnum getGender() {
        return gender;
    }

    public UserInfo setGender(UserGenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public UserLevelEnum getLevel() {
        return level;
    }

    public UserInfo setLevel(UserLevelEnum level) {
        this.level = level;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UserInfo setLocation(String location) {
        this.location = location;
        return this;
    }


}
