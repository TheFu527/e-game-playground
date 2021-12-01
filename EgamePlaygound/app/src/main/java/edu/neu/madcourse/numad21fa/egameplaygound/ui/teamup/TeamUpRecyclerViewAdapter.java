package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public class TeamUpRecyclerViewAdapter extends RecyclerView.Adapter<TeamUpCardViewHolder> {

    private List<TeamUpCard> teamUpCardList;

    public TeamUpRecyclerViewAdapter() {
        this.teamUpCardList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TeamUpCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.teamup_card, viewGroup, false);

        return new TeamUpCardViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TeamUpCardViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // TODO(Hao Fu): set view
        TeamUpCard card = teamUpCardList.get(position);
        Log.i("=========", String.format("name: %s", card.getUserName()));
        Log.i("=========", String.format("description: %s", card.getDescription()));

        viewHolder.getUserName().setText(card.getUserName());
        viewHolder.getDescription().setText(card.getDescription());
        viewHolder.getLocation().setText(card.getLocation());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return teamUpCardList.size();
    }

    public void updateTeamUpCardList(final List<TeamUpCard> teamUpCardList) {
        this.teamUpCardList.clear();
        this.teamUpCardList = teamUpCardList;
        notifyDataSetChanged();
    }
}
