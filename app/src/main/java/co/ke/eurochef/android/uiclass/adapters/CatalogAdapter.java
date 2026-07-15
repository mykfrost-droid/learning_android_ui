package co.ke.eurochef.android.uiclass.adapters;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CatalogAdapter  extends BaseAdapter {
    private List<String> catalogItems ;
    private Context context;
    @Override
    public int getCount() {
        return  catalogItems.size();
    }

    @Override
    public Object getItem(int position) {
        return catalogItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // ==========================================
    // THE CRITICAL LISTADAPTER METHODS
    // ==========================================

    @Override
    public boolean areAllItemsEnabled() {
        //We return false because not all items are enabled
        // This forces the ListView to check isEnabled for each row
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        // check the data at this position
        String item = getItem(position);

        // If its a header , return false ie not clickable , else return true
        if (item.startsWith("[HEADER]")) {
            return false;
        }
        return true;
    }
    // ==========================================
    // OPTIMIZED GETVIEW WITH VIEWHOLDER
    // ==========================================
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        //Basic View recycling
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        // Instantiate the viewholder
            holder  = new viewHolder();
       // Perform the expensive FindViewby ID lookup once
       holder.textView = convertView.findViewById(android.R.id.text1);
       //Attach the viewholder to the recycled view
        convertView.setTag(holder);
        }else{
    //  Recycling (Instantly retrieve the cached UI components)
        holder = (viewHolder) convertView.getTag();
        }
        // 3. Data Binding (Using the cached holder.textView)
        String currentItem = getItem(position);

        // Visual distinction so users know what is a header vs an item
        if (currentItem.startsWith("[HEADER]")) {
            // Format as a Header
            holder.textView.setText(currentItem.replace("[HEADER] ", "")); // Clean up text
            convertView.setBackgroundColor(Color.LTGRAY); // Gray background
            holder.textView.setTypeface(null, Typeface.BOLD);    // Bold text
        } else {
            // Format as a normal item
            holder.textView.setText(currentItem);
            convertView.setBackgroundColor(Color.TRANSPARENT); // Normal background
            holder.textView.setTypeface(null, Typeface.NORMAL);       // Normal text
        }

        return convertView;
    }

    // THE VIEWHOLDER CLASS
    // ==========================================

    // Static class to cache the UI references
    static class viewHolder {
        TextView textView;
    }

}
