package com.example.gestiondecontact_boudjema_rachdi_rahmani.Database;

import com.example.gestiondecontact_boudjema_rachdi_rahmani.Models.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contactsManager";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PHOTO_URI = "photoUri";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_PHONE + " TEXT,"
                + KEY_PHOTO_URI + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_PHONE, contact.getPhoneNumber());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Contact getContact(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_PHONE, KEY_PHOTO_URI },
                KEY_ID + "=?", new String[]{id}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Contact contact = new Contact(
                    UUID.fromString(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            cursor.close();
            return contact;
        }

        return null; // Handle case where contact is not found
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        // Sélectionnez toute la requête
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcourir toutes les lignes et ajouter à la liste
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(UUID.fromString(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setAddress(cursor.getString(2));
                contact.setPhoneNumber(cursor.getString(3));
                contact.setPhotoUri(cursor.getString(4));
                // Ajout du contact à la liste
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contactList;
    }

}
