package com.example.silviumall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class GroceryItemActivity extends AppCompatActivity {
    private static final String TAG = "GroceryItemActivity";

    public static final String GROCERY_ITEM_KEY = "incoming_item";

    private RecyclerView reviewsRecView;
    private TextView txtName, txtPrice, txtDescription, txtAddReview;
    private ImageView itemImage, firstEmptyStar, firstFilledStar, secondEmptyStar, secondFilledStar,
            thirdEmptyStar, thirdFilledStar;
    private Button btnAddToCart;
    private RelativeLayout firstStarRelLayout, secondStarRelLayout, thirdStarRelLayout;

    private GroceryItem incomingItem;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);

        initViews();

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (null != intent) {
            incomingItem = intent.getParcelableExtra(GROCERY_ITEM_KEY);
            if (null != incomingItem) {
                txtName.setText(incomingItem.getName());
                txtPrice.setText(String.valueOf(incomingItem.getPrice()) + "RON");
                txtDescription.setText(incomingItem.getDescription());
                Glide.with(this)
                        .asBitmap()
                        .load(incomingItem.getImageURL())
                        .into(itemImage);
                ArrayList<Review> reviews = incomingItem.getReviews();
                if (null != reviews) {
                    if (reviews.size() > 0) {
                        ReviewsAdapter adapter = new ReviewsAdapter();
                        reviewsRecView.setAdapter(adapter);
                        reviewsRecView.setLayoutManager(new LinearLayoutManager(this));
                        adapter.setReviews(reviews);
                    }
                }
                btnAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Add item to the cart
                    }
                });

                txtAddReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 7/4/2021 Show a dialog
                    }
                });

                handleRating();
            }
        }
    }

    private void handleRating() {
        switch (incomingItem.getRate()) {
            case 0:
                firstEmptyStar.setVisibility(View.VISIBLE);
                firstFilledStar.setVisibility(View.GONE);
                secondEmptyStar.setVisibility(View.VISIBLE);
                secondFilledStar.setVisibility(View.GONE);
                thirdEmptyStar.setVisibility(View.VISIBLE);
                thirdFilledStar.setVisibility(View.GONE);
                break;
            case 1:
                firstEmptyStar.setVisibility(View.GONE);
                firstFilledStar.setVisibility(View.VISIBLE);
                secondEmptyStar.setVisibility(View.VISIBLE);
                secondFilledStar.setVisibility(View.GONE);
                thirdEmptyStar.setVisibility(View.VISIBLE);
                thirdFilledStar.setVisibility(View.GONE);
                break;
            case 2:
                firstEmptyStar.setVisibility(View.GONE);
                firstFilledStar.setVisibility(View.VISIBLE);
                secondEmptyStar.setVisibility(View.GONE);
                secondFilledStar.setVisibility(View.VISIBLE);
                thirdEmptyStar.setVisibility(View.VISIBLE);
                thirdFilledStar.setVisibility(View.GONE);
                break;
            case 3:
                firstEmptyStar.setVisibility(View.GONE);
                firstFilledStar.setVisibility(View.VISIBLE);
                secondEmptyStar.setVisibility(View.GONE);
                secondFilledStar.setVisibility(View.VISIBLE);
                thirdEmptyStar.setVisibility(View.GONE);
                thirdFilledStar.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        firstStarRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incomingItem.getRate() != 1) {
                    Utils.changeRate(GroceryItemActivity.this, incomingItem.getId(), 1);
                    incomingItem.setRate(1);
                    handleRating();
                }
            }
        });

        secondStarRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incomingItem.getId() != 2) {
                    Utils.changeRate(GroceryItemActivity.this, incomingItem.getId(), 2);
                    incomingItem.setRate(2);
                    handleRating();
                }
            }
        });

        thirdStarRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incomingItem.getId() != 3) {
                    Utils.changeRate(GroceryItemActivity.this, incomingItem.getId(), 3);
                    incomingItem.setRate(3);
                    handleRating();
                }
            }
        });
    }

    private void initViews() {
        reviewsRecView = findViewById(R.id.reviewsRecView);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtAddReview = findViewById(R.id.txtAddReview);
        itemImage = findViewById(R.id.itemImage);
        firstEmptyStar = findViewById(R.id.firstEmptyStar);
        firstFilledStar = findViewById(R.id.firstFilledStar);
        secondEmptyStar = findViewById(R.id.secondEmptyStar);
        secondFilledStar = findViewById(R.id.secondFilledStar);
        thirdEmptyStar = findViewById(R.id.thirdEmptyStar);
        thirdFilledStar = findViewById(R.id.thirdFilledStar);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        firstStarRelLayout = findViewById(R.id.firstStarRelLayout);
        secondStarRelLayout = findViewById(R.id.secondStarRelLayout);
        thirdStarRelLayout = findViewById(R.id.thirdStarRelLayout);
        toolbar = findViewById(R.id.toolbar);
    }
}