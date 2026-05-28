package com.example.carlog.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carlog.databinding.ItemRepairBinding;
import com.example.carlog.model.Repair;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class RepairAdapter extends ListAdapter<Repair, RepairAdapter.RepairViewHolder> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public RepairAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Repair> DIFF_CALLBACK = new DiffUtil.ItemCallback<Repair>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repair oldItem, @NonNull Repair newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repair oldItem, @NonNull Repair newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getCost() == newItem.getCost() &&
                    oldItem.getDate() == newItem.getDate();
        }
    };

    @NonNull
    @Override
    public RepairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRepairBinding binding = ItemRepairBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RepairViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class RepairViewHolder extends RecyclerView.ViewHolder {
        private final ItemRepairBinding binding;

        RepairViewHolder(ItemRepairBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Repair repair) {
            binding.tvRepairTitle.setText(repair.getTitle());
            binding.tvRepairDescription.setText(repair.getDescription());
            binding.tvRepairCost.setText(currencyFormat.format(repair.getCost()));
            binding.tvRepairDate.setText(dateFormat.format(repair.getDate()));
            binding.tvPartsChanged.setText(itemView.getContext().getString(R.string.label_parts_format, repair.getPartsChanged()));
        }
    }
}