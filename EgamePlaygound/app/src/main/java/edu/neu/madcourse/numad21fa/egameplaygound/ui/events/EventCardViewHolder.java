package edu.neu.madcourse.numad21fa.egameplaygound.ui.events;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public class EventCardViewHolder extends RecyclerView.ViewHolder {
    private final ImageView teamImg1;
    private final ImageView teamImg2;
    private final TextView teamName1;
    private final TextView teamName2;
    private final TextView score1;
    private final TextView score2;
    private final TextView card_timestamp_event;
    private final Button visit_website_button;

    public EventCardViewHolder(View view) {
        super(view);

        teamImg1 = (ImageView) view.findViewById(R.id.teamImg1);
        teamImg2 = (ImageView) view.findViewById(R.id.teamImg2);
        teamName1 = (TextView) view.findViewById(R.id.teamName1);
        teamName2 = (TextView) view.findViewById(R.id.teamName2);
        score1 = (TextView) view.findViewById(R.id.score1);
        score2 = (TextView) view.findViewById(R.id.score2);
        card_timestamp_event = (TextView) view.findViewById(R.id.card_timestamp_event);
        visit_website_button = (Button) view.findViewById(R.id.visit_website_button);

    }

    public ImageView getTeamImg1() {
        return teamImg1;
    }

    public ImageView getTeamImg2() {
        return teamImg2;
    }

    public TextView getTeamName1() {
        return teamName1;
    }

    public TextView getTeamName2() {
        return teamName2;
    }

    public TextView getScore1() {
        return score1;
    }

    public TextView getScore2() {
        return score2;
    }

    public TextView getCard_timestamp_ev() {
        return card_timestamp_event;
    }
}
