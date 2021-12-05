package edu.neu.madcourse.numad21fa.egameplaygound.model.dto;

public class ExampleDTO {
    private String uuid;
    private String name;

    public ExampleDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public ExampleDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExampleDTO setName(String name) {
        this.name = name;
        return this;
    }
}
