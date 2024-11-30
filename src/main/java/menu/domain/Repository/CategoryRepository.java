package menu.domain.Repository;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.domain.Category;

public class CategoryRepository {

    private CategoryRepository() {
    }

    private static final List<Category> categories = new ArrayList<>();
    private static final List<Category> categoriesAlreadyEaten = new ArrayList<>();


    public static void add(Category category) {
        categories.add(category);
    }

    public static Category findCategoryByName(String name) {
        return categories.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리"));
    }

    public static Category pickRandomCategory() {
        return categories.get(getRandomIndex());
    }

    private static int getRandomIndex() {
        return Randoms.pickNumberInRange(1, 5) - 1;
    }


    public static boolean isAvailableCategory(Category category) {
        return countCategoryOf(category) < 2;
    }

    private static int countCategoryOf(Category category) {
        return (int) categoriesAlreadyEaten.stream()
                .filter(element -> element == category)
                .count();
    }

    public static void updateCategoriesAlreadyEaten(Category category) {
        categoriesAlreadyEaten.add(category);
    }

    public static List<Category> getCategoriesAlreadyEaten() {
        return Collections.unmodifiableList(categoriesAlreadyEaten);
    }
}