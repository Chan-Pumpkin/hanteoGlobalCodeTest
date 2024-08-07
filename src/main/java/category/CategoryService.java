package category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * 식별자로 해당 카테고리와 하위 카테고리 검색
     * @param id 카테고리 ID
     * @return 해당 카테고리와 하위 카테고리 리스트
     */
    public List<CategoryVO> searchCategoryById(int id){
        List<CategoryVO> result = new ArrayList<>();
        if (repository.getCategories().containsKey(id)) {
            result.add(repository.getCategories().get(id));
            result.addAll(getSubcategories(id));
        }
        return result;
    }

    /**
     * 카테고리명으로 해당 카테고리와 하위 카테고리 검색
     * @param name 카테고리 이름
     * @return 해당 카테고리와 하위 카테고리 리스트
     */
    public List<CategoryVO> searchCategoryByName(String name) {
        List<CategoryVO> result = new ArrayList<>();
        for (CategoryVO categoryVO : repository.getCategories().values()) {
            if (categoryVO.getName().equals(name)) {
                result.add(repository.getCategories().get(categoryVO.getId()));
                result.addAll(getSubcategories(categoryVO.getId()));
            }
        }
        return result;
    }

    /**
     * 부모 식별자로 하위 카테고리 찾기 (재귀법)
     * @param parentId 부모 카테고리 ID
     * @return 하위 카테고리 리스트
     */
    public List<CategoryVO> getSubcategories(int parentId) {
        List<CategoryVO> result = new ArrayList<>();
        if (repository.getParentChildMap().containsKey(parentId)) {
            for (int childId : repository.getParentChildMap().get(parentId)) {
                result.add(repository.getCategories().get(childId));
                result.addAll(getSubcategories(childId));
            }
        }
        return result;
    }

    /**
     * 카테고리 가공
     * @param id 카테고리 ID
     * @return 가공된 카테고리 Map
     */
    private Map<String, Object> toJsonCategoriesMap(int id) {
        Map<String, Object> result = new HashMap<>();
        CategoryVO categoryVO = repository.getCategories().get(id);
        result.put("id", categoryVO.getId());
        result.put("name", categoryVO.getName());
        List<Object> subcategories = new ArrayList<>();
        if (repository.getParentChildMap().containsKey(id)) {
            for (int childId : repository.getParentChildMap().get(id)) {
                subcategories.add(toJsonCategoriesMap(childId));
            }
        }
        result.put("subcategories", subcategories);
        return result;
    }

    /**
     * 카테고리 Json 변환
     * @return Json 문자열
     * @throws JsonProcessingException 예외
     */
    public String toJsonCategories() throws JsonProcessingException {
        Map<Integer, Object> jsonMap = new TreeMap<>();
        for (Integer id : repository.getCategories().keySet()) {
            jsonMap.put(id, toJsonCategoriesMap(id));
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonMap);
    }

}