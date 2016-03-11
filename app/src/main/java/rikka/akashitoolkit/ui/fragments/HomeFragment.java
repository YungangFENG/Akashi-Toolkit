package rikka.akashitoolkit.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rikka.akashitoolkit.R;
import rikka.akashitoolkit.ui.MainActivity;

/**
 * Created by Rikka on 2016/3/6.
 */
public class HomeFragment extends BaseFragmet {
    private static final int TAB_LAYOUT_VISIBILITY = View.GONE;

    @Override
    public void onShow() {
        MainActivity activity = ((MainActivity) getActivity());
        activity.getTabLayout().setVisibility(TAB_LAYOUT_VISIBILITY);
        activity.getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_home, container, false);

        if (!isHiddenBeforeSaveInstanceState()) {
            onShow();
        }

        return view;
    }
}
