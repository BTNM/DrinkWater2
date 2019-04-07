package com.example.btnm.drinkwater2.tabFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.btnm.drinkwater2.AlarmItem;
import com.example.btnm.drinkwater2.R;
import com.example.btnm.drinkwater2.RecycleViewAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AlarmsFragment extends Fragment {
    private static final String TAG = "Tab AlarmList";
    public static final int REQUEST_CODE_ALARMDATA = 101;

    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<AlarmItem> listData = new ArrayList<AlarmItem>();

//    private AlarmDatabase alarmDatabase;
//    private ArrayList<Integer> alarmRequestCodeList = new ArrayList<>();

    private int iconPosition, hourDuration, minuteDuration;
    private FloatingActionButton floatingButton;

//    private static final String FILE_NAME = "DrinkWaterStorage.txt";
    private static final String FILE_NAME = "internalStorageTest3.txt";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_alarms,container, false);

        // create adapter and layout manager, and set data list to the adapter, before setting recycle view to the adapater and layout manager
//        initTestData();
        initRecycleView(view);
        readAlarmListFromStorage();

//        alarmDatabase = new AlarmDatabase(getContext(), alarmRequestCodeList);
        setupFloatingButton(view);

//        writeAlarmListToStorage();

        return view;
    }


    /**
     * Receive intent from the finished activity,
     * and take the extra data from the intent to make new alarm item in the recycle view
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ALARMDATA) {
            if (resultCode == AddAlarmActivity.RESULT_OK) {
                //get the data from the addAlarmActivity, by using the same name keys used in the intent
                String iconPos = data.getStringExtra(AddAlarmActivity.ICONPOS);
                String hourDur = data.getStringExtra(AddAlarmActivity.HOURDUR);
                String minuteDur = data.getStringExtra(AddAlarmActivity.MINUTEDUR);

//                System.out.println("icon value: "+ iconPos  + " hour value: "+ hourDur + " minute: "+minuteDur);
//                Toast.makeText(getContext() , "testing reply"+iconPos + hourDur + minuteDur, Toast.LENGTH_SHORT).show();

                iconPosition = Integer.parseInt(iconPos);
                hourDuration = Integer.parseInt(hourDur);
                minuteDuration = Integer.parseInt(minuteDur);


                // add new alarm item to recycleview from activity
                AlarmItem tempAlarmItem = new AlarmItem(iconPosition, (hourDur+" "+minuteDur) );
//                listData.add(tempAlarmItem);

//                String name = getContext().getFilesDir().getName();
//                System.out.println("File name: "+ name);

                //add item to listdata which recycleview has set adapter to, then notify to update recycle view
                addAlarmItemToListAndStorage(tempAlarmItem, true);

//                readAlarmListFromStorage();

                // notify adapter to update recycleview
                adapter.notifyItemInserted(listData.size() );
                adapter.notifyDataSetChanged();
            }

        } else {
            Log.d(TAG, "request code alarm, are wrong somehow");
        }

    }

    public void addAlarmItemToListAndStorage (AlarmItem alarmItem, boolean appendToFile) {
        listData.add(alarmItem);
        String tempLine = ""+alarmItem.getImageID()+" "+ alarmItem.getRepeatingAlarmTime()+"\n";
        writeFile(tempLine, appendToFile);
    }

    public void writeAlarmListToStorage() {
        StringBuilder stringBuilder = new StringBuilder();

        for (AlarmItem alarmItem : listData) {
            String temp = alarmItem.getImageID() + alarmItem.getRepeatingAlarmTime() + "\n";
            stringBuilder.append(temp);
        }
        System.out.println(stringBuilder.toString());
//        return stringBuilder.toString();
    }

    /**
     * write string to internal storage
     * @param addLine
     * @param appendToFile
     */
    public void writeFile (String addLine, Boolean appendToFile) {
//        String fileName = "App test file.txt";
//        String inputText = "testing write to file";
        String inputText = addLine;

        File file = null;

        FileOutputStream fileOutputStream = null;
        try {
            file = getContext().getFilesDir().getAbsoluteFile(); // get the absolute path to the directory

            if (appendToFile ) {
                fileOutputStream = getContext().openFileOutput(FILE_NAME, Context.MODE_APPEND);
            } else {
                fileOutputStream = getContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            }
            fileOutputStream.write(inputText.getBytes() );

            Toast.makeText(getContext(), "test text saved to " + getContext().getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void readAlarmListFromStorage () {
        String storageTxt = readFile();
        System.out.println("storageText : " + storageTxt);

        // split all text in storage by newlines
        String[] itemListRaw = storageTxt.split("\n");

        for (String lines : itemListRaw) {
            // split each line by space
            String[] lineSplitted = lines.split(" ");

            listData.add(new AlarmItem(Integer.valueOf(lineSplitted[0]), lineSplitted[1]+" "+lineSplitted[2]) );
        }
//        adapter.notifyItemInserted(listData.size() );
//        adapter.notifyDataSetChanged();
    }

    /**
     * read text from internal storage file
     * @return
     */
    public String readFile () {
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = getContext().openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(getContext().openFileInput(FILE_NAME)) );

            String tempLines;
            while ((tempLines = bufferedReader.readLine()) != null ) {
                stringBuilder.append(tempLines);
                stringBuilder.append("\n");
            }
            Toast.makeText(getContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }

    private void setupFloatingButton(View view) {
        floatingButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingButton.setOnClickListener( (e) -> {
            Intent intent = new Intent(getContext(), AddAlarmActivity.class);
//            startActivity(intent);
            startActivityForResult(intent, REQUEST_CODE_ALARMDATA);
        } );
    }

    /**
     * Initialize the recyle view with all corresponding data
     * @param view
     */
    private void initRecycleView(View view) {
        recyclerView = (RecyclerView)  view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext() );

//        adapter = new RecycleViewAdapter(initTestData() );
        adapter = new RecycleViewAdapter(listData );

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL );
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycleview_divider, null) );
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void initTestData() {

//        listData.add(new AlarmItem(R.drawable.ic_android,"0 45"));
//        listData.add(new AlarmItem(R.drawable.ic_sun,"1 15" ));
//        listData.add(new AlarmItem(R.drawable.ic_audio,"2 0"));


//        listData.add(new AlarmItem(R.drawable.water_drop_icon,"WaterDrop"," Line 1") );
//        listData.add(new AlarmItem(R.drawable.water_droplet2,"WaterDrop2 for testing"," Line 1" ) );
//        listData.add(new AlarmItem(R.drawable.cartoon_water_drops,"WaterDrop3" ," Line 1") );

    }


}
