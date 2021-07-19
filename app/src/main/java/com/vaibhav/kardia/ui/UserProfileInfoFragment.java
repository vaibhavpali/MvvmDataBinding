package com.vaibhav.kardia.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vaibhav.kardia.R;
import com.vaibhav.kardia.databinding.UserProfileFragmentBinding;
import com.vaibhav.kardia.model.User;
import com.vaibhav.kardia.viewmodel.UserProfileViewModel;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

public class UserProfileInfoFragment extends Fragment implements UserProfileViewModel.UserProfileContract {

    private UserProfileViewModel mViewModel;
    private UserProfileFragmentBinding mBinding;

    public static UserProfileInfoFragment newInstance() {
        return new UserProfileInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.user_profile_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(UserProfileViewModel.class);
        mViewModel.setUserProfileContract(this);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setUserModel(mViewModel);
        mViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(user).getFirstName())) {
                mBinding.edFirstName.setError("Enter valid first name");
                mBinding.edFirstName.requestFocus();
            } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getLastName())) {
                mBinding.edLastName.setError("Enter valid last name");
                mBinding.edLastName.requestFocus();
            }
        });
    }

    @Override
    public void performAction(User user) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) requireActivity()).show(user);
        }
    }
}