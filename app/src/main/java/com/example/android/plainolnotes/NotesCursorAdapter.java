package com.example.android.plainolnotes;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//This Class is used to extract data from the SQLite Database and cast it to a textView
//Also handles line feeds and truncates it, replaying it with ellipsis

public class NotesCursorAdapter extends CursorAdapter{

    public NotesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(
                R.layout.note_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String noteText = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.NOTE_TEXT));

        //Look for a line feed
        //10 is the ASCII value of a line feed character
        int pos = noteText.indexOf(10);

        // -1 means the character is not present
        if (pos != -1) {
            noteText = noteText.substring(0,pos) + " ...";
        }

        //Cast noteText to TextView component
        TextView tv = (TextView) view.findViewById(R.id.tvNote);
        tv.setText(noteText);
    }
}
