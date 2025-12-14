package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TaskDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_TASKS = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_STATUS = "status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_STATUS + " TEXT)";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    // Add a new task
    public long addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_CATEGORY, task.getCategory());
        values.put(KEY_STATUS, task.getStatus());
        return db.insert(TABLE_TASKS, null, values);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                task.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                task.setCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
                task.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;
    }

    // Get a single task by ID
    public Task getTask(long taskId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TASKS,
                new String[]{KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_CATEGORY, KEY_STATUS},
                KEY_ID + "=?",
                new String[]{String.valueOf(taskId)},
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Task task = new Task();
            task.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            task.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            task.setCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
            task.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
            cursor.close();
            return task;
        }
        return null;
    }

    public void deleteTask(long taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tasks", "id = ?", new String[]{String.valueOf(taskId)});
        db.close();
    }

    // Update an existing task
    public void updateTask(long taskId, String name, String description, String category, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_CATEGORY, category);
        values.put(KEY_STATUS, status);

        // Updating row
        db.update(TABLE_TASKS, values, KEY_ID + " = ?", new String[]{String.valueOf(taskId)});
        db.close();
    }
}
