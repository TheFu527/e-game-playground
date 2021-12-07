package edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public enum UserLevelEnum {
    SILVER(R.drawable.ic_silver_24dp),
    GOLD(R.drawable.ic_gold_24dp),
    MASTER(R.drawable.ic_master_24dp),
    UNKNOWN(R.drawable.ic_silver_24dp);

    private final int icon;
    UserLevelEnum(int icon) {
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

}
