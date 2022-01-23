package dev.ogabek.kotlin.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ogabek.kotlin.R
import dev.ogabek.kotlin.activity.MainActivity
import dev.ogabek.kotlin.adapter.ContactAdapter
import dev.ogabek.kotlin.listener.SendContactListener
import dev.ogabek.kotlin.model.Contact

class ListFragment : Fragment() {

    private val mainActivity = MainActivity()

    private lateinit var listener: SendContactListener

    private val contactFragment: ContactFragment = ContactFragment()

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactList = arguments?.getParcelableArrayList<Contact>("contactList")

        recyclerView = view.findViewById(R.id.rv_contact_list_recycler_view)

        val contactAdapter = ContactAdapter(contactList as ArrayList<Contact>, ContactAdapter.ItemClickListener {
            listener.onSendContact(it)
        })

        recyclerView.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

    }

    fun clickListener(listener: SendContactListener) {
        this.listener = listener
    }

    fun newInstance(contactList: ArrayList<Contact>): ListFragment {
        val contactFragment = ListFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("contactList", contactList)
        contactFragment.arguments = bundle
        return contactFragment
    }

}