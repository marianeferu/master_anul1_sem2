package com.example.android.model;
public class ItemSuggestion {


    private String CategoryId;
    private String Id;
    private String Name;

    public ItemSuggestion(String categoryId, String id, String name) {
        CategoryId = categoryId;
        Id = id;
        Name = name;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

