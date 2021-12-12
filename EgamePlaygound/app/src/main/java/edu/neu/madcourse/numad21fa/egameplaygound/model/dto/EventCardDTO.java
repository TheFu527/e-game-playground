package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

public class EventCardDTO {
    private String uuid;
    private String team1;
    private String team2;
    private String team1score;
    private String team2score;
    private String team1Icon;
    private String team2Icon;
    private long timestamp;

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

    public String getTeam1Icon() {
        return team1Icon;
    }

    public String getTeam2Icon() {
        return team2Icon;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUuid() {
        return uuid;
    }


    public EventCardDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public EventCardDTO setTeam1(String teamname) {
        this.team1 = teamname;
        return this;
    }

    public EventCardDTO setTeam2(String teamname) {
        this.team2 = teamname;
        return this;
    }

    public EventCardDTO setTeam1score(String teamscore) {
        this.team1score = teamscore;
        return this;
    }

    public EventCardDTO setTeam2score(String teamscore) {
        this.team2score = teamscore;
        return this;
    }

    public EventCardDTO setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public EventCardDTO setTeam1Icon(String team1Icon) {
        this.team1Icon = team1Icon;
        return this;
    }

    public EventCardDTO setTeam2Icon(String team2Icon) {
        this.team2Icon = team2Icon;
        return this;
    }
}
