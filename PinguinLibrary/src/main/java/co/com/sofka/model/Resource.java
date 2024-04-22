package co.com.sofka.model;

import co.com.sofka.enums.ResourceType;

public abstract class Resource {
    private Integer id;
    private ResourceType type;
    private String title;
    private int quantity;
    private int quantityLoaned;
    private Author author;

    protected Resource() {
    }

    protected Resource(ResourceType type, String title, int quantity, int quantityLoaned,
                       Author author) {
        this.type = type;
        this.title = title;
        this.quantity = quantity;
        this.quantityLoaned = quantityLoaned;
        this.author = author;
    }

    protected Resource(int id, ResourceType type, String title, int quantity, int quantityLoaned,
                       Author author) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.quantity = quantity;
        this.quantityLoaned = quantityLoaned;
        this.author = author;
    }

    public int getAlailableQuantity() {
        return quantity - quantityLoaned;
    }

    @Override
    public String toString() {
        return " id=" + id + ", type=" + type + ", title='" + title + "', quantity=" + quantity + ", quantityLoaned=" + quantityLoaned + ", author=" + author.getName() + " ";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityLoaned() {
        return quantityLoaned;
    }

    public void setQuantityLoaned(int quantityLoaned) {
        this.quantityLoaned = quantityLoaned;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
