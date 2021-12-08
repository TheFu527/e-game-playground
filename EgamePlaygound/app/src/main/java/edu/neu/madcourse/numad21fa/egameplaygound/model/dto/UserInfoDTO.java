package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;

public class UserInfoDTO {
    private String uuid;
    private String name;
    private String avatarURI;
    private UserGenderEnum gender;
    private UserLevelEnum level;
    private String email;
    private String location;

    public UserInfoDTO() {
        this.uuid = "";
        this.name = "";
        this.avatarURI = "";
        this.gender = UserGenderEnum.UNKNOWN;
        this.level = UserLevelEnum.UNKNOWN;
        this.email = "";
        this.location = "";
    }

    public String getUuid() {
        return uuid;
    }

    public UserInfoDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public UserInfoDTO setAvatarURI(String avatarURI) {
        this.avatarURI = avatarURI;
        return this;
    }

    public UserGenderEnum getGender() {
        return gender;
    }

    public UserInfoDTO setGender(UserGenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public UserLevelEnum getLevel() {
        return level;
    }

    public UserInfoDTO setLevel(UserLevelEnum level) {
        this.level = level;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfoDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UserInfoDTO setLocation(String location) {
        this.location = location;
        return this;
    }
}
