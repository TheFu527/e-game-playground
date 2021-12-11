package edu.neu.madcourse.numad21fa.egameplaygound.ui.me;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentMeBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentResetLevelBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.Authentication;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link resetLevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class resetLevelFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public Button setlevel;
    private Spinner reset_level_spinner;
    final UserLevelEnum[] levelSelected = {UserLevelEnum.SILVER};
    private FragmentResetLevelBinding binding;


    public resetLevelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment resetLevelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static resetLevelFragment newInstance(String param1, String param2) {
        resetLevelFragment fragment = new resetLevelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResetLevelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get userinfo
        Authentication auth = AuthenticationImpl.getInstance();
        String uuid = auth.getUserID();

        //get level
        setlevel = (Button) binding.resetLevel;
        reset_level_spinner = (Spinner) binding.resetLevelSpinner;
        List<UserLevelEnum> levelList = Arrays.asList(UserLevelEnum.SILVER,UserLevelEnum.GOLD,UserLevelEnum.MASTER,UserLevelEnum.UNKNOWN);

        ArrayAdapter<UserLevelEnum> resetLevelAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,UserLevelEnum.values());
        reset_level_spinner.setAdapter(resetLevelAdapter);

        reset_level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                levelSelected[0] = levelList.get(i);
                //set level
                setlevel.setOnClickListener(new View.OnClickListener(){
                    //为找到的button设置监听
                    @Override
                    //重写onClick函数
                    public void onClick(View v){
                        DatabaseReference USERS_REF = FirebaseDatabase.getInstance().getReference("/users");
                        Log.i("level got:",USERS_REF.child(uuid).child("level").getKey());
                        USERS_REF.child(uuid).child("level").setValue(levelSelected[0]);
                        Log.i("reset level succeed!","wow~");
                        Toast.makeText(getActivity(),"reset level successfully",Toast.LENGTH_SHORT).show();

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Log.i("selected level:",levelSelected[0].toString());
        //set level
        setlevel.setOnClickListener(new View.OnClickListener(){
            //为找到的button设置监听
            @Override
            //重写onClick函数
            public void onClick(View v){
            }
        });
//        return inflater.inflate(R.layout.fragment_reset_level, container, false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}