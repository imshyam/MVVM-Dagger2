package com.example.shyam.dagger2sample.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.shyam.dagger2sample.R;

public class MainActivity extends AppCompatActivity implements ItemClickInteractionListener{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, new HomeFragment());
        transaction.addToBackStack(null);
        transaction.commit();

        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.home:
                    fragmentTransaction.replace(R.id.fragment, new HomeFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Toast.makeText(getApplicationContext(), "HOME", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.settings:
                    fragmentTransaction.replace(R.id.fragment, new SettingsFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Toast.makeText(getApplicationContext(), "SETTINGS", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.profile:
                    fragmentTransaction.replace(R.id.fragment, new ProfileFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Toast.makeText(getApplicationContext(), "PROFILE", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onUserDetailsClick(String id) {
        Toast.makeText(getApplicationContext(), "Clicked : " + id, Toast.LENGTH_SHORT).show();
    }
}
