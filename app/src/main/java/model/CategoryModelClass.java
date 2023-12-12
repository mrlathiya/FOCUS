package model;

public class CategoryModelClass {
    private final int id;
    private final String category;

    public CategoryModelClass(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

}
