package hr.tvz.krznar.hardwareapp;

import hr.tvz.krznar.hardwareapp.review.Review;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hardware")
public class Hardware implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer stock;

    @OneToMany(mappedBy = "hardware", fetch = FetchType.EAGER)
    private List<Review> reviews;

    public Hardware() { }

    public Hardware(String name, String code, Double price, Type type, Integer stock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public Hardware(Long id, String name, String code, Double price, Type type, Integer stock) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer units) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", stock=" + stock +
                ", reviews=" + reviews +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return id.equals(hardware.id) && name.equals(hardware.name) && code.equals(hardware.code) && price.equals(hardware.price) && type == hardware.type && stock.equals(hardware.stock) && reviews.equals(hardware.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, price, type, stock, reviews);
    }
}
