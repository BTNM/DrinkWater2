package com.example.btnm.drinkwater2.tabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

import com.example.btnm.drinkwater2.R;

public class AddAlarmActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

//        Intent intent = getIntent();
//        String message = intent.getStringExtra();

//        Toolbar addAlarmToolbar = (Toolbar) findViewById(R.id.addAlarmToolbar);
//        setSupportActionBar(addAlarmToolbar);

        addItemOnSpinner();


    }

    private void addItemOnSpinner() {
        spinner = (Spinner) findViewById(R.id.iconChooseSpinner);


    }


}
