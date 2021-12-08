package edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class AuthenticationImpl implements Authentication {
    private static final AuthenticationImpl instance = new AuthenticationImpl();
    private String Currentuserid;

    private AuthenticationImpl() {
        Currentuserid = "";
        getUserID();
    }

    public static AuthenticationImpl getInstance() {
        return instance;
    }

    @Override
    public FirebaseUser getUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Log.i("get current user id:",user.getUid());
            return user;
        }else{
            Log.i("get current user id:",null);
            return null;
        }
    }

    @Override
    public String getUserID() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Currentuserid = user.getUid();
            Log.i("get current user id:",Currentuserid);
            return Currentuserid;
        }else{
            Log.i("get current user id:","");
            return null;
        }

    }

    @Override
    public String getUserIDToken() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String token = user.getIdToken(false).toString();
            return token;
        }else{
            return null;
        }
    }
}
