package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.precioso.group_7_final_project.databinding.CategoryItemLayoutBinding;
import java.util.List;
import model.CategoryModelClass;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModelClass> categoryList;
    private Context context;

    public CategoryAdapter(List<CategoryModelClass> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoryItemLayoutBinding binding = CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryModelClass category = categoryList.get(position);
        holder.binding.categoryTextView.setText(category.getCategory());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CategoryItemLayoutBinding binding;

        public ViewHolder(CategoryItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
