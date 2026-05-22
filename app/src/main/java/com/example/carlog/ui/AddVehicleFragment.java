package com.example.carlog.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.carlog.databinding.FragmentAddVehicleBinding;
import com.example.carlog.model.Vehicle;
import com.example.carlog.viewmodel.VehicleViewModel;

public class AddVehicleFragment extends Fragment {

    private FragmentAddVehicleBinding binding;
    private VehicleViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddVehicleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

        binding.btnSave.setOnClickListener(v -> saveVehicle());
    }

    private void saveVehicle() {
        String make = binding.etMake.getText().toString().trim();
        String model = binding.etModel.getText().toString().trim();
        String yearStr = binding.etYear.getText().toString().trim();
        String plate = binding.etLicensePlate.getText().toString().trim();

        if (make.isEmpty() || model.isEmpty() || yearStr.isEmpty() || plate.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int year = Integer.parseInt(yearStr);
        Vehicle vehicle = new Vehicle(make, model, year, plate);
        viewModel.insert(vehicle);

        Toast.makeText(requireContext(), "Vehicle saved", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}