package edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza;

import android.app.Activity;
import android.content.Intent;
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
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.PiazzaCardDTO;



public class PiazzaRecyclerViewAdapter extends RecyclerView.Adapter<PiazzaCardViewHolder> {
    private final String CONTACT_EMAIL_SUBJECT = "[E-game Playground] This is a Funny post!";
    private final String CONTACT_EMAIL_TEXT = "Find me in the Piazza!";

    private List<PiazzaCard> piazzaCardList;

    public PiazzaRecyclerViewAdapter() {
        this.piazzaCardList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PiazzaCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.piazza_card, viewGroup, false);

        return new PiazzaCardViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PiazzaCardViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        PiazzaCard card = piazzaCardList.get(position);
        Log.i("=========", String.format("name: %s", card.getUserName()));
        Log.i("=========", String.format("Title: %s", card.getTitle()));
        Log.i("=========", String.format("Content: %s", card.getContent()));

        setCard(viewHolder, card);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return piazzaCardList.size();
    }

    public void updatePiazzaCardList(final List<PiazzaCardDTO> piazzaCardDTOList) {
        this.piazzaCardList.clear();
        for (PiazzaCardDTO t : piazzaCardDTOList) {
            piazzaCardList.add(new PiazzaCard()
                    .setTitle(t.getTitle())
                    .setTimestamp(t.getTimestamp())
                    .setUserInfo(t.getCreatorUser())
                    .setContent(t.getContent())
                    .setUuid(t.getUuid()));
        }
        piazzaCardList.sort((o1, o2) -> -o1.getTimestamp().compareTo(o2.getTimestamp()));
        notifyDataSetChanged();
    }

    private void setCard(PiazzaCardViewHolder viewHolder, PiazzaCard card) {
        viewHolder.getUserName().setText(card.getUserName());
        viewHolder.getTitle().setText(card.getTitle());
        viewHolder.getContent().setText(card.getContent());
        viewHolder.getTime().setText(card.getTimestamp());
        StorageManagerImpl.getInstance()
                .loadImageIntoImageView(viewHolder.getUserAvatar().getContext(),
                        card.getUserAvatar(), viewHolder.getUserAvatar());
        viewHolder.getUserLevel().setImageResource(card.getUserLevel().getIcon());
        viewHolder.getUserLevel().setColorFilter(card.getUserLevel().getColor());
        viewHolder.getUserGender().setImageResource(card.getUserGender().getIcon());
        viewHolder.getUserGender().setColorFilter(card.getUserGender().getColor());
        viewHolder.getShare().setOnClickListener(v -> {
            Intent emailIntent = new Intent();
            emailIntent.setAction(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
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
