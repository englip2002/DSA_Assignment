import java.io.Serializable;
import java.util.Iterator;

/**
 * Author: KONG ZHI LIN
 * This is the menu pacakge class
 * 1 menu package has 1 or many menu item(s)
 **/

public class Package implements Serializable {

    // Class attribute
    private String packageID;
    private String packageName;
    private double packagePrice;
    private int menuItemLimit;
    private String packageDescription;
    private static int packageCounter = 1;
    private SetInterface<MenuItem> packageMenuItems; // To store the menuItem in the array set of menuPackage

    // Constructor
    public Package() {
        this.packageID = "";
        this.packageName = "";
        this.packagePrice = 0;
        this.packageDescription = "";
        this.packageMenuItems = new ArraySet<>();
    }

    public Package(String packageName, double packagePrice, int menuItemLimit, String packageDescription) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.menuItemLimit = menuItemLimit;
        this.packageID = generatePackageID();
        this.packageDescription = packageDescription;
        this.packageMenuItems = new ArraySet<>();
        packageCounter++;
    }

    public Package(String packageName, double packagePrice, int menuItemLimit, String packageDescription, SetInterface<MenuItem> packageMenuItems) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.menuItemLimit = menuItemLimit;
        this.packageID = generatePackageID();
        this.packageDescription = packageDescription;
        this.packageMenuItems = packageMenuItems;
        packageCounter++;
    }

    public Package(String packageID, String packageName, double packagePrice, int menuItemLimit,
            SetInterface<MenuItem> packageMenuItems,
            String packageDescription) {
        this.packageID = packageID;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.menuItemLimit = menuItemLimit;
        this.packageDescription = packageDescription;
        this.packageMenuItems = packageMenuItems;
        packageCounter++;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public int getMenuItemLimit() {
        return menuItemLimit;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public SetInterface<MenuItem> getAllPackageMenuItems() {
        return packageMenuItems;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public void setMenuItemLimit(int menuItemLimit) {
        this.menuItemLimit = menuItemLimit;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    // To perform the package has many menuItems we will include the Set ADT in this
    // entity class
    public void addMenuItemToPackage(MenuItem menuItem) {
        packageMenuItems.add(menuItem);
    }

    public void removeMenuItemFromPackage(MenuItem menuItem) {
        packageMenuItems.remove(menuItem);
    }

    private String generatePackageID() {

        return String.format("MP%05d", packageCounter);

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

        if (obj instanceof Package) {
            if (packageID == ((Package) obj).getPackageID()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Package ID           : " + packageID + "\n" +
                "Package Name         : " + packageName + "\n" +
                "Package Price        : RM " + packagePrice + "\n" +
                "Package Description  : " + packageDescription + "\n" +
                "Limit of menu items  : " + menuItemLimit + "\n" +
                "Menu item(s) inside the package:" + "\n" +
                printMenuItemInPackage();
    }

    public String printMenuItemInPackage() {
        String str = "";
        str += String.format("%-3s %-10s %-20s %-30s\n", "No",
                "Dish ID", "Dish Name", "Dish Description");
        for (int i = 0; i < packageMenuItems.getNumberOfEntries(); i++) {
            MenuItem temp = packageMenuItems.getElementAtPos(i);
            str += String.format("%-3s %-10s %-20s %-30s\n", (i + 1), temp.getMenuItemID(), temp.getMenuItemName(),
                    temp.getMenuItemDescription());
        }
        return str;
    }

}
