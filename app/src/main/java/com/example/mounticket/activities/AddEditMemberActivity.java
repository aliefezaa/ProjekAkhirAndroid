package com.example.mounticket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mounticket.R;
import com.example.mounticket.models.Member;

public class AddEditMemberActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText phoneEditText;
    private Button saveButton;

    private Member editedMember;
    private int position = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_member);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        if (intent.hasExtra("editedMember")) {
            editedMember = intent.getParcelableExtra("editedMember");
            position = intent.getIntExtra("position", -1);
            if (editedMember != null) {
                nameEditText.setText(editedMember.getName());
                ageEditText.setText(String.valueOf(editedMember.getAge()));
                phoneEditText.setText(editedMember.getPhone());
            }
        }

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String ageString = ageEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ageString) || TextUtils.isEmpty(phone)) {
                Toast.makeText(AddEditMemberActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageString);

            if (editedMember == null) {
                editedMember = new Member(name, age, phone, 0);
            } else {
                editedMember.setName(name);
                editedMember.setAge(age);
                editedMember.setPhone(phone);
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("editedMember", editedMember);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}