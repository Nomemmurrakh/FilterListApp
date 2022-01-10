package com.example.filterlistapp.adapters;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filterlistapp.databinding.LayoutChocolateListItemBinding;
import com.example.filterlistapp.models.Chocolate;

import java.util.ArrayList;
import java.util.List;

public class ChocolateListAdapter extends ListAdapter<Chocolate, ChocolateListAdapter.ChocolateHolder> implements Filterable {

    private static final DiffUtil.ItemCallback<Chocolate> CALLBACK = new DiffUtil.ItemCallback<Chocolate>() {
        @Override
        public boolean areItemsTheSame(@NonNull Chocolate oldItem, @NonNull Chocolate newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Chocolate oldItem, @NonNull Chocolate newItem) {
            return oldItem.equals(newItem);
        }
    };

    public interface OnListEmptyListener {
        void onListEmpty(boolean isEmpty);
    }

    private OnListEmptyListener listener;

    private List<Chocolate> chocolatesCopy;

    public ChocolateListAdapter() {
        super(CALLBACK);
    }

    public void setOnListEmptyListener(OnListEmptyListener listener){
        this.listener = listener;
    }

    @Override
    public void submitList(@Nullable List<Chocolate> list) {
        super.submitList(list);
        this.chocolatesCopy = list != null ? new ArrayList<>(list) : new ArrayList<>();
    }

    @NonNull
    @Override
    public ChocolateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChocolateHolder(
                LayoutChocolateListItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ChocolateHolder holder, int position) {
        holder.bindWith(getItem(position));
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Chocolate> filteredList = new ArrayList<>();
                if (constraint == null || TextUtils.isEmpty(constraint)){
                    filteredList.addAll(chocolatesCopy);
                }else {
                    for (Chocolate chocolate : chocolatesCopy){
                        if (chocolate.getName().toLowerCase().contains(constraint.toString().trim().toLowerCase())){
                            filteredList.add(chocolate);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<Chocolate> filteredList = (List<Chocolate>) results.values;
                Log.d("TAG", "performFiltering: " + filteredList.isEmpty());
                if (listener != null){
                    listener.onListEmpty(filteredList.isEmpty());
                }
                ChocolateListAdapter.super.submitList((List<Chocolate>) results.values);
            }
        };
        return filter;
    }

    static class ChocolateHolder extends RecyclerView.ViewHolder{

        private LayoutChocolateListItemBinding binding;

        public ChocolateHolder(@NonNull LayoutChocolateListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bindWith(Chocolate item) {
            binding.txtChocolateName.setText(item.getName());
            binding.txtChocolateType.setText(item.getType());
            binding.txtChocolatePrice.setText(String.valueOf(item.getPrice()));
        }
    }
}
