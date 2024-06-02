package com.example.mounticket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mounticket.R;
import com.example.mounticket.adapter.MemberAdapter;
import com.example.mounticket.models.Member;
import com.example.mounticket.models.Mountain;
import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

    private static final int ADD_MEMBER_REQUEST = 1;
    private static final int EDIT_MEMBER_REQUEST = 2;

    private Mountain selectedMountain;
    private ArrayList<Member> memberList;
    private MemberAdapter memberAdapter;

    private Button buttonAddMember;
    private Button buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Intent intent = getIntent();
        selectedMountain = intent.getParcelableExtra("selectedMountain");

        if (selectedMountain == null) {
            Toast.makeText(this, "Failed to get mountain details", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        buttonAddMember = findViewById(R.id.buttonAddMember);
        buttonFinish = findViewById(R.id.buttonFinish);
        RecyclerView recyclerViewMembers = findViewById(R.id.recyclerViewMembers);

        memberList = new ArrayList<>();
        memberAdapter = new MemberAdapter(memberList, this);

        memberAdapter.setOnItemClickListener(new MemberAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent editIntent = new Intent(FormActivity.this, AddEditMemberActivity.class);
                editIntent.putExtra("editedMember", memberList.get(position));
                editIntent.putExtra("position", position);
                startActivityForResult(editIntent, EDIT_MEMBER_REQUEST);
            }
            @Override
            public void onEditClick(int position) {
                Intent editIntent = new Intent(FormActivity.this, AddEditMemberActivity.class);
                editIntent.putExtra("editedMember", memberList.get(position));
                editIntent.putExtra("position", position);
                startActivityForResult(editIntent, EDIT_MEMBER_REQUEST);
            }

            @Override
            public void onDeleteClick(int position) {
                memberList.remove(position);
                memberAdapter.notifyItemRemoved(position);
                Toast.makeText(FormActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewMembers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMembers.setAdapter(memberAdapter);

        buttonAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(FormActivity.this, AddEditMemberActivity.class);
                startActivityForResult(addIntent, ADD_MEMBER_REQUEST);
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishForm();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Member member = data.getParcelableExtra("editedMember");
            int position = data.getIntExtra("position", -1);

            if (requestCode == ADD_MEMBER_REQUEST) {
                if (member != null) {
                    member.setMountainId(selectedMountain.getId());
                    memberList.add(member);
                    memberAdapter.notifyItemInserted(memberList.size() - 1);
                }
            } else if (requestCode == EDIT_MEMBER_REQUEST) {
                if (member != null && position != -1) {
                    memberList.set(position, member);
                    memberAdapter.notifyItemChanged(position);
                }
            }
        }
    }

    private void finishForm() {
        if (memberList.isEmpty()) {
            Toast.makeText(this, "Please add at least one member", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(FormActivity.this, BookingActivity.class);
        intent.putExtra("selectedMountain", selectedMountain);
        intent.putParcelableArrayListExtra("memberList", memberList);
        startActivity(intent);
    }
}