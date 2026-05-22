package com.example.carlog.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.carlog.R;
import com.example.carlog.databinding.FragmentVehicleListBinding;
import com.example.carlog.ui.adapter.VehicleAdapter;
import com.example.carlog.viewmodel.VehicleViewModel;

public class VehicleListFragment extends Fragment {

    private FragmentVehicleListBinding binding;
    private VehicleViewModel viewModel;
    private VehicleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVehicleListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
        setupRecyclerView();

        binding.fabAddVehicle.setOnClickListener(v -> {
            // Will navigate to add vehicle fragment
            Navigation.findNavController(v).navigate(R.id.action_vehicleListFragment_to_addVehicleFragment);
        });

        viewModel.getAllVehicles().observe(getViewLifecycleOwner(), vehicles -> {
            adapter.submitList(vehicles);
        });
    }

    private void setupRecyclerView() {
        adapter = new VehicleAdapter(vehicle -> {
            // Handle vehicle click - navigate to details
            Bundle args = new Bundle();
            args.putInt("vehicleId", vehicle.getId());
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_vehicleListFragment_to_vehicleDetailFragment, args);
        });
        binding.rvVehicles.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}