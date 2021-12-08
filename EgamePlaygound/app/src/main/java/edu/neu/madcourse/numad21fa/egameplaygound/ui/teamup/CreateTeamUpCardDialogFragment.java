package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Date;
import java.util.UUID;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class CreateTeamUpCardDialogFragment extends DialogFragment {
    private CreateTeamUpCardDialogFragmentListener listener;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static CreateTeamUpCardDialogFragment newInstance(String creatorUserUuid) {
        CreateTeamUpCardDialogFragment f = new CreateTeamUpCardDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("uuid", creatorUserUuid);
        f.setArguments(args);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setMessage(R.string.create_teamup_card)
                .setView(inflater.inflate(R.layout.create_teamup_card_dialog, null))
                .setPositiveButton(R.string.post, (dialog, id) -> {
                    listener.onDialogPositiveClick(CreateTeamUpCardDialogFragment.this);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    listener.onDialogNegativeClick(CreateTeamUpCardDialogFragment.this);
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CreateTeamUpCardDialogFragmentListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("Must implement CreateTeamUpCardDialogFragmentListener");
        }
    }

    public String getCreatorUserUuid() {
        return getArguments().getString("uuid");
    }

}