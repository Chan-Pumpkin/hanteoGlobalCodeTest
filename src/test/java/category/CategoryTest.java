package category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private CategoryService manager;

    @BeforeEach
    void setUp() {
        manager = new CategoryService();

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
        // 남자
        manager.addRelation(100, 10);
        manager.addRelation(100, 20);
        // 여자
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
    }

    @Test
    void testSearchCategoryByIdMan() {
        List<CategoryVO> searchId = manager.searchCategoryById(100);
        assertNotNull(searchId);
        assertEquals(10, searchId.size());

        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("남자")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("엑소")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("첸")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("백현")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("시우민")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("방탄소년단")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("익명게시판")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("뷔")));
    }

    @Test
    void testSearchCategoryByIDExo() {
        List<CategoryVO> searchId = manager.searchCategoryById(10);
        assertNotNull(searchId);
        assertEquals(5, searchId.size());

        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("엑소")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("첸")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("백현")));
        assertTrue(searchId.stream().anyMatch(c -> c.getName().equals("시우민")));
    }

    @Test
    void testSearchCategoryByNameGirl() {
        List<CategoryVO> searchName = manager.searchCategoryByName("여자");
        assertNotNull(searchName);
        assertEquals(5, searchName.size());

        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("여자")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("블랙핑크")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("익명게시판")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("로제")));
    }

    @Test
    void testSearchCategoryByNameBts() {
        List<CategoryVO> searchName = manager.searchCategoryByName("방탄소년단");
        assertNotNull(searchName);
        assertEquals(4, searchName.size());

        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("방탄소년단")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("익명게시판")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("뷔")));
    }

    @Test
    void testGetSubcategories() {
        List<CategoryVO> subcategories = manager.getSubcategories(10); // 엑소
        assertNotNull(subcategories);
        assertEquals(4, subcategories.size()); // 공지사항, 첸, 백현, 시우민
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("첸")));
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("백현")));
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("시우민")));
    }

    @Test
    void testToJsonCategories() throws Exception {
        String json = manager.toJsonCategories();
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"남자\""));
        assertTrue(json.contains("\"name\":\"첸\""));
    }
}
