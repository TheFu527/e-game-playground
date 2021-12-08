package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;

public class TeamUpFragment extends Fragment {

    private TeamUpViewModel teamUpViewModel;
    private FragmentTeamupBinding binding;
    private RecyclerView teamUpRecyclerView;
    private TeamUpRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private DatabaseManager databaseManager;
    private Button myCard;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseManager = DatabaseManagerImpl.getInstance();

        teamUpViewModel =
                new ViewModelProvider(this).get(TeamUpViewModel.class);

        binding = FragmentTeamupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTeamup;
        teamUpViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        rLayoutManger = new LinearLayoutManager(getContext());
        teamUpRecyclerView = binding.cardRecyclerView;
        adapter = new TeamUpRecyclerViewAdapter();
        teamUpRecyclerView.setAdapter(adapter);
        teamUpRecyclerView.setLayoutManager(rLayoutManger);

        databaseManager.getTeamUpCardList(this).observe(getViewLifecycleOwner(), teamUpCardDTOS -> teamUpViewModel.updateTeamUpCardList(teamUpCardDTOS));

        teamUpViewModel.getTeamUpCard().observe(getViewLifecycleOwner(), teamUpCardList -> adapter.updateTeamUpCardList(teamUpCardList));

        myCard = root.findViewById(R.id.my_card_button);
        myCard.setOnClickListener(v -> {
            Bundle myCardBundle = new Bundle();
            myCardBundle.putString("uuid", AuthenticationImpl.getInstance().getUserID());
            NavHostFragment.findNavController(TeamUpFragment.this)
                    .navigate(R.id.navigation_user_teamup, myCardBundle);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}