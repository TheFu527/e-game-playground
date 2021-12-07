package edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public enum UserGenderEnum {
    MALE(R.drawable.ic_man_24dp),
    FEMALE(R.drawable.ic_woman_24dp),
    UNKNOWN(R.drawable.ic_woman_24dp);

    private final int icon;

    UserGenderEnum(int icon) {
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }
}
