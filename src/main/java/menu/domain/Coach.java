package menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Coach {

    private final String name;
    private final List<Menu> menuNotToEat = new ArrayList<>();
    private final List<Menu> menuAlreadyEaten = new ArrayList<>();

    public Coach(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMenuNotToEat(List<Menu> menuNotToEat) {
        this.menuNotToEat.addAll(menuNotToEat);
    }

    public boolean isAvailableMenu(Menu menu) {
        return !menuNotToEat.contains(menu) && !menuAlreadyEaten.contains(menu);
    }

    public void addMenuAlreadyEaten(Menu menu) {
        menuAlreadyEaten.add(menu);
    }

    public List<Menu> getMenuAlreadyEaten() {
        return menuAlreadyEaten;
    }
}
