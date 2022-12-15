package com.example.pr26;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;


public class order extends Fragment {

    private ImageButton btnMenu;

    private TextView tv_Name;
    private TextView tv_Price;
    private ImageView image;
    private Spinner spinner;

    private Switch switchSugar;
    private Switch switchAdds;
    private RadioButton rbDeliver;
    private RadioButton rbPickup;
    private EditText editTextName;
    private EditText editTextPhone;
    private Button btnSend;
    private Button btnSearch;


    public static order newInstance(String name, String price) {
        order fragment = new order();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("price", price);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        tv_Name = view.findViewById(R.id.tvName);
        tv_Price = view.findViewById(R.id.tvPrice);
        image = view.findViewById(R.id.image);
        spinner = view.findViewById(R.id.spinner);

        switchAdds = view.findViewById(R.id.switchAdds);
        switchSugar = view.findViewById(R.id.switchSugar);
        rbDeliver = view.findViewById(R.id.rbDeliver);
        rbPickup = view.findViewById(R.id.rbPickup);
        editTextName = view.findViewById(R.id.editTextName);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        btnSend = view.findViewById(R.id.btnSend);
        btnSearch = view.findViewById(R.id.btnSearch);

        btnMenu = view.findViewById(R.id.btnMenu);

        int drawable = R.drawable.comfe;
        int array = R.array.coffee;
        String name = getArguments().getString("name", "");
        String price = getArguments().getString("price", "0 рублей");
        if (name == "Торт") {
            drawable = R.drawable.cak;
            array = R.array.cake;
        } else if (name == "Тост с яичницей") {
            drawable = R.drawable.egg;
            array = R.array.combo;
        } else if (name == "Чай с Лимоном") {
            drawable = R.drawable.lime;
            array = R.array.tea;
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(order.this.getContext(),
                array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tv_Name.setText(name);
        tv_Price.setText(price);
        spinner.setAdapter(adapter);
        image.setImageResource(drawable);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addItem = spinner.getSelectedItem().toString();
                Boolean sugar = switchSugar.isChecked();
                Boolean adds = switchAdds.isChecked();
                Boolean deliver = rbDeliver.isChecked();
                String clientName = editTextName.getText().toString();
                String clientPhone = editTextPhone.getText().toString();
                if (clientName.isEmpty() || clientPhone.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Заполните поля имени и телефона", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = getActivity().getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

                    db.execSQL("CREATE TABLE IF NOT EXISTS client_Orders2 (name TEXT, cost TEXT," + "addItem TEXT, sugar INTEGER,adds INTEGER,deliver INTEGER" + ",clientName TEXT,clientPhone TEXT)");
                    db.execSQL("INSERT OR IGNORE INTO client_Orders2 VALUES ('" + name + "','" + price + "','" + addItem + "','" + sugar + "'," +
                            "'" + adds + "','" + deliver + "','" + clientName + "','" + clientPhone + "');");
                    db.close();
                }
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:55.044526, 82.941082"));
                startActivity(intent);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                choise_page catalogFragment = new choise_page();
                fragmentTransaction.replace(R.id.fragmentContainerView, catalogFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}