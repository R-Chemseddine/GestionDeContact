package com.example.gestiondecontact_boudjema_rachdi_rahmani.UserInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestiondecontact_boudjema_rachdi_rahmani.Database.DatabaseHelper;
import com.example.gestiondecontact_boudjema_rachdi_rahmani.Models.Contact;
import com.example.gestiondecontact_boudjema_rachdi_rahmani.R;

public class AddEditContactActivity extends AppCompatActivity {
    private EditText editTextName, editTextPhone, editTextAddress;
    private Button btnSave;
    private DatabaseHelper databaseHelper;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        btnSave = findViewById(R.id.btnSave);

        databaseHelper = new DatabaseHelper(this);

        // Pour l'édition d'un contact, récupérez et affichez ses informations
        // Supposons que l'ID du contact à éditer soit passé via l'intent
        // String contactId = getIntent().getStringExtra("CONTACT_ID");
        // if (contactId != null) {
        //     currentContact = databaseHelper.getContact(contactId);
        //     editTextName.setText(currentContact.getName());
        //     editTextPhone.setText(currentContact.getPhoneNumber());
        //     editTextAddress.setText(currentContact.getAddress());
        // }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
    }

    private void saveContact() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        // Si currentContact est null, il s'agit d'un nouveau contact
        if (currentContact == null) {
            currentContact = new Contact(); // Assurez-vous que le constructeur Contact existe et est adapté
        }

        currentContact.setName(name);
        currentContact.setPhoneNumber(phone);
        currentContact.setAddress(address);

        // Si le contact est nouveau, ajoutez-le à la base de données, sinon mettez à jour le contact existant
        // Vous devrez peut-être ajuster cette logique en fonction de votre implémentation de DatabaseHelper
        if (/* vérifiez si le contact est nouveau */) {
            databaseHelper.addContact(currentContact);
        } else {
            databaseHelper.updateContact(currentContact);
        }

        finish(); // Retour à l'activité principale
    }
}
