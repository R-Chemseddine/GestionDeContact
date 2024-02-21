package com.example.gestiondecontact_boudjema_rachdi_rahmani.UserInterface;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondecontact_boudjema_rachdi_rahmani.Database.DatabaseHelper;
import com.example.gestiondecontact_boudjema_rachdi_rahmani.Models.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewContacts;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private FloatingActionButton fabAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Assurez-vous d'avoir un layout 'activity_main.xml' correspondant

        databaseHelper = new DatabaseHelper(this);
        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(contactList);
        recyclerViewContacts.setAdapter(contactAdapter);

        fabAddContact = findViewById(R.id.fabAddContact);
        fabAddContact.setOnClickListener(view -> {
            // Ici, vous ouvrez l'activité ou le fragment pour ajouter ou éditer un contact
        });

        loadContacts();
    }

    private void loadContacts() {
        new Thread(() -> {
            contactList.clear();
            contactList.addAll(databaseHelper.getAllContacts());
            // Pour interagir avec les éléments de l'interface utilisateur, vous devez exécuter le code sur le thread principal
            runOnUiThread(() -> contactAdapter.notifyDataSetChanged());
        }).start();
    }
}
