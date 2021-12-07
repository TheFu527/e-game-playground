package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.storage.StorageManagerImpl;

public class TeamUpRecyclerViewAdapter extends RecyclerView.Adapter<TeamUpCardViewHolder> {

    private final String CONTACT_EMAIL_SUBJECT = "[E-game Playground] Find you in Team Up!";
    private final String CONTACT_EMAIL_TEXT = "Let's play together!";

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

        setCard(viewHolder, card);

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

    private void setCard(TeamUpCardViewHolder viewHolder, TeamUpCard card) {
        viewHolder.getUserName().setText(card.getUserName());
        viewHolder.getDescription().setText(card.getDescription());
        viewHolder.getLocation().setText(card.getLocation());
        viewHolder.getTime().setText(card.getTimestamp());
        StorageManagerImpl.getInstance()
                .loadImageIntoImageView(viewHolder.itemView.getContext(),
                        card.getUserAvatar(), viewHolder.getUserAvatar());
        viewHolder.getUserLevel().setImageResource(card.getUserLevel().getIcon());
        viewHolder.getUserGender().setImageResource(card.getUserGender().getIcon());
        viewHolder.getContactMe().setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setDataAndType(Uri.parse("mailto:"), "text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{card.getUserEmail()});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, CONTACT_EMAIL_SUBJECT);
            emailIntent.putExtra(Intent.EXTRA_TEXT, CONTACT_EMAIL_TEXT);
            try {
                viewHolder.itemView.getContext().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                ((Activity)viewHolder.itemView.getContext()).finish();
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(viewHolder.itemView.getContext(),
                        "There is no email client installed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
