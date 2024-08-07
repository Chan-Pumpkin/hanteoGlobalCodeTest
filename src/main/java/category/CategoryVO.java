package category;

public class CategoryVO {
    private final int id;
    private final String name;

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
