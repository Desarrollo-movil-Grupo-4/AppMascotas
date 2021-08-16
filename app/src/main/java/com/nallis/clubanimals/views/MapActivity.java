package com.nallis.clubanimals.views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nallis.clubanimals.R;
import com.nallis.clubanimals.databinding.ActivityMapBinding;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private  ArrayList local = new ArrayList();

    private GoogleMap mMap;
    private ActivityMapBinding binding;

    private LocationManager ubicacion;

    private String nombre;
    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nombre = getIntent().getStringExtra("nombrevet");
        lat = getIntent().getDoubleExtra("latitudVet",0);
        lon = getIntent().getDoubleExtra("longitudVet",0);
        localizacion();

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //localizarMovimientos();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

            LatLng veterinaria = new LatLng( lat, lon);
            mMap.addMarker(new MarkerOptions().position(veterinaria).title(nombre));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(veterinaria, 9));

            LatLng positionUsuario = new LatLng( (double) local.get(0), (double) local.get(1));
            mMap.addMarker(new MarkerOptions().position(positionUsuario).title("Tu ubicacion"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positionUsuario, 9));

    }

    public ArrayList localizacion() {

        ubicacion = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        Location location = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {

            local.add(location.getLatitude());
            local.add(location.getLongitude());
        }
        return local;
    }
/*
    public ArrayList localizarMovimientos() {

        LocationListener location_listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    String address = addresses.get(0).getAddressLine(0);

                }catch (Exception e) {
                    e.printStackTrace();
                }

                local.add(location.getLatitude());
                local.add(location.getLongitude());

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

        };
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            //return;
        }
        ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, location_listener);

        return local;
    }

 */
}