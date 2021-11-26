package edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza;

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

import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentPiazzaBinding;

public class PiazzaFragment extends Fragment {

    private PiazzaViewModel piazzaViewModel;
    private FragmentPiazzaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        piazzaViewModel =
                new ViewModelProvider(this).get(PiazzaViewModel.class);

        binding = FragmentPiazzaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPiazza;
        piazzaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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