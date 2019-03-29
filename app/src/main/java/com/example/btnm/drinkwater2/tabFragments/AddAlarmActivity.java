package com.example.btnm.drinkwater2.tabFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.btnm.drinkwater2.R;
import com.example.btnm.drinkwater2.SimpleImageArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AddAlarmActivity extends AppCompatActivity {

    private static Integer[] imageIconData =  {
            R.drawable.ic_android, R.drawable.ic_audio, R.drawable.ic_sun, R.drawable.ic_add, R.drawable.ic_small_waterdrop_blue,R.drawable.ic_small_waterdrop_red };
    private String[] imageNameData = { "android","audio","sun","add" };

    public final static String ICONPOS = "com.example.btnm.ICONPOSITION";
    public final static String MINUTEDUR = "com.example.btnm.MINUTE";
    public final static String HOURDUR = "com.example.btnm.HOUR";

    private Button saveBtn, cancelBtn;
    private Spinner spinner;
    private NumberPicker minutePicker, hourPicker;

    private int hourDuration;
    private int minuteDuration;
    private int iconPosition;

//    private ArrayList<HashMap<String, Object>> spinnerList;
//    private HashMap<String, Object> hashMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);


        setupImageSpinner();
//        ImageView tempImage = (ImageView) findViewById(R.id.testImage);

        setupHourMinutePicker();

        setupButtons();


    }

    private void setupButtons() {
//        EditText inputAlarm = (EditText)  findViewById(R.id.inputAlarmTime);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener((e) -> {

            // extract icon and time data for the new alarm item
            // gather all data as Strings to be passed back to alarmFragment to create new alarm item
            Intent backIntent = new Intent();
            backIntent.putExtra(ICONPOS, String.valueOf(iconPosition));
            backIntent.putExtra(HOURDUR, String.valueOf(hourDuration));
            backIntent.putExtra(MINUTEDUR, String.valueOf(minuteDuration));
            setResult(RESULT_OK, backIntent);

            finish();
        });

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener( (e) -> {
            finish();
        });
    }

    private void setupImageSpinner() {
        //setup icon spinner with images

        spinner = (Spinner) findViewById(R.id.iconChooseSpinner);

        SimpleImageArrayAdapter simpleImageArrayAdapter = new SimpleImageArrayAdapter(getBaseContext(), imageIconData);
        spinner.setAdapter(simpleImageArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                iconPosition = spinner.getSelectedItemPosition();
                iconPosition = (int) spinner.getItemAtPosition(position);
                System.out.println("pos: "+position + " iconpos selected from spinner: "+ iconPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

    }

    private void setupHourMinutePicker() {
        // setup number picker for the time of the alarm
        
        minutePicker = (NumberPicker) findViewById(R.id.minutePicker);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(60);
        minutePicker.setWrapSelectorWheel(true);
        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minuteDuration = newVal;
                System.out.println("old value: "+ oldVal + " new value: "+ newVal + "alarmDur: "+minuteDuration);
            }
        });

        hourPicker = (NumberPicker) findViewById(R.id.hourPicker);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(24);
        hourPicker.setWrapSelectorWheel(true);
        hourPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            hourDuration = newVal;
            System.out.println("old value: "+ oldVal + " new value: "+ newVal);
        });
    }

//    private void initializeImageList () {
//        for (int i = 0; i < imageNameData.length; i++) {
//            hashMap = new HashMap<>();
//
//            hashMap.put("Name", imageNameData);
//            hashMap.put("Icon", imageIconData);
//            spinnerList.add(hashMap);
//        }
//        ImageView imageView = new ImageView(this);
//        imageView.setBackgroundResource((Integer) spinnerList.get(0).get("Icon"));
//        spinnerList.get(0).get("Name");
//
//    }
//
//    private void addItemOnSpinner() {
//        spinner = (Spinner) findViewById(R.id.iconChooseSpinner);
//
//        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.testSpinnerData, android.R.layout.simple_spinner_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(spinnerAdapter);
//
//    }


}

