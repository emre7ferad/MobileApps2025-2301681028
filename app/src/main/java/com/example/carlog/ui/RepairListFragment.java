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
import com.example.carlog.databinding.FragmentRepairListBinding;
import com.example.carlog.ui.adapter.RepairAdapter;
import com.example.carlog.viewmodel.VehicleViewModel;

public class RepairListFragment extends Fragment {

    private FragmentRepairListBinding binding;
    private VehicleViewModel viewModel;
    private int vehicleId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRepairListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);

        if (getArguments() != null) {
            vehicleId = getArguments().getInt("vehicleId");
        }

        RepairAdapter adapter = new RepairAdapter();
        binding.rvRepairs.setAdapter(adapter);

        viewModel.getRepairsForVehicle(vehicleId).observe(getViewLifecycleOwner(), repairs -> {
            adapter.submitList(repairs);
            binding.tvEmptyRepairs.setVisibility(repairs.isEmpty() ? View.VISIBLE : View.GONE);
            binding.tvEmptyRepairs.setText(R.string.msg_empty_repairs);
        });

        binding.fabAddRepair.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putInt("vehicleId", vehicleId);
            Navigation.findNavController(v).navigate(R.id.action_repairListFragment_to_addRepairFragment, args);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}