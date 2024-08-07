package category;

import java.util.*;

public class CategoryRepository {
    private final Map<Integer, CategoryVO> categories;
    private final Map<Integer, List<Integer>> parentChildMap;

    public CategoryRepository() {
        categories = new HashMap<>();
        parentChildMap = new HashMap<>();
        initializeDummyData();
    }

    /**
     * 더미 데이터 초기화
     * */
    private void initializeDummyData() {
        // 카테고리 추가
        addCategory(100, "남자");
        addCategory(200, "여자");
        addCategory(10, "엑소");
        addCategory(20, "방탄소년단");
        addCategory(30, "블랙핑크");
        addCategory(1, "공지사항");
        addCategory(2, "첸");
        addCategory(3, "백현");
        addCategory(4, "시우민");
        addCategory(5, "공지사항");
        addCategory(6, "익명게시판");
        addCategory(7, "뷔");
        addCategory(8, "공지사항");
        addCategory(9, "로제");

        // 관계 설정
        addRelation(100, 10);
        addRelation(100, 20);
        addRelation(200, 30);
        addRelation(10, 1);
        addRelation(10, 2);
        addRelation(10, 3);
        addRelation(10, 4);
        addRelation(20, 5);
        addRelation(20, 6);
        addRelation(20, 7);
        addRelation(30, 8);
        addRelation(30, 6);
        addRelation(30, 9);
    }

    /**
     * 카테고리 추가
     * @param id 카테고리 ID
     * @param name 카테고리 이름
     */
    public void addCategory(int id, String name) {
        categories.put(id, new CategoryVO(id, name));
    }

    /**
     * 카테고리 관계 추가
     * @param parentIdx 부모 카테고리 ID
     * @param childIdx 자식 카테고리 ID
     */
    public void addRelation(int parentIdx, int childIdx) {
        parentChildMap.putIfAbsent(parentIdx, new ArrayList<>());
        parentChildMap.get(parentIdx).add(childIdx);
    }

    /**
     * 모든 카테고리를 리턴
     * @return 카테고리 맵
     * */
    public Map<Integer, CategoryVO> getCategories() {
        return categories;
    }

    /**
     * 모든 부모-자식 관계를 리턴
     * @return 부모-자식 관계 맵
     */
    public Map<Integer, List<Integer>> getParentChildMap() {
        return parentChildMap;
    }
}