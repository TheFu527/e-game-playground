package edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user;

import android.graphics.Color;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public enum UserGenderEnum {
    MALE(R.drawable.ic_man_24dp, Color.parseColor("#3498DB")),
    FEMALE(R.drawable.ic_woman_24dp, Color.parseColor("#884EA0")),
    UNKNOWN(R.drawable.ic_woman_24dp, Color.parseColor("#884EA0"));

    private final int icon;
    private final int color;

    UserGenderEnum(int icon, int color) {
        this.icon = icon;
        this.color = color;
    }

    public int getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }
}
