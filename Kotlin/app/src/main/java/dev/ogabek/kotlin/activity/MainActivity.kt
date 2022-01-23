package dev.ogabek.kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.ogabek.kotlin.R
import dev.ogabek.kotlin.fragment.ContactFragment
import dev.ogabek.kotlin.fragment.ListFragment
import dev.ogabek.kotlin.listener.SendContactListener
import dev.ogabek.kotlin.model.Contact

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment = ListFragment().newInstance(getContacts() as ArrayList<Contact>)

        listFragment.clickListener(object : SendContactListener{
            override fun onSendContact(contact: Contact) {
                val contactFragment = ContactFragment().newInstance(contact)
                createContactFragment(contactFragment)
            }

        })

        createListFragment(listFragment)

    }

    private fun createListFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_list, fragment)
            .commit()
    }

    private fun createContactFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_contact, fragment)
            .commit()
    }

    fun getContacts(): List<Contact> {


        val contactList = ArrayList<Contact>()

        contactList.add(Contact("Ogabek Matyakubov", "+998 99 749 25 81", R.drawable.img))
        contactList.add(Contact("Bogibek Matyaqubov", "+998 97 525 97 12", R.drawable.img))
        contactList.add(Contact("Odilbek Matyoqubov", "+998 99 568 44 38", R.drawable.img))
        contactList.add(Contact("Jo'rabek Matyakubov", "+998 33 512 25 81", R.drawable.img))
        contactList.add(Contact("Onajonim", "+998 99 568 25 81", R.drawable.img))
        contactList.add(Contact("PDP Academy", "+998 78 777 47 47", R.drawable.img))

        return contactList

    }

}