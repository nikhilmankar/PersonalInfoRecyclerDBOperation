package com.example.nikhil.personalinfodata;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    List<Person> personList;

    public PersonListAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_list, null);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.txtName.setText(person.getName());
        holder.txtAddress.setText(person.getAddress());
        holder.txtPhone.setText(person.getPhone());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
