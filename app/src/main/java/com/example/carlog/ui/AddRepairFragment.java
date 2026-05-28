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
import com.example.carlog.R;
import com.example.carlog.databinding.FragmentAddRepairBinding;
import com.example.carlog.model.Repair;
import com.example.carlog.viewmodel.VehicleViewModel;

public class AddRepairFragment extends Fragment {

    private FragmentAddRepairBinding binding;
    private VehicleViewModel viewModel;
    private int vehicleId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddRepairBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

        if (getArguments() != null) {
            vehicleId = getArguments().getInt("vehicleId");
        }

        binding.btnSaveRepair.setOnClickListener(v -> saveRepair());
    }

    private void saveRepair() {
        String title = binding.etRepairTitle.getText().toString().trim();
        String description = binding.etRepairDescription.getText().toString().trim();
        String costStr = binding.etRepairCost.getText().toString().trim();
        String partsChanged = binding.etPartsChanged.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty() || costStr.isEmpty()) {
            Toast.makeText(requireContext(), R.string.msg_fill_all_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        double cost = Double.parseDouble(costStr);
        long date = System.currentTimeMillis();

        Repair repair = new Repair(vehicleId, title, description, cost, date, partsChanged);
        viewModel.insertRepair(repair);

        Toast.makeText(requireContext(), R.string.msg_repair_saved, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}