package com.vaibhav.kardia.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vaibhav.kardia.R;
import com.vaibhav.kardia.databinding.UserDetailFragmentBinding;
import com.vaibhav.kardia.viewmodel.UserProfileViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UserDetailFragment extends Fragment {
    private UserProfileViewModel mViewModel;

    private UserDetailFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.user_detail_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(UserProfileViewModel.class);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setUserModel(mViewModel.getUser().getValue());
        mBinding.tvAgeValue.setText(mViewModel.calculateAge(mViewModel.dob));
    }

    public static UserDetailFragment forUser() {
        return new UserDetailFragment();
    }
}
