package com.precioso.group_7_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.precioso.group_7_final_project.databinding.ActivityAddTaskBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import broadcast.AlarmHandler;
import model.CategoryModelClass;
import model.TaskModelClass;
import utils.SharedPreference;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityAddTaskBinding activityAddTaskBinding;
    private List<CategoryModelClass> categoryList;
    int hour;
    int minute;
    int sHour;
    int sMinute;
    String selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddTaskBinding = ActivityAddTaskBinding.inflate(getLayoutInflater());
        setContentView(activityAddTaskBinding.getRoot());
        activityAddTaskBinding.editCategoryLayout2.setOnClickListener(this);
        activityAddTaskBinding.txtEditCategoryLayout2.setOnClickListener(this);
        activityAddTaskBinding.image1.setOnClickListener(this);
        activityAddTaskBinding.btnAddTaskLayout1.setOnClickListener(this);
        activityAddTaskBinding.selectDuration.setOnClickListener(this);
        categoryList = SharedPreference.getCategoryList(this);
        populateCategorySpinner();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == (R.id.editCategoryLayout2) || v.getId() == (R.id.txtEditCategoryLayout2) || v.getId() == (R.id.image1)) {
            Intent intent = new Intent(this, AddEditCategory.class);
            startActivity(intent);
        } else if (v.getId() == R.id.selectDuration) {
            timeSelect();
        } else if (v.getId() == R.id.btnAddTaskLayout1) {
            addTask();
        }
    }

    private void populateCategorySpinner() {
        if (categoryList != null) {
            List<String> categoryNames = new ArrayList<>();
            for (CategoryModelClass category : categoryList) {
                categoryNames.add(category.getCategory());
            }
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            activityAddTaskBinding.spinnerCategory.setAdapter(adapter);
        }
    }

    private void addTask() {
        String selectedCategoryName = (String) activityAddTaskBinding.spinnerCategory.getSelectedItem();
        String taskName = activityAddTaskBinding.editTextTaskName.getText().toString().trim();

        if (selectedCategoryName != null && !taskName.isEmpty() && selectedDateTime != null) {
            TaskModelClass newTask = new TaskModelClass(
                    selectedCategoryName,
                    taskName,
                    selectedDateTime
            );

            List<TaskModelClass> taskList = SharedPreference.getTaskList(this);

            if (taskList == null) {
                taskList = new ArrayList<>();
            }
            taskList.add(newTask);

            SharedPreference.saveTaskList(this, taskList);

            // Schedule the alarm
            scheduleAlarm();

            Toast.makeText(this, "Task Added Successfully", Toast.LENGTH_SHORT).show();

            activityAddTaskBinding.editTextTaskName.getText().clear();
            activityAddTaskBinding.timeDuration.setText("");
            finish();
        } else {
            Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void scheduleAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmHandler.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, sHour);
        calendar.set(Calendar.MINUTE, sMinute);
        calendar.set(Calendar.SECOND, 0);
        long alarmStartTime = calendar.getTimeInMillis();
        Log.d("", "----alarmStartTime------" + alarmStartTime);

        Objects.requireNonNull(alarmManager).set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
        AlarmHandler.enqueueWork(this, alarmIntent);
    }


    private void timeSelect() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) -> {
                    setSelectedTimeString(selectedHour, selectedMinute);
                }, hour, minute, false);
        timePickerDialog.show();
    }

    private void setSelectedTimeString(int hour, int minute) {
        sMinute = minute;
        sHour = hour;
        selectedDateTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
        activityAddTaskBinding.timeDuration.setText(selectedDateTime);
    }

}