package com.gaurav.krushimitra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class CultipsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TabLayout tabLayout=null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    Toolbar toolbar=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultips);

        tabLayout=this.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Cultivation Stages"));
        tabLayout.addTab(tabLayout.newTab().setText("Weekly Cultivation Tips"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager=findViewById(R.id.pager);
        EventAdapter eventAdapter=new EventAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(eventAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh) {
            finish();
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
        }
        if (id == R.id.logout) {
            finish();
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.cul_tips:
                startActivity(new Intent(this, CultipsActivity.class));
                break;
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.nav_diagnose:
                startActivity(new Intent(this, DiagnosisActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.nav_expenditure:
                startActivity(new Intent(this,ExpenditureActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}