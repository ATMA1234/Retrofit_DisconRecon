package com.androiddeft.jsonretrofit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class splash_screen extends Activity {
    public static final int RequestPermissionCode = 1;
    Typewriter load;
    TextView version, splash_text;
    String current_version = "";
    Typeface typeface;

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        typeface = Typeface.createFromAsset(getAssets(), "CALIBRIB.TTF");

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo webShortcut = new ShortcutInfo.Builder(this, "shortcut_web")
                .setShortLabel("google.com")
                .setRank(1)
                .setLongLabel("Open google.com web site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_click_here))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")))
                .build();
        ShortcutInfo webShortcut1 = new ShortcutInfo.Builder(this, "shortcut_web2")
                .setShortLabel("tvd")
                .setRank(0)
                .setLongLabel("Open tvd web site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_click_here))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://transvisionsoftware.com")))
                .build();
        shortcutManager.setDynamicShortcuts(Arrays.asList(webShortcut, webShortcut1));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        version = findViewById(R.id.txt_version_code);
        splash_text = findViewById(R.id.txt_splash_text);
        splash_text.setTypeface(typeface);
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            current_version = packageInfo.versionName;
            version.setText(current_version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        load = findViewById(R.id.txt_load);
        load.setCharacterDelay(250);
        load.animateText("Loading...");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermissions();
            }
        }, 2900);

    }

    public void startup() {
        Intent intent = new Intent(splash_screen.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkPermissions() {
        int currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion >= 23) {
            if (checkPermission()) {
                startup();
            } else {
                requestPermission();
            }
        } else {
            startup();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(splash_screen.this, new String[]
                {
                        READ_PHONE_STATE,
                        WRITE_EXTERNAL_STORAGE,
                        ACCESS_FINE_LOCATION,
                        CAMERA

                }, RequestPermissionCode);
    }

    private boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean ReadPhoneStatePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadStoragePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadLocationPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    if (ReadPhoneStatePermission && ReadStoragePermission && ReadLocationPermission) {
                        startup();
                    } else {
                        Toast.makeText(splash_screen.this, "Required All Permissions..", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                break;
        }
    }
}
