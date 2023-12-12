package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import model.CategoryModelClass;
import model.TaskModelClass;

public class SharedPreference {

    private static final String CATEGORY_LIST_KEY = "category_list";
    private static final String TASK_LIST_KEY = "task_list";

    // Save and retrieve category list methods (similar to what you already have)
    public static void saveCategoryList(Context context, List<CategoryModelClass> categoryList) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(categoryList);
        editor.putString(CATEGORY_LIST_KEY, json);
        editor.apply();
    }

    public static List<CategoryModelClass> getCategoryList(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(CATEGORY_LIST_KEY, null);
        Type type = new TypeToken<List<CategoryModelClass>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Save and retrieve task list methods
    public static void saveTaskList(Context context, List<TaskModelClass> taskList) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskList);
        editor.putString(TASK_LIST_KEY, json);
        editor.apply();
    }

    public static List<TaskModelClass> getTaskList(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(TASK_LIST_KEY, null);
        Type type = new TypeToken<List<TaskModelClass>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
