package com.gonzalo.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

  public interface OnLongClickListener {
    void onItemLongClicked(int position);
  }

  public interface OnClickListener {
    void onItemClicked(int position);
  }

  private List<String> items;
  private OnLongClickListener longClickListener;
  private OnClickListener clickListener;

  public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
    this.items = items;
    this.longClickListener = longClickListener;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // Use layout inflator to inflate a view
    View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
    // Wrap it inside a view holder  and return it
    return new ViewHolder(todoView);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // Grab item  at the position
    String item = items.get(position);
    // Bind the item into the specified view holder
    holder.bind(item);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  // Container to provide easy access to views that represent each row of the list
  class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvItem;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvItem = itemView.findViewById(android.R.id.text1);
    }

    public void bind(String item) {
      // Update view inside the view holder
      tvItem.setText(item);
      tvItem.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          // Notify the listener which position was long pressed
          longClickListener.onItemLongClicked(getAdapterPosition());
          return true;
        }
      });

      tvItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          clickListener.onItemClicked(getAdapterPosition());
         }
      });
    }
  }
}
