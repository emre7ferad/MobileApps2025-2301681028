package com.example.carlog.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.carlog.R;
import com.example.carlog.databinding.FragmentVehicleDetailBinding;
import com.example.carlog.model.Vehicle;
import com.example.carlog.viewmodel.VehicleViewModel;

public class VehicleDetailFragment extends Fragment {

    private FragmentVehicleDetailBinding binding;
    private VehicleViewModel viewModel;
    private int vehicleId;
    private Vehicle currentVehicle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVehicleDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

        if (getArguments() != null) {
            vehicleId = getArguments().getInt("vehicleId");
            viewModel.getVehicleById(vehicleId).observe(getViewLifecycleOwner(), vehicle -> {
                if (vehicle != null) {
                    currentVehicle = vehicle;
                    populateFields(vehicle);
                }
            });
        }

        binding.btnUpdate.setOnClickListener(v -> updateVehicle());
        binding.btnDelete.setOnClickListener(v -> showDeleteConfirmation());
        binding.btnViewRepairs.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putInt("vehicleId", vehicleId);
            Navigation.findNavController(v).navigate(R.id.action_vehicleDetailFragment_to_repairListFragment, args);
        });
    }

    private void populateFields(Vehicle vehicle) {
        binding.etMake.setText(vehicle.getMake());
        binding.etModel.setText(vehicle.getModel());
        binding.etYear.setText(String.valueOf(vehicle.getYear()));
        binding.etLicensePlate.setText(vehicle.getLicensePlate());
    }

    private void updateVehicle() {
        String make = binding.etMake.getText().toString().trim();
        String model = binding.etModel.getText().toString().trim();
        String yearStr = binding.etYear.getText().toString().trim();
        String plate = binding.etLicensePlate.getText().toString().trim();

        if (make.isEmpty() || model.isEmpty() || yearStr.isEmpty() || plate.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        currentVehicle.setMake(make);
        currentVehicle.setModel(model);
        currentVehicle.setYear(Integer.parseInt(yearStr));
        currentVehicle.setLicensePlate(plate);

        viewModel.update(currentVehicle);
        Toast.makeText(requireContext(), "Vehicle updated", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Vehicle")
                .setMessage("Are you sure you want to delete this vehicle and all its repair history?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    viewModel.delete(currentVehicle);
                    Toast.makeText(requireContext(), "Vehicle deleted", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(requireView()).popBackStack();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}