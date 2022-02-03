package com.gzeinnumer.pd.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.pd.R;

import java.util.ArrayList;
import java.util.List;

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.MyHolder> {

    private Context context;
    private final List<String> list;
    private final List<String> listFilter;

    public ColumnAdapter(List<String> list) {
        this.list = list;
        this.listFilter = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pdb_item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.textView.setText(list.get(position));

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();

        prepareSpace(layoutParams, position, holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv);
            textView = itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public int intToDp(int sizeInDPH) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDPH, context.getResources().getDisplayMetrics());
    }

    private void prepareSpace(ViewGroup.MarginLayoutParams layoutParams, int position, MyHolder holder) {
        int topBottomRv = 6;
        int leftRightItem = 6;
        int spaceBetween = 6 / 2;
        if (position == 0) {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(topBottomRv), intToDp(leftRightItem), intToDp(spaceBetween));
        } else if (position == list.size()) {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(spaceBetween), intToDp(leftRightItem), intToDp(topBottomRv));
        } else {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(spaceBetween), intToDp(leftRightItem), intToDp(spaceBetween));
        }
        holder.cardView.setLayoutParams(layoutParams);
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> fildteredList = new ArrayList<>();
            if (constraint != null || constraint.length() != 0) {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String item : listFilter) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        fildteredList.add(item);
                    }
                }
            } else {
                fildteredList.addAll(list);
            }
            FilterResults results = new FilterResults();
            results.values = fildteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public Filter getFilter() {
        return filter;
    }
}