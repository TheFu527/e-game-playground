package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.me.UserInfo;

public class UserInfoDTO {
    private String uuid;
    private String name;
    private String avatarURI;
    private UserGenderEnum gender;
    private UserLevelEnum level;
    private String email;
    private String location;
    private String tokenid;

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uuid);
        result.put("name", name);
        result.put("avatarURI", avatarURI);
        result.put("gender", gender);
        result.put("level", level);
        result.put("email", email);
        result.put("location", location);
        result.put("tokenid", tokenid);
        return result;
    }
    public UserInfoDTO(String uuid,String name,String avatarURI, UserGenderEnum gender,UserLevelEnum level,String email,String location,String tokenid) {
        this.uuid = uuid;
        this.name = name;
        this.avatarURI = avatarURI;
        this.gender = gender;
        this.level = level;
        this.email = email;
        this.location = location;
        this.tokenid = tokenid;
    }


    public UserInfoDTO() {
        this.uuid = "";
        this.name = "";
        this.avatarURI = "";
        this.gender = UserGenderEnum.UNKNOWN;
        this.level = UserLevelEnum.UNKNOWN;
        this.email = "";
        this.location = "";
        this.tokenid = "";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoDTO that = (UserInfoDTO) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(avatarURI, that.avatarURI) && gender == that.gender && level == that.level && Objects.equals(email, that.email) && Objects.equals(location, that.location) && Objects.equals(tokenid, that.tokenid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, avatarURI, gender, level, email, location, tokenid);
    }


    public UserInfoDTO setTokenid(String tokenid) {
        this.tokenid = tokenid;
        return this;
    }


    public String getTokenid() {
        return tokenid;
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
