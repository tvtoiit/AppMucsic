package com.example.appmusic.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appthibanglaixe.R;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab_Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab_Home_Fragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab_Home_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab_Home_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab_Home_Fragment newInstance(String param1, String param2) {
        Tab_Home_Fragment fragment = new Tab_Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        Button btnAddMusic = view.findViewById(R.id.btnAddMusic);




        Button btnRegister = view.findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi button đăng nhập được click
                goToLoginPage();
            }
        });

        btnAddMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi button đăng nhập được click
                goToAddMusic();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterPage();
            }
        });

        Button btnNen = view.findViewById(R.id.btnNen);

        btnNen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNenPage();
            }
        });
        return view;
    }

    // Hàm chuyển đến trang đăng nhập
    private void goToLoginPage() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    // Hàm chuyển đến trang đăng nhập
    private void goToAddMusic() {
        Intent intent = new Intent(getActivity(), AddMusicActivity.class);
        startActivity(intent);
    }

    // Hàm chuyển đến trang đăng kí
    private void goToRegisterPage() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
    }

    // Hàm chuyển đến trang nền
    private void goToNenPage() {
        Intent intent = new Intent(getActivity(), NenActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}