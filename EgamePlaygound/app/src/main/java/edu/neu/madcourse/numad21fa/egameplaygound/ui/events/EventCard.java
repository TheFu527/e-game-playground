package edu.neu.madcourse.numad21fa.egameplaygound.ui.events;

import android.icu.text.SimpleDateFormat;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class EventCard {
    private String uuid;
    private String team1;
    private String team2;
    private String team1score;
    private String team2score;
    private String team1Icon;
    private String team2Icon;
    private String timestamp;

    public EventCard() {
    }

    public String getUuid() {
        return uuid;
    }

    public EventCard setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam1score() {
        return team1score;
    }

    public String getTeam2score() {
        return team2score;
    }

    public String getTeam1IconURI() {
        return team1Icon;
    }

    public String getTeam2IconURI() {
        return team2Icon;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public EventCard setTimestamp(long timestamp) {
        SimpleDateFormat fmt= new SimpleDateFormat("yy-MM-dd HH:mm");
        this.timestamp = fmt.format(timestamp);
        return this;
    }

    public EventCard setTeam1(String team1) {
        this.team1 = team1;
        return this;
    }

    public EventCard setTeam2(String team2) {
        this.team2 = team2;
        return this;
    }

    public EventCard setTeam1score(String team1score) {
        this.team1score = team1score;
        return this;
    }

    public EventCard setTeam2score(String team2score) {
        this.team2score = team2score;
        return this;
    }

    public EventCard setTeam1Icon(String team1Icon) {
        this.team1Icon = team1Icon;
        return this;
    }

    public EventCard setTeam2Icon(String team2Icon) {
        this.team2Icon = team2Icon;
        return this;
    }

//    public EventCard setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//        return this;
//    }
}
