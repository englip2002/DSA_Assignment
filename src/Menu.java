public class Menu {
    private ListInterface<Category> menuCategory;

    public Menu(){
        menuCategory=new LinkedList<Category>();
    }

    /**
     * @return ListInterface<Category> return the menuCategory
     */
    public ListInterface<Category> getMenuCategory() {
        return menuCategory;
    }

    /**
     * @param menuCategory the menuCategory to set
     */
    public void setMenuCategory(ListInterface<Category> menuCategory) {
        this.menuCategory = menuCategory;
    }

}
