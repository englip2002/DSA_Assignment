
/**
 * Author: KONG ZHI LIN
 * Description: Store food and beverage (menuItems)
 */
import java.io.Serializable;
import java.util.Iterator;

public class MenuItem implements Serializable{
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
        this.menuItemID = generateMenuItemID();
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

    private String generateMenuItemID() {

        if (menuItemCategory.charAt(0) == 'A') {
            return String.format("A%05d", menuItemCounter);
        } else if (menuItemCategory.charAt(0) == 'M') {
            return String.format("M%05d", menuItemCounter);
        } else if (menuItemCategory.charAt(0) == 'B') {
            return String.format("B%05d", menuItemCounter);
        } else {
            return String.format("D%05d", menuItemCounter);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (obj instanceof MenuItem) {
            if (menuItemID == ((MenuItem) obj).getMenuItemID()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Menu Item ID           : " + menuItemID + "\n" +
                "Menu Category          : " + menuItemCategory + "\n" +
                "Menu Item Name         : " + menuItemName + "\n" +
                "Menu Item Description  : " + menuItemDescription + "\n";
    }

}
