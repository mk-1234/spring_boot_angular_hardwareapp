package hr.tvz.krznar.hardwareapp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

public class HardwareCommand {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Code must not be empty")
    @Pattern(message = "Code must have 6 characters", regexp = "[\\w]{6}")
    private String code;

    @NotNull(message = "Price must be entered")
    @PositiveOrZero(message = "Price must be positive or zero")
    private Double price;

    @NotNull(message = "Type must not be null")
    private Type type;

    @NotNull(message = "Stock must be entered")
    @PositiveOrZero(message = "Stock must be positive or zero")
    private Integer stock;

    public HardwareCommand(String name, String code, Double price, Type type, Integer stock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public Integer getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "HardwareCommand{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", stock=" + stock +
                '}';
    }
}
