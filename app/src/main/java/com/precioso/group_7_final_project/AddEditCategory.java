package com.precioso.group_7_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.precioso.group_7_final_project.databinding.ActivityAddEditCategoryBinding;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adapter.CategoryAdapter;
import model.CategoryModelClass;
import utils.SharedPreference;

public class AddEditCategory extends AppCompatActivity implements View.OnClickListener {

    ActivityAddEditCategoryBinding addEditCategoryBinding;
    private CategoryAdapter categoryAdapter;
    private List<CategoryModelClass> categoryList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addEditCategoryBinding = ActivityAddEditCategoryBinding.inflate(getLayoutInflater()); // Update with your actual binding class name
        setContentView(addEditCategoryBinding.getRoot());
        addEditCategoryBinding.btnAddCategory.setOnClickListener(this);
        recyclerView = addEditCategoryBinding.rcrCategory;
        categoryList = SharedPreference.getCategoryList(this);
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        } else {
            categoryAdapter = new CategoryAdapter(categoryList, this);
            recyclerView.setAdapter(categoryAdapter);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(categoryAdapter);

    }

    private void addCategory() {
        String category = addEditCategoryBinding.editTxtCategory.getText().toString().trim();
        if (!category.isEmpty()) {
            int nextId = new Random().nextInt(Integer.MAX_VALUE);
            CategoryModelClass newCategory = new CategoryModelClass(nextId, category);

            if (categoryAdapter == null) {
                categoryAdapter = new CategoryAdapter(categoryList, this);
                recyclerView.setAdapter(categoryAdapter);
            }

            categoryList.add(newCategory);

            if (categoryAdapter != null) {
                categoryAdapter.notifyDataSetChanged();
            }

            addEditCategoryBinding.editTxtCategory.setText("");
            Toast.makeText(this, "Category Added Successfully", Toast.LENGTH_SHORT).show();

            SharedPreference.saveCategoryList(this, categoryList);
        } else {
            Toast.makeText(this, "Enter Category Name", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddCategory) {
            addCategory();
        }
    }
}
