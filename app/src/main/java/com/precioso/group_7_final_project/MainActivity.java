package com.precioso.group_7_final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.precioso.group_7_final_project.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import adapter.TaskAdapter;
import model.TaskModelClass;
import utils.SharedPreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mainBinding;
    private TaskAdapter taskAdapter;
    private List<TaskModelClass> taskModelClassList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        mainBinding.btnAddTask.setOnClickListener(this);
        recyclerView = mainBinding.rcrTasks;
        taskModelClassList = new ArrayList<>();
        loadTaskData();
        taskAdapter = new TaskAdapter(taskModelClassList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTaskData();
        if (taskAdapter != null) {
            taskAdapter.notifyDataSetChanged();
        }
    }

    private void loadTaskData() {
        taskModelClassList = SharedPreference.getTaskList(this);
        if (taskModelClassList == null) {
            taskModelClassList = new ArrayList<>();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddTask) {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
        }
    }
}