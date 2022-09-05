import java.io.Serializable;

/**
 * Author: KONG ZHI LIN
 * This is the menu pacakge class
 * 1 menu package has 1 or many menu item(s)
 **/

import java.io.Serializable;

public class Package implements Serializable {

    // Class attribute
    private String packageID;
    private String packageName;
    private double packagePrice;
    private String packageDescription;
    private static int packageCounter = 1;
    private SetInterface<MenuItem> menuPackage; // To store the menuItem in the array set of menuPackage

    // Constructor
    public Package() {
        this.packageID = "";
        this.packageName = "";
        this.packagePrice = 0;
        this.packageDescription = "";
        this.menuPackage = new ArraySet<>();
    }

    public Package(String packageName, double packagePrice, String packageDescription) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packageID = generatePackageID();
        this.packageDescription = packageDescription;
        packageCounter++;
    }

    public Package(String packageName, double packagePrice, SetInterface<MenuItem> menuPackage,
            String packageDescription) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packageID = generatePackageID();
        this.packageDescription = packageDescription;
        this.menuPackage = menuPackage;
        packageCounter++;
    }

    public Package(String packageID, String packageName, double packagePrice, SetInterface<MenuItem> menuPackage,
            String packageDescription) {
        this.packageID = packageID;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packageDescription = packageDescription;
        this.menuPackage = menuPackage;
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

    public SetInterface<MenuItem> getAllMenuPackage() {
        return menuPackage;
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

    // To perform the package has many menuItems we will include the Set ADT in this
    // entity class
    public void addMenuItemToPackage(MenuItem menuItem) {
        menuPackage.add(menuItem);
    }

    private String generatePackageID() {

        return String.format("MP%5d", packageCounter);

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

    // Havent complete
    public String toString() {
        return "Package ID: " + packageID + "\n" +
                "Package Name: " + packageName + "\n";
    }

}
