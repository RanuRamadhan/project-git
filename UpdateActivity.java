package com.example.projeckpastibisa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.DbHelper;
import models.Student;

public class UpdateActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private EditText etName, etGenre, etSinopsis;
    private Button btnSave;
    private Student student;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DbHelper(this);

        etName = findViewById(R.id.edt_nama);
        etGenre = findViewById(R.id.edt_genre);
        etSinopsis = findViewById(R.id.edt_sinopsis);
        btnSave = findViewById(R.id.btn_submit);

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("user");

        etName.setText(student.getName());
        etGenre.setText(student.getGenre());

        btnSave.setOnClickListener((View v) -> {
            dbHelper.updateUser(student.getId(), etName.getText().toString(), etGenre.getText().toString(), etSinopsis.getText().toString());
            Toast.makeText(UpdateActivity.this, "Updated berhasil!", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}