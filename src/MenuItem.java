
/**
 * Author: KONG ZHI LIN
 * Description: Store food and beverage (menuItems)
 */

public class MenuItem {
    // Class Attributes
    private String menuItemCategory; // Appertizer, Main course, Beverage, Dessert
    private String menuItemName;
    private String menuItemID;
    private String menuItemDescription;
    private static int menuItemCounter = 1; // Number to improve the index

    // Constructor
    public MenuItem() {
        this.menuItemCategory = "";
        this.menuItemName = "";
        this.menuItemDescription = "";
        this.menuItemCounter = 1;
    }

    public MenuItem(String menuItemCategory, String menuItemName, String menuItemDescription) {
        this.menuItemCategory = menuItemCategory;
        this.menuItemName = menuItemName;
        this.menuItemDescription = menuItemDescription;
        this.menuItemID = generateDishID();
        menuItemCounter++;
    }

    // Accessors & Mutators
    public String getMenuItemCategory() {
        return menuItemCategory;
    }

    public String getMenuItemID() {
        return menuItemID;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public static int getMenuItemCounter() {
        return menuItemCounter;
    }

    public void setMenuItemCategory(String menuItemCategory) {
        this.menuItemCategory = menuItemCategory;
    }

    public void setMenuItemID(String menuItemID) {
        this.menuItemID = menuItemID;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    private String generateDishID() {

        if (menuItemCategory.charAt(0) == 'A') {
            return String.format("A%5d", menuItemCounter);
        } else if (menuItemCategory.charAt(0) == 'M') {
            return String.format("M%5d", menuItemCounter);
        } else if (menuItemCategory.charAt(0) == 'B') {
            return String.format("B%5d", menuItemCounter);
        } else {
            return String.format("D%5d", menuItemCounter);
        }

    }

}
