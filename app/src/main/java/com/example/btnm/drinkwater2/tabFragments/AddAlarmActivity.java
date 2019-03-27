package com.example.btnm.drinkwater2.tabFragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btnm.drinkwater2.R;
import com.example.btnm.drinkwater2.SimpleImageArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AddAlarmActivity extends AppCompatActivity {

    private static Integer[] imageIconData =  {
            R.drawable.ic_android, R.drawable.ic_audio, R.drawable.ic_sun, R.drawable.ic_add, R.drawable.ic_small_waterdrop_blue,R.drawable.ic_small_waterdrop_red };
    private String[] imageNameData = { "android","audio","sun","add" };

    private Spinner spinner;
    private int alarmDuration;
    private Button saveBtn, cancelBtn;

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

        ImageView testIcon = (ImageView) spinner2.getSelectedItem();
        ImageView tempImage = (ImageView) findViewById(R.id.testImage);


        EditText inputAlarm = (EditText)  findViewById(R.id.inputAlarmTime);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener((e) -> {
//            String test = inputAlarm.getText().toString();

            if ( inputAlarm.getText().toString().trim().isEmpty() || Integer.parseInt(inputAlarm.getText().toString()) > 1 ) {
                alarmDuration = (int) Integer.valueOf(inputAlarm.getText().toString() );
                Toast.makeText(getBaseContext(), "Test editText for now: "+alarmDuration + "test2 : "+ inputAlarm.getText().toString(),Toast.LENGTH_SHORT).show();

//                tempImage.setImageResource((Integer) spinner2.getSelectedItem());
                tempImage.setImageResource(imageIconData[spinner2.getSelectedItemPosition()] );

//                testIconCheck.setImageResource(spinner2.getSelectedView());
//                testIconCheck.setImageResource(R.drawable.ic_audio);

//                testIconCheck.setImageResource(imageIconData[spinner2.getSelectedItemPosition()] );
            }
        });
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener( (e) -> {
            finish();
        });



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

