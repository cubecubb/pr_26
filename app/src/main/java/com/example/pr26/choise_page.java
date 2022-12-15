package com.example.pr26;

import android.content.Intent;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class choise_page extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choise_page, container, false);

        Button btnCoffee = view.findViewById(R.id.btnCoffee);
        Button btnCake = view.findViewById(R.id.btnCake);
        Button btnCombo = view.findViewById(R.id.btnCombo);
        Button btnTea = view.findViewById(R.id.btnTea);
        Button btnResult = view.findViewById(R.id.btnResult);
        ImageButton btnTwitter = view.findViewById(R.id.btnTwitter);
        ImageButton btnFacebook = view.findViewById(R.id.btnFacebook);
        ImageButton btnWhatsapp = view.findViewById(R.id.btnWhatsapp);
        ImageButton btnInstagram = view.findViewById(R.id.btnInstagram);

        btnCoffee.setOnClickListener(this);
        btnCake.setOnClickListener(this);
        btnCombo.setOnClickListener(this);
        btnTea.setOnClickListener(this);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                results dataFragment = new results();
                fragmentTransaction.replace(R.id.fragmentContainerView, dataFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet("https://twitter.com");
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet("https://www.facebook.com");
            }
        });
        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet("https://web.whatsapp.com");
            }
        });
        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internet("http://instagram.com");
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCoffee:
                transaction("Кофе с карамелью", "254 рублей");
                break;
            case R.id.btnCake:
                transaction("Торт", "250 рублей");
                break;
            case R.id.btnCombo:
                transaction("Тост с яичницей", "320 рублей");
                break;
            case R.id.btnTea:
                transaction("Чай с Лимоном", "70 рублей");
                break;
        }
    }
    public void transaction(String name, String price){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        order orderFragment = order.newInstance(name, price);
        fragmentTransaction.replace(R.id.fragmentContainerView, orderFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void internet(String url){
        Uri uriUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(intent);
    }
}