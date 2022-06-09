package hr.tvz.krznar.hardwareapp;

public class HardwareDTO {

    private final String name;
    private final Double price;
    private final String code;
    private final Integer stock;

    public HardwareDTO(String name, Double price, String code, Integer stock) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public Integer getStock() {
        return stock;
    }
}
