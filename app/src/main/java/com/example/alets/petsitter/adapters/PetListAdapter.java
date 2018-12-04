package com.example.alets.petsitter.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alets.petsitter.ConectionDetails;
import com.example.alets.petsitter.Login;
import com.example.alets.petsitter.R;
import com.example.alets.petsitter.TestActivity;
import com.example.alets.petsitter.pojos.FullInformation;

import java.util.ArrayList;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.MyViewHolder> {

    private ArrayList<FullInformation> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        TextView tvDateBegin,tvDateEnd,tvName, tvSpecies,tvProprio,tvPrice,tvPlace;
        ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            mView = v;

            tvDateBegin = v.findViewById(R.id.tvDateBegin);
            tvDateEnd = v.findViewById(R.id.tvDateEnd);
            tvName = v.findViewById(R.id.tvName);
            tvSpecies = v.findViewById(R.id.tvSpecies);
            tvProprio = v.findViewById(R.id.tvProprio);
            tvPrice = v.findViewById(R.id.tvPrice);
            tvPlace = v.findViewById(R.id.tvPlace);
            imageView = v.findViewById(R.id.imageView);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PetListAdapter(ArrayList<FullInformation> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PetListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pet_item_list, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        FullInformation fi= mDataset.get(position);

        holder.tvDateBegin.setText(fi.getC().getDate().toString());
        holder.tvName.setText(fi.getAnimals().get(0).getPrenom());
        holder.tvSpecies.setText(fi.getAnimals().get(0).getEspece());
        holder.tvProprio.setText(fi.getpAnimal().getUserName());
//        holder.tvPrice.setText(fi.getC().getPrix());
  //      holder.tvPlace.setText(fi.getpAnimal().getCodePostale());

    }
    public  void setmDataset(ArrayList<FullInformation> dataset){
        mDataset = dataset;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
