package edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user;

import android.graphics.Color;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public enum UserLevelEnum {
    SILVER(R.drawable.ic_silver_24dp, Color.parseColor("#C5C5C5")),
    GOLD(R.drawable.ic_gold_24dp, Color.parseColor("#FFC300")),
    MASTER(R.drawable.ic_master_24dp, Color.parseColor("#C70039")),
    UNKNOWN(R.drawable.ic_silver_24dp, Color.parseColor("#C5C5C5"));

    private final int icon;
    private final int color;

    UserLevelEnum(int icon, int color) {
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
