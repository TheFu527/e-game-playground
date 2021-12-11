package edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentUserPiazzaBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentUserTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.CreateTeamUpCardDialogFragment;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpRecyclerViewAdapter;

public class UserPiazzaFragment extends Fragment {

    private FragmentUserPiazzaBinding binding;
    private RecyclerView piazzaRecyclerView;
    private PiazzaRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private DatabaseManager databaseManager;
    private FloatingActionButton createCard;
    private String userUuidForThisCardList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseManager = DatabaseManagerImpl.getInstance();
        binding = FragmentUserPiazzaBinding.inflate(inflater, container, false);
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
        piazzaRecyclerView = binding.userCardRecyclerViewPz;
        adapter = new PiazzaRecyclerViewAdapter();
        piazzaRecyclerView.setAdapter(adapter);
        piazzaRecyclerView.setLayoutManager(rLayoutManger);
        databaseManager.getPiazzaCardList(this, getCurrentUserUuid()).observe(getViewLifecycleOwner(),
                piazzaCardDTOS -> adapter.updatePiazzaCardList(piazzaCardDTOS));
    }

    private void initCreateCardFloatingActionButton() {
        createCard = binding.newCardFabPz;

        if (!userUuidForThisCardList.equals(getCurrentUserUuid())) {
            createCard.setVisibility(View.INVISIBLE);
        }

        createCard.setOnClickListener(v -> {
            Bundle myCardBundle = new Bundle();
            myCardBundle.putString("uuid", getCurrentUserUuid());
            DialogFragment createPiazzaCardDialogFragment = CreatePiazzaCardDialogFragment.newInstance(getCurrentUserUuid());
            createPiazzaCardDialogFragment.show(getActivity().getSupportFragmentManager(), "createPiazzaCard");
        });
    }

    private String getCurrentUserUuid() {
        return AuthenticationImpl.getInstance().getUserID();
    }
}
