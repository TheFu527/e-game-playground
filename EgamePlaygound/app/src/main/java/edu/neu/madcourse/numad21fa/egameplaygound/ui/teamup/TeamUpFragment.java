package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;

public class TeamUpFragment extends Fragment {

    private FragmentTeamupBinding binding;
    private RecyclerView teamUpRecyclerView;
    private TeamUpRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private DatabaseManager databaseManager;
    private Button myCard;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseManager = DatabaseManagerImpl.getInstance();

        binding = FragmentTeamupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initRecyclerView();
        initMyCardButton();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecyclerView() {
        rLayoutManger = new LinearLayoutManager(getContext());
        teamUpRecyclerView = binding.cardRecyclerView;
        adapter = new TeamUpRecyclerViewAdapter();
        teamUpRecyclerView.setAdapter(adapter);
        teamUpRecyclerView.setLayoutManager(rLayoutManger);
        databaseManager.getTeamUpCardList(this).observe(getViewLifecycleOwner(),
                teamUpCardDTOS -> adapter.updateTeamUpCardList(teamUpCardDTOS));
    }

    private void initMyCardButton() {
        myCard = binding.myCardButton;
        myCard.setOnClickListener(v -> {
            Bundle myCardBundle = new Bundle();
            myCardBundle.putString("uuid", AuthenticationImpl.getInstance().getUserID());
            NavHostFragment.findNavController(TeamUpFragment.this)
                    .navigate(R.id.navigation_user_teamup, myCardBundle);
        });
    }
}