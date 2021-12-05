package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.ExampleDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;

public class TeamUpFragment extends Fragment {

    private TeamUpViewModel teamUpViewModel;
    private FragmentTeamupBinding binding;
    private RecyclerView teamUpRecyclerView;
    private TeamUpRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private DatabaseManager databaseManager;


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

        databaseManager.getTeamUpCardList(this).observe(getViewLifecycleOwner(), new Observer<List<TeamUpCardDTO>>() {
            @Override
            public void onChanged(List<TeamUpCardDTO> teamUpCardDTOS) {
                teamUpViewModel.updateTeamUpCardList(teamUpCardDTOS);
            }
        });

        teamUpViewModel.getTeamUpCard().observe(getViewLifecycleOwner(), new Observer<List<TeamUpCard>>() {
            @Override
            public void onChanged(List<TeamUpCard> teamUpCardList) {
                adapter.updateTeamUpCardList(teamUpCardList);
            }
        });

        databaseManager.getExampleList(this).observe(getViewLifecycleOwner(), new Observer<List<ExampleDTO>>() {
            @Override
            public void onChanged(List<ExampleDTO> exampleDTOList) {
                adapter.updateExamplesList(exampleDTOList);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}