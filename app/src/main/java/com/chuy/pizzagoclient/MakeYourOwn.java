package com.chuy.pizzagoclient;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuy.pizzagoclient.fragments.FragmentSauce;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.chuy.pizzagoclient.fragments.FragmentExtras;
import com.chuy.pizzagoclient.fragments.FragmentCheese;
import com.chuy.pizzagoclient.fragments.FragmentMeat;
import com.chuy.pizzagoclient.fragments.FragmentDough;

public class MakeYourOwn extends AppCompatActivity {

    private BottomBar bottomBar;
    private ImageView backButton, carButton;
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_your_own);

        bottomBar = findViewById(R.id.BottomBar);
        bottomBar.setDefaultTab(R.id.tab_dough);

        backButton = findViewById(R.id.backButton);
        tittle = findViewById(R.id.text_toolbar);
        carButton = findViewById(R.id.carButton);

        showToolbar(backButton, tittle, carButton);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.tab_dough:
                        FragmentDough doughFragment = new FragmentDough();
                        getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmetsMYO, doughFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.tab_sauce:
                        FragmentSauce sauceFragment = new FragmentSauce();
                        getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmetsMYO, sauceFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.tab_cheese:
                        FragmentCheese cheeseFragment = new FragmentCheese();
                        getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmetsMYO, cheeseFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.tab_meat:
                        FragmentMeat meatFragemnt = new FragmentMeat();
                        getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmetsMYO, meatFragemnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                    case R.id.tab_extras:
                        FragmentExtras extrasFragment = new FragmentExtras();
                        getSupportFragmentManager().beginTransaction().replace(R.id.ContainerFragmetsMYO, extrasFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                }
            }
        });

    }

    private void showToolbar(ImageView back, TextView tittle, ImageView car) {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menus.class));
            }
        });

        tittle.setText(R.string.arma_tu_pizza);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Todavia no aplcado!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
