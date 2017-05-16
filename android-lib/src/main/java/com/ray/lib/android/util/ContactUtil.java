package com.ray.lib.android.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * utils for contact
 * 关于联系人的工具箱
 */
public class ContactUtil {
    private ContactUtil() {
    }

    public static ArrayList<Contact> getAllContacts(Context context) {
        ArrayList<Contact> list = new ArrayList<Contact>();
        ContentResolver resolver = context.getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
        Cursor cursor = null;

        try {
            cursor = resolver.query(uri, projection, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    Contact contact = new ContactUtil().new Contact(id, name, number);
                    list.add(contact);
                }
            }
            return list;
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
    }

    public static Bitmap getContactImg(Context context, int id) {
        Bitmap bitmap = null;
        ContentResolver resolver = context.getContentResolver();
        Uri contactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(id));
        InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(resolver, contactUri);
        bitmap = BitmapFactory.decodeStream(inputStream);
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    public class Contact implements Serializable {
        private static final long serialVersionUID = 2607565403949049699L;
        public int id;
        public String name;
        public String number;

        public String toString() {
            return "Contact [id=" + id + ", name=" + name + ", number=" + number + "]";
        }

        public Contact(int id, String name, String number) {
            super();
            this.id = id;
            this.name = name;
            this.number = number;
        }

        public Contact() {

        }

    }

    // <uses-permission android:name="android.permission.READ_CONTACTS" />
    // <uses-permission android:name="android.permission.WRITE_CONTACTS" />
    // <uses-permission android:name="android.permission.READ_CALL_LOG" />
    // <uses-permission android:name="android.permission.WRITE_CALL_LOG" />
    public void deleteCallLog(final Context context, final String number) {
        final ContentResolver resolver = context.getContentResolver();
        resolver.registerContentObserver(CallLog.Calls.CONTENT_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
//                resolver.delete(CallLog.Calls.CONTENT_URI,CallLog.Calls.NUMBER + "=?", new String[]{number}));
            }
        });
    }


}
