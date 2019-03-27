package com.example.btnm.drinkwater2.tabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.btnm.drinkwater2.R;
import com.example.btnm.drinkwater2.SimpleImageArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AddAlarmActivity extends AppCompatActivity {

    private static Integer[] imageIconData =  {
            R.drawable.ic_android, R.drawable.ic_audio, R.drawable.ic_sun, R.drawable.ic_add, R.drawable.ic_small_waterdrop_blue,R.drawable.ic_small_waterdrop_red };
    private String[] imageNameData = {
            "android","audio","sun","add" };

    private Spinner spinner;

    private ArrayList<HashMap<String, Object>> spinnerList;
    private HashMap<String, Object> hashMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

//        Intent intent = getIntent();
//        String message = intent.getStringExtra();

//        Toolbar addAlarmToolbar = (Toolbar) findViewById(R.id.addAlarmToolbar);
//        setSupportActionBar(addAlarmToolbar);

//        addItemOnSpinner();

//        initializeImageList();
        Spinner spinner2 = (Spinner) findViewById(R.id.iconChooseSpinner);
//        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter();
        SimpleImageArrayAdapter simpleImageArrayAdapter = new SimpleImageArrayAdapter(getBaseContext(), imageIconData);
        spinner2.setAdapter(simpleImageArrayAdapter);

    }

    private void initializeImageList () {
        for (int i = 0; i < imageNameData.length; i++) {
            hashMap = new HashMap<>();

            hashMap.put("Name", imageNameData);
            hashMap.put("Icon", imageIconData);
            spinnerList.add(hashMap);
        }
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource((Integer) spinnerList.get(0).get("Icon"));
        spinnerList.get(0).get("Name");

    }



    private void addItemOnSpinner() {
        spinner = (Spinner) findViewById(R.id.iconChooseSpinner);

        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.testSpinnerData, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


    }


}

