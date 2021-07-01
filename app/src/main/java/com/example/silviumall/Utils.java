package com.example.silviumall;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static int ID = 0;

    private static final String ALL_ITEMS_KEY = "all_items";
    private static final String DB_NAME = "fake_dataase";
    private static Gson gson = new Gson();
    private static Type groceryListType = new TypeToken<ArrayList<GroceryItem>>(){}.getType();

    public static void initSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if (null != allItems) {
            initAllItems(context);
        }
    }

    private static void initAllItems(Context context) {
        ArrayList<GroceryItem> allItems = new ArrayList<GroceryItem>();
        GroceryItem milk = new GroceryItem("Milk",
                "Milk (also known in unfermented form as sweet milk) is a nutrient-rich liquid food produced by the mammary glands of mammals. It is the primary source of nutrition for young mammals, including breastfed human infants before they are able to digest solid food.",
                "https://www.google.com/imgres?imgurl=https%3A%2F%2Fassets.tmecosys.com%2Fimage%2Fupload%2Ft_web767x639%2Fimg%2Frecipe%2Fras%2FAssets%2F82E83CE3-2558-4469-972A-5A4D9CB010C8%2FDerivates%2Fc0a449c4-81b0-447f-a4e6-e76ffcdf5e50.jpg&imgrefurl=https%3A%2F%2Fcookidoo.international%2Frecipes%2Frecipe%2Fro%2Fr329400&tbnid=VEVGRSYl1yriDM&vet=12ahUKEwiA5qqp_MHxAhWZkKQKHUH8AwMQMygFegUIARDLAQ..i&docid=ev3doyRfahvoGM&w=767&h=639&q=Milk&ved=2ahUKEwiA5qqp_MHxAhWZkKQKHUH8AwMQMygFegUIARDLAQ",
                "drink", 2.3, 8);
        allItems.add(milk);

        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allItems));
        editor.commit();
    }

    public static ArrayList<GroceryItem> getAllItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        return allItems;
    }

    public static int getID() {
        ID++;
        return ID;
    }
}
