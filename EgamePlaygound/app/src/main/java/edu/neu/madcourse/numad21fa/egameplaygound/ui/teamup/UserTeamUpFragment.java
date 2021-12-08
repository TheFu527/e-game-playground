package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentUserTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;

public class UserTeamUpFragment extends Fragment {

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
//        databaseManager.getTeamUpCardList(this, userUuidForThisCardList)
//                .observe(getViewLifecycleOwner(),
//                        teamUpCardDTOs -> teamUpViewModel.updateTeamUpCardList(teamUpCardDTOs));

        databaseManager.getTeamUpCardList(this).observe(getViewLifecycleOwner(),
                teamUpCardDTOS -> adapter.updateTeamUpCardList(teamUpCardDTOS));
//        teamUpViewModel.getTeamUpCard().observe(getViewLifecycleOwner(),
//                teamUpCardList -> adapter.updateTeamUpCardList(teamUpCardList));
    }

    private void initCreateCardFloatingActionButton() {
        createCard = binding.newCardFab;

        if (!userUuidForThisCardList.equals(getCurrentUserUuid())) {
            createCard.setVisibility(View.INVISIBLE);
        }

        createCard.setOnClickListener(v -> {
            Bundle myCardBundle = new Bundle();
            myCardBundle.putString("uuid", getCurrentUserUuid());
            DialogFragment createTeamUpCardDialogFragment = CreateTeamUpCardDialogFragment.newInstance(getCurrentUserUuid());
            createTeamUpCardDialogFragment.show(getActivity().getSupportFragmentManager(), "createTeamUpCard");
        });
    }

    private String getCurrentUserUuid() {
        return AuthenticationImpl.getInstance().getUserID();
    }
}