package com.example.carlog.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carlog.databinding.ItemVehicleBinding;
import com.example.carlog.model.Vehicle;

public class VehicleAdapter extends ListAdapter<Vehicle, VehicleAdapter.VehicleViewHolder> {

    private final OnVehicleClickListener listener;

    public interface OnVehicleClickListener {
        void onVehicleClick(Vehicle vehicle);
    }

    public VehicleAdapter(OnVehicleClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<Vehicle> DIFF_CALLBACK = new DiffUtil.ItemCallback<Vehicle>() {
        @Override
        public boolean areItemsTheSame(@NonNull Vehicle oldItem, @NonNull Vehicle newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Vehicle oldItem, @NonNull Vehicle newItem) {
            return oldItem.getMake().equals(newItem.getMake()) &&
                    oldItem.getModel().equals(newItem.getModel()) &&
                    oldItem.getLicensePlate().equals(newItem.getLicensePlate());
        }
    };

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVehicleBinding binding = ItemVehicleBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new VehicleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        private final ItemVehicleBinding binding;

        public VehicleViewHolder(ItemVehicleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Vehicle vehicle, final OnVehicleClickListener listener) {
            binding.tvVehicleMakeModel.setText(vehicle.getMake() + " " + vehicle.getModel());
            binding.tvVehiclePlate.setText(vehicle.getLicensePlate());
            binding.tvVehicleYear.setText(String.valueOf(vehicle.getYear()));
            
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onVehicleClick(vehicle);
                }
            });
        }
    }
}