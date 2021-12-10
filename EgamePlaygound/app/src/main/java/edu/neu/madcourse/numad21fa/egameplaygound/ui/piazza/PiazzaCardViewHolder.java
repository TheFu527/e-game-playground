package edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public class PiazzaCardViewHolder extends RecyclerView.ViewHolder {
    private final ImageView userAvatar;
    private final ImageView userGender;
    private final ImageView userLevel;
    private final TextView userName;
    private final TextView title;
    private final TextView content;
    private final TextView time;
    private final Button share;

    public PiazzaCardViewHolder(View view) {
        super(view);

        userAvatar = (ImageView) view.findViewById(R.id.card_user_avatar_img_pz);
        userGender = (ImageView) view.findViewById(R.id.card_user_gender_icon_pz);
        userLevel = (ImageView) view.findViewById(R.id.card_user_level_icon_pz);
        userName = (TextView) view.findViewById(R.id.card_user_name_pz);
        title = (TextView) view.findViewById(R.id.card_title_pz);
        content = (TextView) view.findViewById(R.id.card_content_pz);
        time = (TextView) view.findViewById(R.id.card_timestamp_pz);
        share = (Button) view.findViewById(R.id.card_share_button_pz);

    }

    public ImageView getUserAvatar() {
        return userAvatar;
    }

    public ImageView getUserGender() {
        return userGender;
    }

    public ImageView getUserLevel() {
        return userLevel;
    }

    public TextView getUserName() {
        return userName;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getContent() {
        return content;
    }

    public TextView getTime() {
        return time;
    }

    public Button getShare() {
        return share;
    }
}
