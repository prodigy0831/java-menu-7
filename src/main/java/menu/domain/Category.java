package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.Repository.MenuRepository;

public class Category {
    private final String name;
    private final List<Menu> menus = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void addMenusToCategory(List<Menu> menus) {
        this.menus.addAll(menus);
    }

    public Menu pickRandomMenuInCategory() {
        String menu = Randoms.shuffle(convertMenuToName()).get(0);
        return MenuRepository.findMenuByName(menu);
    }

    private List<String> convertMenuToName() {
        return menus.stream().map(Menu::getName).collect(Collectors.toList());
    }
}
