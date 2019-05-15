package com.vapecenter.demo.models;

public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private int categorySubList;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String categoryDescription, int categorySubList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categorySubList = categorySubList;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

<<<<<<< HEAD
    public int getCategorySubList() {
        return categorySubList;
    }

    public void setCategorySubList(int categorySubList) {
        this.categorySubList = categorySubList;
    }

=======
>>>>>>> master
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}
