package edu.neu.madcourse.numad21fa.egameplaygound.ui;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class MainActivity extends AppCompatActivity
        implements LocationListener {

    private LocationManager locationManager;

    private List<String> enabledProviders;
    public static final int LOCATION_REQUEST_CODE = 1001; //Any number

    private TextView location_value;
    private TextView email;
    private String email_address;
    private Spinner level;

    private Spinner gender;

    private FirebaseAuth mAuth;
    private FirebaseUser currentuser;
    final UserGenderEnum[] genderSelected = {UserGenderEnum.MALE};
    final UserLevelEnum[] levelSelected = {UserLevelEnum.GOLD};
    private String cityName;
    private  DatabaseManager dbManager;





    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = DatabaseManagerImpl.getInstance();

        // This is for example!!!! NOT production
//        DatabaseManager dbManager = DatabaseManagerImpl.getInstance();
//        TeamUpCardDTO teamUpCardDTO = new TeamUpCardDTO().setDescription("abcde")
//                .setTimestamp("time")
//                .setCreatorUser(user)
//                .setLocation("beijing")
//                .setUuid("uuid-uuid");
//        dbManager.insertTeamUpCard(teamUpCardDTO);


        mAuth = FirebaseAuth.getInstance();

        location_value =  (TextView) findViewById(R.id.location_value);
        email = (TextView) findViewById(R.id.email);


        //open location sensor permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {}
        else{
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        StringBuffer stringBuffer = new StringBuffer();
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        enabledProviders = locationManager.getProviders(criteria, true);
        Log.i("provider:",enabledProviders.toString());

        if (enabledProviders.isEmpty()) {
            Log.i("get Location:","No Provider");
        } else {
            for (String enabledProvider : enabledProviders) {
//                stringBuffer.append(enabledProvider).append(" ");


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
                    locationManager.requestSingleUpdate(enabledProvider,
                            this,
                            null);
                }
                else {
                    locationManager.requestSingleUpdate(enabledProvider,
                            this,
                            null);
                }

            }
//            enabledProvidersValue.setText(stringBuffer);
        }

        //get level
        List<UserLevelEnum> levelList = Arrays.asList(UserLevelEnum.MASTER,UserLevelEnum.GOLD,UserLevelEnum.SILVER,UserLevelEnum.UNKNOWN);

        level = (Spinner) findViewById(R.id.user_level);
        ArrayAdapter<UserLevelEnum> levelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,UserLevelEnum.values() );
        level.setAdapter(levelAdapter);

        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                levelSelected[0] = levelList.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //get gender
        List<UserGenderEnum> genderList = Arrays.asList(UserGenderEnum.MALE, UserGenderEnum.FEMALE, UserGenderEnum.UNKNOWN);
        gender = (Spinner) findViewById(R.id.user_gender);
        ArrayAdapter<UserGenderEnum> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UserGenderEnum.values());
        gender.setAdapter(genderAdapter);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderSelected[0] = genderList.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register:
                email_address = email.getText().toString();
                createAccount(email_address, "Passwordforallusers123!");
                break;

            case R.id.login:
                email_address = email.getText().toString();
                signIn(email_address,"Passwordforallusers123!");
                break;
        }
    }


    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            UserInfoDTO userDTO = new UserInfoDTO().setAvatarURI("AvatarURI")
                                    .setEmail(email)
                                    .setGender(genderSelected[0])
                                    .setLevel(levelSelected[0])
                                    .setLocation(cityName)
                                    .setUuid(uid)
                                    .setName(email.split("@")[0]);
                            dbManager.insertUser(userDTO);
                            Toast.makeText(MainActivity.this,"Create account successfully!",Toast.LENGTH_SHORT).show();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this,"Sign in successfully!",Toast.LENGTH_SHORT).show();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void updateUI(){
        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(intent);
    }


    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onResume() {
        super.onResume();



    }


    @Override
    protected void onPause()
    {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    /**
     * Updates the display with the  new location information if new location
     * information is more accurate than the current location information.
     *
     * @param location The new location information
     *
     * @see android.location.LocationListener#onLocationChanged(android.location.Location)
     */
    @Override
    public void onLocationChanged(Location location)
    {

        /*------- To get city name from coordinates -------- */
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }
            location_value.setText(cityName);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("city name",cityName);

//        long timeToFix = SystemClock.uptimeMillis() - uptimeAtResume;
//        timeToFixValue.setText(String.valueOf(timeToFix / 1000));

    }

    /**
     * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
     */
    @Override
    public void onProviderDisabled(String provider)
    {
        // no-op
    }

    /**
     * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
     */
    @Override
    public void onProviderEnabled(String provider)
    {
        // no-op
    }

    /**
     * @see android.location.LocationListener#onStatusChanged(java.lang.String,
     *      int, android.os.Bundle)
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        // no-op
    }

    public void onChangeLocationProvidersSettingsClick(View view)
    {
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }


}