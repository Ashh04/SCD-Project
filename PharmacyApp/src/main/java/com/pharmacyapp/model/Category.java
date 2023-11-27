package com.pharmacyapp.model;

/**
 * Category entity representing a product category in the system.
 */
public class Category {
    private int id; // Unique identifier for the category
    private String name; // Name of the category
    private String description; // Description of the category
    private Integer parentId; // Parent category ID, nullable for top-level categories

    /**
     * Constructor for creating a new Category instance.
     *
     * @param id          The unique identifier of the category.
     * @param name        The name of the category.
     * @param description The description of the category.
     * @param parentId    The parent category ID, can be null for top-level categories.
     */
    public Category(int id, String name, String description, Integer parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    // Business logic methods (if any)
    // For example, if categories have specific behaviors or validations,
    // they can be implemented here.

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
