package category;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class CategoryMain {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        CategoryService categoryService = new CategoryService(categoryRepository);

        // ID 검색
        List<CategoryVO> searchId = categoryService.searchCategoryById(100);
        System.out.println("아이디 검색");
        for (CategoryVO categoryVO : searchId) {
            System.out.println("- " + categoryVO.getName());
        }

        // 이름 검색
        List<CategoryVO> searchName = categoryService.searchCategoryByName("여자");
        System.out.println("이름 검색");
        for (CategoryVO categoryVO : searchName) {
            System.out.println("- " + categoryVO.getName());
        }

        // 카테고리 JSON 변환
        try {
            String json = categoryService.toJsonCategories();
            System.out.println("카테고리 JSON: " + json);
        }catch (JsonProcessingException e){
            System.err.println("JSON 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
