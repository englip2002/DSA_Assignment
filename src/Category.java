public class Category {
    private String categoryName;
    private ListInterface<MenuItem> menuCategory;
    private static int count=0;

    public Category(String categoryName){
        this.categoryName=categoryName;
        menuCategory=new LinkedList<MenuItem>();
    }

    public void addFood(MenuItem food){
        menuCategory.add(food);
        count++;
    }

    /**
     * @return String return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return ListInterface<MenuItem> return the menuCategory
     */
    public ListInterface<MenuItem> getMenuCategory() {
        return menuCategory;
    }

    /**
     * @param menuCategory the menuCategory to set
     */
    public void setMenuCategory(ListInterface<MenuItem> menuCategory) {
        this.menuCategory = menuCategory;
    }

}
