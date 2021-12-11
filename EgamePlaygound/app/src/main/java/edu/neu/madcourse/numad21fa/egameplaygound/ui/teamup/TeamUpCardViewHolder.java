package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder).
 */
public class TeamUpCardViewHolder extends RecyclerView.ViewHolder {
    private final ImageView userAvatar;
    private final ImageView userGender;
    private final ImageView userLevel;
    private final TextView userName;
    private final TextView location;
    private final TextView description;
    private final TextView time;
    private final Button contactMe;

    public TeamUpCardViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View
        userAvatar = (ImageView) view.findViewById(R.id.card_user_avatar_img);
        userGender = (ImageView) view.findViewById(R.id.card_user_gender_icon);
        userLevel = (ImageView) view.findViewById(R.id.card_user_level_icon);
        userName = (TextView) view.findViewById(R.id.card_user_name_tv);
        location = (TextView) view.findViewById(R.id.card_location_tv);
        time = (TextView) view.findViewById(R.id.card_timestamp_tv);
        description = (TextView) view.findViewById(R.id.card_description_tv);
        contactMe = (Button) view.findViewById(R.id.card_contact_me_button);

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

    public TextView getLocation() {
        return location;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getTime() {
        return time;
    }

    public Button getContactMe() {
        return contactMe;
    }
}
