package com.example.pr26;

import static android.content.Context.MODE_PRIVATE;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class results extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        SQLiteDatabase db = getActivity().getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS client_Orders2 (name TEXT, cost TEXT,addItem TEXT, sugar INTEGER,adds INTEGER,deliver INTEGER,clientName TEXT,clientPhone TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM client_Orders2;", null);
        TextView textView = view.findViewById(R.id.tv_db);
        textView.setText("");
        while (query.moveToNext()) {
            String name = query.getString(0);
            String cost = query.getString(1);
            String addItem = query.getString(2);
            int sugar = query.getInt(3);
            String Sugar;
            if(sugar==1){
                Sugar="Есть";
            }else {
                Sugar="Нет";
            }
            int adds = query.getInt(4);
            String Adds;
            if(adds==1){
                Adds="Есть";
            }else {
                Adds="Нет";
            }
            int deliver = query.getInt(5);
            String Deliver;
            if(deliver==1){
                Deliver="Доставка";
            }else {
                Deliver="Самовывоз";
            }
            String clientName = query.getString(6);
            String clientPhone = query.getString(7);
            textView.append("Название: " + name + "\nЦена: " + cost + "\nСвойство: "+ addItem +"\nCахар: " + Sugar+"\nДобавки: "+Adds+"\nСпособ доставки: " + Deliver+ "\nИмя клиента: " + clientName+ "\nТелефон клиента: "+ clientPhone+ ";\n\n");
        }
        query.close();
        db.close();
        return view;
    }
}