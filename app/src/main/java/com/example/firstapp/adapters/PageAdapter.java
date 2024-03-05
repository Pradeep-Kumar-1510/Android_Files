package com.example.firstapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.firstapp.fragments.JokeApiFragment;
import com.example.firstapp.fragments.MapFragment;
import com.example.firstapp.fragments.PostApiFragment;

public class PageAdapter extends FragmentStateAdapter {
    public PageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new PostApiFragment();
        } else if (position == 1) {
            return new MapFragment();
        } else if (position == 2)
            return new JokeApiFragment();
        return new PostApiFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
