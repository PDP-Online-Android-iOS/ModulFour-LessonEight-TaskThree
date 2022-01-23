package dev.ogabek.java.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private final String name;
    private final String number;
    private final int photo;

    public Contact(String name, String number, int photo) {
        this.name = name;
        this.number = number;
        this.photo = photo;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        number = in.readString();
        photo = in.readInt();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public int getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeInt(photo);
    }
}
