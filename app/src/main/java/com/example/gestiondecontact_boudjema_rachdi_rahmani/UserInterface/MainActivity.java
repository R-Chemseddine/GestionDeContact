package com.example.gestiondecontact_boudjema_rachdi_rahmani.UserInterface;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondecontact_boudjema_rachdi_rahmani.Database.DatabaseHelper;
import com.example.gestiondecontact_boudjema_rachdi_rahmani.Models.Contact;
import com.example.gestiondecontact_boudjema_rachdi_rahmani.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ContactAdapter contactAdapter;
    private final List<Contact> contactList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private FloatingActionButton fabAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        RecyclerView recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(contactList);
        recyclerViewContacts.setAdapter(contactAdapter);

        fabAddContact = findViewById(R.id.fabAddContact);
        fabAddContact.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddEditContactActivity.class);
            startActivity(intent);
        });

        loadContacts();
    }

    private void loadContacts() {
        new Thread(() -> {
            contactList.clear();
            contactList.addAll(databaseHelper.getAllContacts());
            runOnUiThread(() -> contactAdapter.notifyDataSetChanged());
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }
}