package com.example.dryruntagtofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashSet;

public class TagsMainActivity extends AppCompatActivity{

    private Integer searchOperation;
    /*
    * Search Operation
    * And -> 1
    * OR - > 2
    * Not -> 3
    */
    private RecyclerView recyclerView;
    private ArrayList<String> tags;
    private EditText searchInput;
    ImageView btnSearchFilter;

    // Dialog components
    ImageView btnFilterClose;
    MaterialButton btnAndFilter, btnOrFilter, btnNotFilter;
    private ChipGroup andChipGroup, orChipGroup, notChipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_main);

        searchOperation = 1;
        recyclerView = findViewById(R.id.tags_recycler_view);
        searchInput = findViewById(R.id.search_input_bar);
        btnSearchFilter = findViewById(R.id.btn_search_filter);

        btnSearchFilter.setOnClickListener(view -> showDialog());
    }


    private void showDialog() {
        Dialog filterDialog = new Dialog(TagsMainActivity.this, R.style.filter_dialog_theme);
//        filterDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        filterDialog.setContentView(R.layout.search_filter_dialog);
        filterDialog.setCancelable(true);
        filterDialog.setCanceledOnTouchOutside(true);

        btnFilterClose = filterDialog.findViewById(R.id.btn_filter_close);
        btnAndFilter = filterDialog.findViewById(R.id.btn_and_filter);
        btnOrFilter = filterDialog.findViewById(R.id.btn_or_filter);
        btnNotFilter = filterDialog.findViewById(R.id.btn_not_filter);
        andChipGroup = filterDialog.findViewById(R.id.and_filter_chipGroup);
        orChipGroup = filterDialog.findViewById(R.id.or_filter_chipGroup);
        notChipGroup = filterDialog.findViewById(R.id.not_filter_chipGroup);

        btnFilterClose.setOnClickListener(view -> filterDialog.dismiss());

        btnAndFilter.setOnClickListener(view -> searchOperation = 1);
        btnOrFilter.setOnClickListener(view -> searchOperation = 2);
        btnNotFilter.setOnClickListener(view -> searchOperation = 3);

        filterDialog.show();
    }

    private void onSelectTag(String tagName, ChipGroup chipGroup) {
        Chip chip = new Chip(this);
        chip.setText(tagName);
        chip.setCloseIconVisible(true);
        chip.setCheckable(false);
        chip.setClickable(false);

        // chip.setOnCloseIconClickListener(this);
        chip.setOnCloseIconClickListener(view -> {
            Chip chip1 = (Chip) view;
            chipGroup.removeView(chip1);
        });

        chipGroup.addView(chip);
        chipGroup.setVisibility(View.VISIBLE);
    }

    /*
    private HashSet<String> union(ArrayList<Integer> tags) {
        HashSet<String> unionSet = new HashSet<String>();
        for(Integer tagUID : tags) {
            //getFilesForATag()
            String filesString = getFilesForATag(tagUID);
            ArrayList<String> files = (ArrayList<String>) Arrays.asList(filesString.split(";"));
            for (String filePath : files){
                unionSet.add(filePath);
            }
        }
        return unionSet;
    }

    private HashSet<String> intersection(ArrayList<Integer> tags) {}
    */

}