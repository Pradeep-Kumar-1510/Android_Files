package com.example.firstapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.firstapp.fragments.JokeApiFragment;
import com.example.firstapp.fragments.MapFragment;
import com.example.firstapp.fragments.PostApiFragment;
import com.example.firstapp.fragments.ThreadFragment;

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
        } else if (position == 2) {
            return new JokeApiFragment();
        } else if (position == 3) {
            return new ThreadFragment();
        }
        return new PostApiFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
