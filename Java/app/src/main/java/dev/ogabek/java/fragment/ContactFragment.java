package dev.ogabek.java.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dev.ogabek.java.R;
import dev.ogabek.java.model.Contact;

public class ContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    public ContactFragment newInstance(Contact contact) {
        ContactFragment contactFragment = new ContactFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("contact", contact);
        contactFragment.setArguments(bundle);
        return contactFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Contact contact = getArguments().getParcelable("contact");

        ImageView photo = view.findViewById(R.id.iv_person_photo);
        TextView name = view.findViewById(R.id.tv_person_name);
        TextView number = view.findViewById(R.id.tv_person_number);

        photo.setImageResource(contact.getPhoto());
        name.setText(contact.getName());
        number.setText(contact.getNumber());

        ImageView call = view.findViewById(R.id.person_call);
        ImageView sms = view.findViewById(R.id.person_sms);
        ImageView video = view.findViewById(R.id.person_video);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(
                        number.getText().toString()
                );
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "We don't support Video Call yet\nIt is coming", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void call(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    private void sendSMS(String number) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("sms", number, null));
        startActivity(intent);
    }
}