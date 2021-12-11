package edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza;

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

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentPiazzaBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentTeamupBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpFragment;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpRecyclerViewAdapter;

public class PiazzaFragment extends Fragment {

    private FragmentPiazzaBinding binding;
    private RecyclerView piazzaRecyclerView;
    private PiazzaRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private DatabaseManager databaseManager;
    private Button myCard;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        databaseManager = DatabaseManagerImpl.getInstance();

        binding = FragmentPiazzaBinding.inflate(inflater, container, false);
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
        piazzaRecyclerView = binding.cardRecyclerViewPz;
        adapter = new PiazzaRecyclerViewAdapter();
        piazzaRecyclerView.setAdapter(adapter);
        piazzaRecyclerView.setLayoutManager(rLayoutManger);
        databaseManager.getPiazzaCardList(this).observe(getViewLifecycleOwner(),
                piazzaCardDTOS -> adapter.updatePiazzaCardList(piazzaCardDTOS));
    }

    private void initMyCardButton() {
        myCard = binding.myCardButtonPz;
        myCard.setOnClickListener(v -> {
            Bundle myCardBundle = new Bundle();
            myCardBundle.putString("uuid", AuthenticationImpl.getInstance().getUserID());
            NavHostFragment.findNavController(PiazzaFragment.this)
                    .navigate(R.id.navigation_user_piazza, myCardBundle);
        });
    }
}