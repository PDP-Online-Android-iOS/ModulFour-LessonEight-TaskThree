package dev.ogabek.java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.fragment.ContactFragment;
import dev.ogabek.java.fragment.ListFragment;
import dev.ogabek.java.listener.ItemClickListener;
import dev.ogabek.java.listener.SendContactListener;
import dev.ogabek.java.model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment = new ListFragment().newInstance(getContacts());

        listFragment.setOnClickListener(new SendContactListener(){
            @Override
            public void onContactSend(Contact contact) {
                ContactFragment contactFragment = new ContactFragment().newInstance(contact);
                createContactFragment(contactFragment);
            }
        });

        createListFragment(listFragment);

    }

    private void createListFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_list, fragment)
                .commit();
    }

    private void createContactFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_contact, fragment)
                .commit();
    }

    private List<Contact> getContacts() {

        List<Contact> contactList = new ArrayList<>();

        contactList.add(new Contact("Ogabek Matyakubov", "+998 99 749 25 81", R.drawable.img));
        contactList.add(new Contact("Bogibek Matyaqubov", "+998 97 525 97 12", R.drawable.img));
        contactList.add(new Contact("Odilbek Matyoqubov", "+998 99 568 44 38", R.drawable.img));
        contactList.add(new Contact("Jo'rabek Matyakubov", "+998 33 512 25 81", R.drawable.img));
        contactList.add(new Contact("Onajonim", "+998 99 568 25 81", R.drawable.img));
        contactList.add(new Contact("PDP Academy", "+998 78 777 47 47", R.drawable.img));

        return contactList;

    }

}