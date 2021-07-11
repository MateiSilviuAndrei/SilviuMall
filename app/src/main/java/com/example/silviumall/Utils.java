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
    private static final String DB_NAME = "fake_database";
    private static Gson gson = new Gson();
    private static Type groceryListType = new TypeToken<ArrayList<GroceryItem>>(){}.getType();

    public static void initSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if (null == allItems) {
            initAllItems(context);
        }
    }

    public static void clearSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private static void initAllItems(Context context) {
        ArrayList<GroceryItem> allItems = new ArrayList<GroceryItem>();
        GroceryItem milk = new GroceryItem("Milk",
                "Milk (also known in unfermented form as sweet milk) is a nutrient-rich liquid food produced by the mammary glands of mammals. It is the primary source of nutrition for young mammals, including breastfed human infants before they are able to digest solid food.",
                "data:image/jpeg;base64,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",
                "drink", 2.3, 8);
        allItems.add(milk);

        GroceryItem iceCream = new GroceryItem("Ice Cream",
                "Ice cream (derived from earlier cream ice)[1] is a sweetened frozen food typically eaten as a snack or dessert.",
                "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.hpcismart.com%2Fimages%2Fwebsite%2FManChemTechnical%2FDIR_20%2FF_70920.jpg&imgrefurl=https%3A%2F%2Fcleanroomtechnology.com%2Fnews%2Farticle_page%2FNew_tech_for_ice_cream_making%2F146617&tbnid=8DbB726QPmNgKM&vet=12ahUKEwjyyNnl1sbxAhXAEWMBHSGaCwoQMygKegUIARDuAQ..i&docid=1-6oRrA8v-BxdM&w=800&h=450&q=ice%20cream&ved=2ahUKEwjyyNnl1sbxAhXAEWMBHSGaCwoQMygKegUIARDuAQ",
                "food", 5.4, 20);
        iceCream.setPopularityPoint(10);
        iceCream.setUserPoint(11);
        allItems.add(iceCream);

        GroceryItem soda = new GroceryItem("Soda", "Tasty", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fthumbs.dreamstime.com%2Fb%2Fsoda-can-red-aluminium-34903303.jpg&imgrefurl=https%3A%2F%2Fwww.dreamstime.com%2Fstock-photos-soda-can-red-aluminium-image34903303&tbnid=UNfsmdtxoYfHuM&vet=12ahUKEwjwn7fM18bxAhW15OAKHTl0CYsQMygHegUIARDgAQ..i&docid=JLfHcXqqIoK67M&w=800&h=800&q=soda&hl=ro&ved=2ahUKEwjwn7fM18bxAhW15OAKHTl0CYsQMygHegUIARDgAQ",
                "drink", 3.2, 15);
        soda.setUserPoint(10);
        allItems.add(soda);

        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allItems));
        editor.commit();
    }

    public static ArrayList<GroceryItem> getAllItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        return allItems;
    }

    public static void changeRate(Context context, int itemId, int newRate) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if (null != allItems) {
            ArrayList<GroceryItem> newItems = new ArrayList<GroceryItem>();
            for (GroceryItem i : allItems) {
                if (i.getId() == itemId) {
                    i.setRate(newRate);
                    newItems.add(i);
                }else {
                    newItems.add(i);
                }
            }

            editor.remove(ALL_ITEMS_KEY);
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }
    }

    public static int getID() {
        ID++;
        return ID;
    }
}
