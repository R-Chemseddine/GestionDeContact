package com.example.gestiondecontact_boudjema_rachdi_rahmani.UserInterface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondecontact_boudjema_rachdi_rahmani.Models.Contact;
import com.example.gestiondecontact_boudjema_rachdi_rahmani.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private final List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhoneNumber());
        // Ajoutez d'autres vues selon votre layout d'item
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView phoneTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contactName);
            phoneTextView = itemView.findViewById(R.id.contactPhone);
        }
    }
}