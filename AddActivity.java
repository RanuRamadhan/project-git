package com.example.projeckpastibisa;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.DbHelper;

public class AddActivity extends AppCompatActivity {

    DbHelper dbHelper;
    private EditText etName, etGenre, etSinopsis;
    private Button btnSave, btnList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHelper = new DbHelper(this);

        etName = findViewById(R.id.edt_nama);
        etGenre = findViewById(R.id.edt_genre);
        etSinopsis = findViewById(R.id.edt_sinopsis);
        btnSave = findViewById(R.id.btn_submit);
        btnList = findViewById(R.id.btn_list);

        btnSave.setOnClickListener(v -> {
            if (etName.getText().toString().isEmpty()) {
                Toast.makeText(AddActivity.this, "Error: Nim harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etGenre.getText().toString().isEmpty()) {
                Toast.makeText(AddActivity.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etSinopsis.getText().toString().isEmpty()) {
                Toast.makeText(AddActivity.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
            }else {
                dbHelper.addUserDetail(etName.getText().toString(),etGenre.getText().toString(), etSinopsis.getText().toString());
                etName.setText("");
                etGenre.setText("");
                Toast.makeText(AddActivity.this, "Simpan berhasil!", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(iv -> {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
    });
};
}