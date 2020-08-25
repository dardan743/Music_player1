package com.dardan.musicplayer.Helpers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dardan.musicplayer.R;

public class BottomSheetDialogHelper extends BottomSheetDialogFragment {

    private BottomSheetListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        Button button1 = (Button) v.findViewById(R.id.applemusic);
        Button button2= v.findViewById(R.id.dropbox);
        Button button3 = v.findViewById(R.id.googledrive);
        Button button4 = v.findViewById(R.id.onedrive);
        Button button5 = v.findViewById(R.id.files);
        Button button6 = v.findViewById(R.id.cancel);
        return v;
    }
    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
