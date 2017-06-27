package com.ray.baseandroid.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ray.baseandroid.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private final Context mContext;
    private final List<Person> mPersons;
    private final LayoutInflater mInflater;

    public PersonAdapter(Context context, List<Person> persons) {
        mPersons = persons;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_person_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person person = mPersons.get(position);
        holder.tvName.setText(person.getName());
        holder.tvSex.setText(person.getSex());
        holder.tvAge.setText(String.valueOf(person.getAge()));
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }
}