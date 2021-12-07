package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

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

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentUserTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpCard;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpRecyclerViewAdapter;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpViewModel;

public class UserTeamUpFragment extends Fragment {

    private TeamUpViewModel teamUpViewModel;
    private FragmentUserTeamupBinding binding;
    private RecyclerView teamUpRecyclerView;
    private TeamUpRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private DatabaseManager databaseManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseManager = DatabaseManagerImpl.getInstance();

        teamUpViewModel =
                new ViewModelProvider(this).get(TeamUpViewModel.class);

        binding = FragmentUserTeamupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rLayoutManger = new LinearLayoutManager(getContext());
        teamUpRecyclerView = binding.userCardRecyclerView;
        adapter = new TeamUpRecyclerViewAdapter();
        teamUpRecyclerView.setAdapter(adapter);
        teamUpRecyclerView.setLayoutManager(rLayoutManger);

        databaseManager.getTeamUpCardList(this, requireArguments().getString("uuid"))
                .observe(getViewLifecycleOwner(),
                        teamUpCardDTOs -> teamUpViewModel.updateTeamUpCardList(teamUpCardDTOs));

        teamUpViewModel.getTeamUpCard().observe(getViewLifecycleOwner(),
                teamUpCardList -> adapter.updateTeamUpCardList(teamUpCardList));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}