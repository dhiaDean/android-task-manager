package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private TextView taskNameDetail;
    private TextView taskDescriptionDetail;
    private TextView taskCategoryDetail;
    private TextView taskStatusDetail;
    private DatabaseHelper dbHelper;
    private long taskId;
    private Spinner editTaskCategory;
    private Spinner editTaskStatus;
    private EditText editTaskName;
    private EditText editTaskDescription;
    private Button editTaskButton;
    private Button saveEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Initialize views
        taskNameDetail = findViewById(R.id.taskNameDetail);
        taskDescriptionDetail = findViewById(R.id.taskDescriptionDetail);
        taskCategoryDetail = findViewById(R.id.taskCategoryDetail);
        taskStatusDetail = findViewById(R.id.taskStatusDetail);
        editTaskName = findViewById(R.id.editTaskName);
        editTaskDescription = findViewById(R.id.editTaskDescription);
        editTaskCategory = findViewById(R.id.editTaskCategory);
        editTaskStatus = findViewById(R.id.editTaskStatus);
        editTaskButton = findViewById(R.id.editTaskButton);
        saveEditButton = findViewById(R.id.SaveEdit);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Get task ID from intent
        taskId = getIntent().getLongExtra("TASK_ID", -1);

        // Load task details
        if (taskId != -1) {
            Task task = dbHelper.getTask(taskId);
            if (task != null) {
                taskNameDetail.setText(task.getName());
                taskDescriptionDetail.setText(task.getDescription());
                taskCategoryDetail.setText(task.getCategory());
                taskStatusDetail.setText(task.getStatus());
            }
        }

        // Populate Spinners
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.task_categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTaskCategory.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.task_statuses, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTaskStatus.setAdapter(statusAdapter);

        // Set up Edit Task button click listener
        editTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of edit fields and save button
                if (editTaskName.getVisibility() == View.GONE) {
                    editTaskName.setVisibility(View.VISIBLE);
                    editTaskDescription.setVisibility(View.VISIBLE);
                    editTaskCategory.setVisibility(View.VISIBLE);
                    editTaskStatus.setVisibility(View.VISIBLE);
                    saveEditButton.setVisibility(View.VISIBLE);
                } else {
                    editTaskName.setVisibility(View.GONE);
                    editTaskDescription.setVisibility(View.GONE);
                    editTaskCategory.setVisibility(View.GONE);
                    editTaskStatus.setVisibility(View.GONE);
                    saveEditButton.setVisibility(View.GONE);
                }
            }
        });

        // Set up Save Edit button click listener
        saveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the updateTask method to save the edited task details
                updateTask();
            }
        });

        Button deleteTaskButton = findViewById(R.id.deleteTaskButton);
        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();
            }
        });
    }

    private void deleteTask() {
        if (taskId != -1) {
            // Call the method in DatabaseHelper to delete the task
            dbHelper.deleteTask(taskId);
            // Optionally, show a message or navigate back
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
        } else {
            Toast.makeText(this, "Task ID is invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTask() {
        if (taskId != -1) {
            String name = editTaskName.getText().toString();
            String description = editTaskDescription.getText().toString();
            String category = editTaskCategory.getSelectedItem().toString();
            String status = editTaskStatus.getSelectedItem().toString();

            // Call the method in DatabaseHelper to update the task
            dbHelper.updateTask(taskId, name, description, category, status);
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
        } else {
            Toast.makeText(this, "Task ID is invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
