package com.example.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private LinearLayout tasksContainer;
    private FloatingActionButton fabAddTask;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DatabaseHelper(this);
        tasksContainer = findViewById(R.id.tasksContainer);
        fabAddTask = findViewById(R.id.fabAddTask);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_tasks) {
                    Intent intent = new Intent(HomeActivity.this, RulesActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_settings) {
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        // Load initial tasks
        loadTasks();

        // Add task button click listener
        fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // In a real app, you'd open a dialog or new activity to add a task
                // For this example, we'll add a sample task
                Task sampleTask = new Task(
                        "Sample Task",
                        "This is a sample task description",
                        "Personal",
                        "Pending"
                );
                dbHelper.addTask(sampleTask);
                loadTasks();
            }
        });
    }

    private void loadTasks() {
        // Clear existing views
        tasksContainer.removeAllViews();

        // Fetch tasks from database
        List<Task> tasks = dbHelper.getAllTasks();

        // Create a view for each task
        for (final Task task : tasks) {
            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, 16);
            cardView.setLayoutParams(cardParams);
            cardView.setCardElevation(4);
            cardView.setRadius(16);

            TextView taskNameView = new TextView(this);
            taskNameView.setText(task.getName());
            taskNameView.setTextSize(18);
            taskNameView.setPadding(32, 32, 32, 32);
            taskNameView.setGravity(Gravity.CENTER_VERTICAL);

            // Set color based on task status
            if ("Completed".equalsIgnoreCase(task.getStatus())) {
                taskNameView.setTextColor(Color.GREEN);
            } else if ("Pending".equalsIgnoreCase(task.getStatus())) {
                taskNameView.setTextColor(Color.RED);
            } else {
                taskNameView.setTextColor(Color.BLACK); // default color
            }

            cardView.addView(taskNameView);

            // Set click listener to open task details
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, TaskDetailActivity.class);
                    intent.putExtra("TASK_ID", task.getId());
                    startActivity(intent);
                }
            });

            tasksContainer.addView(cardView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }
}