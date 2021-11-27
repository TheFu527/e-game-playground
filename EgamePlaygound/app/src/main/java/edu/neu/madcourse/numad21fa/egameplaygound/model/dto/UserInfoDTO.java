package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;

public class UserInfoDTO {
    private final String uuid;
    private final String name;
    private final String avatarURI;
    private final UserGenderEnum gender;
    private final UserLevelEnum level;
    private final String email;

    public UserInfoDTO(String uuid, String name, String avatarURI, UserGenderEnum gender, UserLevelEnum level, String email) {
        this.uuid = uuid;
        this.name = name;
        this.avatarURI = avatarURI;
        this.gender = gender;
        this.level = level;
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public UserGenderEnum getGender() {
        return gender;
    }

    public UserLevelEnum getLevel() {
        return level;
    }

    public String getEmail() {
        return email;
    }
}
