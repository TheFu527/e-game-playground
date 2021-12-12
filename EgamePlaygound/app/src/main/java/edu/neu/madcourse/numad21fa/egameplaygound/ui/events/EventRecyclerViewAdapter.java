package edu.neu.madcourse.numad21fa.egameplaygound.ui.events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.storage.StorageManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.EventCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpCard;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpCardViewHolder;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventCardViewHolder> {

//    private final String CONTACT_EMAIL_SUBJECT = "[E-game Playground] Find you in Team Up!";
//    private final String CONTACT_EMAIL_TEXT = "Let's play together!";

    private List<EventCard> eventCardList;

    public EventRecyclerViewAdapter() {
        this.eventCardList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_card, viewGroup, false);

        return new EventCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventCardViewHolder viewHolder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // TODO(Hao Fu): set view
        EventCard card = eventCardList.get(position);
        Log.i("=========", String.format("team1: %s", card.getTeam1()));
        Log.i("=========", String.format("team2: %s", card.getTeam2()));
        Log.i("=========", String.format("timestamp: %s", card.getTimestamp()));

        setCard(viewHolder, card);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return eventCardList.size();
    }

    public void updateEventCardList(final List<EventCardDTO> eventCardDTOList) {
        this.eventCardList.clear();
        for (EventCardDTO t : eventCardDTOList) {
            eventCardList.add(new EventCard()
                    .setTeam1(t.getTeam1())
                    .setTeam2(t.getTeam2())
                    .setTeam1score(t.getTeam1score())
                    .setTeam2score(t.getTeam2score())
                    .setUuid(t.getUuid())
                    .setTeam1Icon(t.getTeam1Icon())
                    .setTeam2Icon(t.getTeam2Icon())
                    .setTimestamp(t.getTimestamp()));
        }
        eventCardList.sort((o1, o2) -> -o1.getTimestamp().compareTo(o2.getTimestamp()));
        notifyDataSetChanged();
    }

    private void setCard(EventCardViewHolder viewHolder, EventCard card) {
        viewHolder.getTeamName1().setText(card.getTeam1());
        viewHolder.getTeamName2().setText(card.getTeam2());
        viewHolder.getScore1().setText(card.getTeam1score());
        viewHolder.getScore2().setText(card.getTeam2score());
        viewHolder.getCard_timestamp_ev().setText(card.getTimestamp());
        StorageManagerImpl.getInstance()
                .loadImageIntoImageView(viewHolder.getTeamImg1().getContext(),
                        card.getTeam1IconURI(), viewHolder.getTeamImg1());
        StorageManagerImpl.getInstance()
                .loadImageIntoImageView(viewHolder.getTeamImg2().getContext(),
                        card.getTeam2IconURI(), viewHolder.getTeamImg2());
//        Bundle myCardBundle = new Bundle();
//        myCardBundle.putString("uuid", card.getUserUuid());
//        viewHolder.itemView
//                .setOnClickListener(Navigation.createNavigateOnClickListener(
//                        R.id.navigation_me, myCardBundle));
    }
}
