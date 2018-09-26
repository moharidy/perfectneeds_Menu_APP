package com.example.mohammed.Menu_App;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<Menu_Item> {
    private int mColorResourceId;
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param menuItems A List of AndroidFlavor objects to display in a list
     */
    public MenuItemAdapter(Activity context, ArrayList<Menu_Item> menuItems, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, menuItems);
         mColorResourceId = colorResourceId;
    }
    /**
 * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
             * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
            * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false); }

        Menu_Item currentMenuItem = getItem(position);

        TextView menuitemTextView = listItemView.findViewById(R.id.menu_item_text_view);
        menuitemTextView.setText(currentMenuItem.getmMenuitem());

        TextView descriptionTextView =listItemView.findViewById(R.id.menu_item_description_text_view);
        descriptionTextView.setText(currentMenuItem.getmMenuitemDescription());

        ImageView imageView = listItemView.findViewById(R.id.image_view);

        if(currentMenuItem.hasImage()){
        imageView.setImageResource(currentMenuItem.getmImageResourceId());imageView.setVisibility(View.VISIBLE);}
        else { imageView.setVisibility(View.GONE);}

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;

        }


}
