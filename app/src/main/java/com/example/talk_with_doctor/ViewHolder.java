package com.example.talk_with_doctor;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemlongClick(view, getAdapterPosition());
                return false;
            }
        });
    }

    public void setData(Context context, String ID, String name, String password, int mobile, String email, String category, String hospital){
       TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("ID: " + ID + "\n" + "Name: " + name + "\n" + "Password: " + password + "\n" + "Mobile: " + mobile + "\n" +
                "Email: " + email + "\n" + "Category: " + category + "\n" + "Hospital: " + hospital);

    }

    public void setPharmacyData(Context context, String ID, String name, String password, int mobile, String email, String address, String city){
        TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("ID: " + ID + "\n" + "Name: " + name + "\n" + "Password: " + password + "\n" + "Mobile: " + mobile + "\n" +
                "Email: " + email + "\n" + "Address: " + address + "\n" + "City: " + city);

    }

    public void setPatientData(Context context, String name, String email, String address, int mobile, String password){
        TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("Name: " + name + "\n" + "Email: " + email + "\n" + "Address: " + address + "\n" +
                "Mobile: " + mobile + "\n" + "Password: " + password);

    }

    private ViewHolder.Clicklistener mClicklistener;

    public interface Clicklistener{
        void onItemlongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.Clicklistener clickListener){
        mClicklistener = clickListener;
    }


}
