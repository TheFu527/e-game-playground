package edu.neu.madcourse.numad21fa.egameplaygound.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Date;
import java.util.UUID;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.ActivityNavigationBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.CreateTeamUpCardDialogFragment;

public class NavigationActivity extends AppCompatActivity implements DialogFragmentListener {

    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_events,
                R.id.navigation_piazza,
                R.id.navigation_teamup,
                R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public void onCreateTeamUpCardDialogPositiveClick(CreateTeamUpCardDialogFragment dialog) {
        Dialog dialogView = dialog.getDialog();
        EditText descriptionEt = (EditText) dialogView.findViewById(R.id.description);



        DatabaseManagerImpl.getInstance()
                .getUserInfo(this, dialog.getCreatorUserUuid())
                .observe(this, new Observer<UserInfoDTO>() {
                    @Override
                    public void onChanged(UserInfoDTO userInfoDTO) {
                        DatabaseManagerImpl.getInstance()
                                .insertTeamUpCard(new TeamUpCardDTO().setCreatorUser(userInfoDTO)
                                        .setDescription(descriptionEt.getText().toString())
                                        .setLocation(userInfoDTO.getLocation())
                                        .setTimestamp(new Date(System.currentTimeMillis()).toString())
                                        .setUuid(UUID.randomUUID().toString()));
                        DatabaseManagerImpl.getInstance()
                                .getUserInfo(NavigationActivity.this, dialog.getCreatorUserUuid())
                                .removeObserver(this);
                    }
                });
    }

    @Override
    public void onCreateTeamUpCardDialogNegativeClick(CreateTeamUpCardDialogFragment dialog) {
        Snackbar.make(findViewById(R.id.container), "Post Team Up Card Canceled", Snackbar.LENGTH_SHORT)
                .show();
    }
}