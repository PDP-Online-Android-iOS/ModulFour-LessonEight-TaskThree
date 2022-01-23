package dev.ogabek.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.listener.ItemClickListener;
import dev.ogabek.java.model.Contact;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Contact> contacts;
    ItemClickListener listener;

    public ContactAdapter(List<Contact> contacts, ItemClickListener listener) {
        this.contacts = contacts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list, parent, false);
        return new ContectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        if (holder instanceof ContectViewHolder) {
            ((ContectViewHolder) holder).name.setText(contact.getName());
            ((ContectViewHolder) holder).number.setText(contact.getNumber());
            ((ContectViewHolder) holder).photo.setImageResource(contact.getPhoto());
            ((ContectViewHolder) holder).item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(contact);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    private static class ContectViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView name, number;
        LinearLayout item;

        public ContectViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.iv_item_contact_photo);
            name = view.findViewById(R.id.tv_item_contact_name);
            number = view.findViewById(R.id.tv_item_contact_number);
            item = view.findViewById(R.id.main_item);
        }
    }
}
