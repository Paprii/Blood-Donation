package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class DonerRVAdapter<CourseModal> extends RecyclerView.Adapter<DonerRVAdapter.ViewHolder> {

    /**
     * variable for our array list and context.
     */
    private ArrayList<CourseModal> donerModalArrayList;
    private Context context;

    /**
     * This is constractor
     * @param courseModalArrayList
     * @param context
     */
    public DonerRVAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.donerModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * on below line we are inflating our layout
         * file for our recycler view items.
         */

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doner_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /**
         * on below line we are setting data
         * to our views of recycler view item.
         */
        CourseModal modal = donerModalArrayList.get(position);
        holder.donerNameTV.setText(modal.getDonerName());
        holder.locationTV.setText(modal.getLocation());
        holder.bloodGroupTV.setText(modal.getBloodGroup());
        holder.email.setText(modal.getEmail());

        /**
         * below line is to add on click listener for our recycler view item.
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * on below line we are calling an intent.
                 */
                Intent i = new Intent(context, UpdateDonerActivity.class);

                /**
                 * below we are passing all our values.
                 */
                i.putExtra("name", modal.getDonerName());
                i.putExtra("description", modal.getLocation());
                i.putExtra("duration", modal.getBloodGroup());
                i.putExtra("tracks", modal.getEmail());

                /**
                 * starting our activity.
                 */
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        /**
         * returning the size of our array list.
         */
        return donerModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * creating variables for our text views.
         */
        private TextView donerNameTV, locationTV, bloodGroupTV, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /**
             * initializing our text views.
             */
            donerNameTV = itemView.findViewById(R.id.idTVgetDonerName);
            locationTV = itemView.findViewById(R.id.idTVgetLocation);
            bloodGroupTV = itemView.findViewById(R.id.idTVgetBloodGroup);
            email = itemView.findViewById(R.id.idTVgetEmail);
        }
    }
}

