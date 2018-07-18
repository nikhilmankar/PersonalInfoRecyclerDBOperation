package com.example.nikhil.personalinfodata;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class PersonViewHolder extends RecyclerView.ViewHolder
{
    TextView txtName, txtAddress, txtPhone;

    public PersonViewHolder(View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txtName);
        txtAddress = itemView.findViewById(R.id.txtAddress);
        txtPhone = itemView.findViewById(R.id.txtPhone);
    }
}
