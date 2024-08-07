package category;

import java.util.List;

public class CategoryMain {
    public static void main(String[] args) throws Exception {
        CategoryService manager = new CategoryService();

        // 카테고리 추가
        // 1뎁스
        manager.addCategory(100, "남자");
        manager.addCategory(200, "여자");
        // 2뎁스
        manager.addCategory(10, "엑소");
        manager.addCategory(20, "방탄소년단");
        manager.addCategory(30, "블랙핑크");
        // 3뎁스
        manager.addCategory(1, "공지사항");
        manager.addCategory(2, "첸");
        manager.addCategory(3, "백현");
        manager.addCategory(4, "시우민");
        manager.addCategory(5, "공지사항");
        manager.addCategory(6, "익명게시판");
        manager.addCategory(7, "뷔");
        manager.addCategory(8, "공지사항");
        manager.addCategory(9, "로제");

        // 관계 설정 1뎁스-2뎁스
        //남자
        manager.addRelation(100, 10);
        manager.addRelation(100, 20);
        //여자
        manager.addRelation(200, 30);

        // 관계 설정 2뎁스-3뎁스
        // 엑소
        manager.addRelation(10, 1);
        manager.addRelation(10, 2);
        manager.addRelation(10, 3);
        manager.addRelation(10, 4);
        // 방탄소년단
        manager.addRelation(20, 5);
        manager.addRelation(20, 6);
        manager.addRelation(20, 7);
        // 블랙핑크
        manager.addRelation(30, 8);
        manager.addRelation(30, 6);
        manager.addRelation(30, 9);


        // ID 검색
        List<CategoryVO> searchId = manager.searchCategoryById(100);
        System.out.println("아이디 검색");
        for (CategoryVO categoryVO : searchId) {
            System.out.println("- " + categoryVO.getName());
        }

        // 이름 검색
        List<CategoryVO> searchName = manager.searchCategoryByName("남자");
        System.out.println("이름 검색");
        for (CategoryVO categoryVO : searchName) {
            System.out.println("- " + categoryVO.getName());
        }

        // 카테고리 JSON 변환
        String json = manager.toJsonCategories();
        System.out.println("카테고리 JSON: " + json);
    }
}
