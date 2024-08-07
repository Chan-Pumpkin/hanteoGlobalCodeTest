package category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = new CategoryRepository();
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void testSearchCategoryByIdMan() {
        List<CategoryVO> searchId = categoryService.searchCategoryById(100);
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
        List<CategoryVO> searchId = categoryService.searchCategoryById(10);
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
        List<CategoryVO> searchName = categoryService.searchCategoryByName("여자");
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
        List<CategoryVO> searchName = categoryService.searchCategoryByName("방탄소년단");
        assertNotNull(searchName);
        assertEquals(4, searchName.size());

        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("방탄소년단")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("익명게시판")));
        assertTrue(searchName.stream().anyMatch(c -> c.getName().equals("뷔")));
    }

    @Test
    void testGetSubcategories() {
        List<CategoryVO> subcategories = categoryService.getSubcategories(10); // 엑소
        assertNotNull(subcategories);
        assertEquals(4, subcategories.size()); // 공지사항, 첸, 백현, 시우민
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("공지사항")));
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("첸")));
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("백현")));
        assertTrue(subcategories.stream().anyMatch(c -> c.getName().equals("시우민")));
    }

    @Test
    void testToJsonCategories() throws Exception {
        String json = categoryService.toJsonCategories();
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"남자\""));
        assertTrue(json.contains("\"name\":\"첸\""));
    }
}
