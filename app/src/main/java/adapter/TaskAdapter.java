package adapter;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.precioso.group_7_final_project.databinding.TaskItemLayoutBinding;

import java.util.List;

import model.TaskModelClass;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskModelClass> taskList;
    private Context context;

    public TaskAdapter(List<TaskModelClass> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TaskItemLayoutBinding binding = TaskItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskModelClass task = taskList.get(position);
        holder.binding.taskNameTextView.setText(task.getTaskName());
        holder.binding.categoryTextView.setText(task.getCategory());
        holder.binding.durationTextView.setText(task.getDurationMinutes() + " minutes");
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TaskItemLayoutBinding binding;

        public ViewHolder(TaskItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(TaskModelClass task) {
            binding.taskNameTextView.setText(task.getTaskName());
            binding.categoryTextView.setText(task.getCategory());
            binding.durationTextView.setText(task.getDurationMinutes() + " minutes");
        }

    }
}
