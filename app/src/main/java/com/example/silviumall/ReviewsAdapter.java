package com.example.silviumall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder>{

    private ArrayList<Review> reviews = new ArrayList<Review>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ViewHolder holder, int position) {
        holder.txtUserName.setTextContent(reviews.get(position).getUserName());
        holder.txtReview.setTextContent(reviews.get(position).getText());
        holder.txtDate.setTextContent(reviews.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Text txtUserName, txtDate, txtReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtReview = itemView.findViewById(R.id.txtReview);
        }
    }
}
