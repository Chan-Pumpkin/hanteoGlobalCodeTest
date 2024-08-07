package category;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class CategoryService {
    private Map<Integer, CategoryVO> categories;
    private Map<Integer, List<Integer>> parentChildMap;

    public CategoryService() {
        categories = new HashMap<>();
        parentChildMap = new HashMap<>();
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
     * 식별자로 해당 카테고리와 하위 카테고리 검색
     * @param id 카테고리 ID
     * @return 해당 카테고리와 하위 카테고리 리스트
     */
    public List<CategoryVO> searchCategoryById(int id){
        List<CategoryVO> result = new ArrayList<>();
        if (categories.containsKey(id)) {
            result.add(categories.get(id));
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
        for (CategoryVO categoryVO : categories.values()) {
            if (categoryVO.getName().equals(name)) {
                result.add(categories.get(categoryVO.getId()));
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
        if (parentChildMap.containsKey(parentId)) {
            for (int childId : parentChildMap.get(parentId)) {
                result.add(categories.get(childId));
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
        CategoryVO categoryVO = categories.get(id);
        result.put("id", categoryVO.getId());
        result.put("name", categoryVO.getName());
        List<Object> subcategories = new ArrayList<>();
        if (parentChildMap.containsKey(id)) {
            for (int childId : parentChildMap.get(id)) {
                subcategories.add(toJsonCategoriesMap(childId));
            }
        }
        result.put("subcategories", subcategories);
        return result;
    }

    /**
     * 카테고리 Json 변환
     * @return Json 문자열
     * @throws Exception 예외
     */
    public String toJsonCategories() throws Exception {
        Map<Integer, Object> jsonMap = new TreeMap<>();
        for (Integer id : categories.keySet()) {
            jsonMap.put(id, toJsonCategoriesMap(id));
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonMap);
    }

}