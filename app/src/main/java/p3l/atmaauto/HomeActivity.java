package p3l.atmaauto;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PegawaiFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_pegawai);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_pegawai:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PegawaiFragment()).commit();
                break;
            case R.id.nav_supplier:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SupplierFragment()).commit();
                break;
            case R.id.nav_sparepart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SparepartFragment()).commit();
                break;
            case R.id.nav_service:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ServiceFragment()).commit();
                break;
            case R.id.nav_kendaraan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new KendaraanFragment()).commit();
                break;
            case R.id.nav_pelanggan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PelangganFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
