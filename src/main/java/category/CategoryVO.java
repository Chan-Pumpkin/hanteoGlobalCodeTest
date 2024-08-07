package category;

public class CategoryVO {
    private int id;
    private String name;

    public CategoryVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
