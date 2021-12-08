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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentUserTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;
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
    private FloatingActionButton createCard;
    private String userUuidForThisCardList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseManager = DatabaseManagerImpl.getInstance();

        teamUpViewModel =
                new ViewModelProvider(this).get(TeamUpViewModel.class);

        binding = FragmentUserTeamupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userUuidForThisCardList = requireArguments().getString("uuid");
        initCreateCardFloatingActionButton();
        initRecyclerView();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecyclerView() {
        rLayoutManger = new LinearLayoutManager(getContext());
        teamUpRecyclerView = binding.userCardRecyclerView;
        adapter = new TeamUpRecyclerViewAdapter();
        teamUpRecyclerView.setAdapter(adapter);
        teamUpRecyclerView.setLayoutManager(rLayoutManger);
        databaseManager.getTeamUpCardList(this, userUuidForThisCardList)
                .observe(getViewLifecycleOwner(),
                        teamUpCardDTOs -> teamUpViewModel.updateTeamUpCardList(teamUpCardDTOs));

        teamUpViewModel.getTeamUpCard().observe(getViewLifecycleOwner(),
                teamUpCardList -> adapter.updateTeamUpCardList(teamUpCardList));
    }

    private void initCreateCardFloatingActionButton() {
        createCard = binding.newCardFab;

        if (!userUuidForThisCardList.equals(getCurrentUserUuid())) {
            createCard.setVisibility(View.INVISIBLE);
        }

        createCard.setOnClickListener(v -> {
            Bundle myCardBundle = new Bundle();
            myCardBundle.putString("uuid", getCurrentUserUuid());
            // TODO(Hao FU): create card by dialog.
        });
    }

    private String getCurrentUserUuid() {
        // TODO(Hao Fu): get real current user uuid by Zoe's api.
        return "";
    }
}