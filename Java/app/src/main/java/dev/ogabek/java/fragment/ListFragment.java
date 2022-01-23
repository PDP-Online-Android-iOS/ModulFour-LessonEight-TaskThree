package dev.ogabek.java.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.adapter.ContactAdapter;
import dev.ogabek.java.listener.ItemClickListener;
import dev.ogabek.java.listener.SendContactListener;
import dev.ogabek.java.model.Contact;

public class ListFragment extends Fragment {

    SendContactListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    public ListFragment newInstance(List<Contact> contactList) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("contactList", (ArrayList<? extends Parcelable>) contactList);
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Contact> contactList = null;
        if (getArguments() != null) {
            contactList = getArguments().getParcelableArrayList("contactList");
        }

        RecyclerView recyclerView = view.findViewById(R.id.rv_contact_list_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(new ContactAdapter(contactList, new ItemClickListener(){
            @Override
            public void itemClick(Contact contact) {
                listener.onContactSend(contact);
            }
        }));

    }

    public void setOnClickListener(SendContactListener listener) {
        this.listener = listener;
    }

}