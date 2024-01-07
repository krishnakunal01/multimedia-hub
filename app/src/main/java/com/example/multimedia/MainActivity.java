package com.example.multimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.adapter.AdapterViewPager;
import com.example.fragment.FragmentAudio;
import com.example.fragment.FragmentImage;
import com.example.fragment.FragmentPdf;
import com.example.fragment.FragmentVideo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerMain = findViewById(R.id.pagerMain);
        bottomNav = findViewById(R.id.bottomNav);


        fragmentArrayList.add(new FragmentVideo());
        fragmentArrayList.add(new FragmentAudio());
        fragmentArrayList.add(new FragmentImage());
        fragmentArrayList.add(new FragmentPdf());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);
        pagerMain.setAdapter(adapterViewPager);
        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNav.setSelectedItemId(R.id.itVideo);
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(R.id.itMusic);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.itImage);
                        break;
                    case 3:
                        bottomNav.setSelectedItemId(R.id.itPdf);
                        break;
                }
                super.onPageSelected(position);
            }
        });
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                    if (id == R.id.itVideo) {
                        pagerMain.setCurrentItem(0);

                    } else if (id == R.id.itMusic) {
                        pagerMain.setCurrentItem(1);
                    } else if (id == R.id.itImage) {
                        pagerMain.setCurrentItem(2);
                    } else if (id == R.id.itPdf) {
                        pagerMain.setCurrentItem(3);
                    }


               return true;
            }
        });

    }
}