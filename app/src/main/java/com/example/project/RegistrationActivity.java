package com.example.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private DBHelper dbHelper;  // SQLite helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etLastName = findViewById(R.id.etLastName);
        EditText etPhone = findViewById(R.id.etPhone);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        CheckBox checkBoxAgreewithCredentials = findViewById(R.id.checkBoxAgreewithcrendial);
        Button btnRegister = findViewById(R.id.btnRegister);

        TextView textViewCredential = findViewById(R.id.textViewCredential);
        TextView textViewTerms = findViewById(R.id.textViewTerms);

        dbHelper = new DBHelper(this);

        // Set clickable links for terms and conditions
        textViewTerms.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, RulesActivity.class);
            startActivity(intent);
        });
        textViewCredential.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, RulesActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            // Validate fields
            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(RegistrationActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if (!checkBoxAgreewithCredentials.isChecked()) {
                Toast.makeText(RegistrationActivity.this, "You must agree to the credentials and rules", Toast.LENGTH_SHORT).show();
            } else {
                // All checks passed, insert data into database
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DBHelper.COLUMN_FIRST_NAME, firstName);
                values.put(DBHelper.COLUMN_LAST_NAME, lastName);
                values.put(DBHelper.COLUMN_PHONE, phone);
                values.put(DBHelper.COLUMN_EMAIL, email);
                values.put(DBHelper.COLUMN_PASSWORD, password);
                values.put(DBHelper.COLUMN_TERMS_AGREED, checkBoxAgreewithCredentials.isChecked() ? 1 : 0);  // Store checkbox as 1 (true) or 0 (false)

                long newRowId = db.insert(DBHelper.TABLE_USER, null, values);

                if (newRowId != -1) {
                    Toast.makeText(RegistrationActivity.this, "Registration done", Toast.LENGTH_LONG).show();
                    // Navigate back to MainActivity
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear activity stack
                    startActivity(intent);
                    finish(); // Finish current activity
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
