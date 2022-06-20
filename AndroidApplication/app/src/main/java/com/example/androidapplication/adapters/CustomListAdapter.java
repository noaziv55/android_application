package com.example.androidapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication.R;
import com.example.androidapplication.entities.Contact;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView imageView;
        private final TextView contactName;
        private final TextView lastMsg;
        private final TextView time;
        private final TextView server;

        private ContactViewHolder (View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.profile_image);
            contactName = itemView.findViewById(R.id.user_name);
            lastMsg = itemView.findViewById(R.id.last_massage);
            time = itemView.findViewById(R.id.time);
            server = itemView.findViewById(R.id.etServer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts;
    private RecyclerViewClickListener listener;

    public CustomListAdapter(Context context, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.custom_list_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (contacts != null){
            final Contact current = contacts.get(position);
            holder.contactName.setText(current.getContactName());
            holder.lastMsg.setText(current.getLastMassage());
            holder.time.setText(current.getLastMassageSendingTime());
            holder.imageView.setImageResource(current.getProfileImg());
            // holder.server.setText(current.getServer());
        }
    }

    public void setContacts(List<Contact> s){
        contacts = s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (contacts != null){
            return contacts.size();
        }
        else{
            return 0;
        }
    }

    public List<Contact> getContacts(){
        return contacts;
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}