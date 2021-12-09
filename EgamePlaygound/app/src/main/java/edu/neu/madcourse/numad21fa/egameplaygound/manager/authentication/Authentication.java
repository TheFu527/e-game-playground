package edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication;

import com.google.firebase.auth.FirebaseUser;

public interface Authentication {
    String getUserID();
    String getUserIDToken();
    FirebaseUser getUser();
}
