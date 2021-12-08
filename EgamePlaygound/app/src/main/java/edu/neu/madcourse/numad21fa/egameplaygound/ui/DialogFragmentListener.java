package edu.neu.madcourse.numad21fa.egameplaygound.ui;

import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.CreateTeamUpCardDialogFragment;

public interface DialogFragmentListener {
    void onCreateTeamUpCardDialogPositiveClick(CreateTeamUpCardDialogFragment dialog);
    void onCreateTeamUpCardDialogNegativeClick(CreateTeamUpCardDialogFragment dialog);
}
