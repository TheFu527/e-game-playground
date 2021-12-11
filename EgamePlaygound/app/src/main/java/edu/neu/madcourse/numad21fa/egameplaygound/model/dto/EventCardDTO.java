package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

public class EventCardDTO {
    private String uuid;
    private String team1;
    private String team2;
    private String teamscore1;
    private String teamscore2;
    private long timestamp;

    public EventCardDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public EventCardDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public EventCardDTO setTeam1(String team) {
        this.team1 = team;
        return this;
    }

    public String getTeam1() {
        return team1;
    }

    public EventCardDTO setTeam2(String team) {
        this.team2 = team;
        return this;
    }

    public String getTeam2() {
        return team2;
    }

    public EventCardDTO setTeamscore1(String team) {
        this.teamscore1 = team;
        return this;
    }

    public String getTeamscore1() {
        return teamscore1;
    }

    public EventCardDTO setTeamscore2(String team) {
        this.teamscore1 = team;
        return this;
    }

    public String getTeamscore2() {
        return teamscore2;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public EventCardDTO setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
