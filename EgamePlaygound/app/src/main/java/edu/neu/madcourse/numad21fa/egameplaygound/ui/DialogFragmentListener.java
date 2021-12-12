package edu.neu.madcourse.numad21fa.egameplaygound.ui;

import edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza.CreatePiazzaCardDialogFragment;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.CreateTeamUpCardDialogFragment;

public interface DialogFragmentListener {
    void onCreateTeamUpCardDialogPositiveClick(CreateTeamUpCardDialogFragment dialog);
    void onCreateTeamUpCardDialogNegativeClick(CreateTeamUpCardDialogFragment dialog);
    void onCreatePiazzaCardDialogPositiveClick(CreatePiazzaCardDialogFragment dialog);
    void onCreatePiazzaCardDialogNegativeClick(CreatePiazzaCardDialogFragment dialog);
//    void onCreateEventCardDialogPositiveClick(CreateEventCardDialogFragment dialog);
//    void onCreateEventCardDialogNegativeClick(CreateEventCardDialogFragment dialog);
}
