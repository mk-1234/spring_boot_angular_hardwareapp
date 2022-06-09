package hr.tvz.krznar.hardwareapp;

import javax.validation.constraints.NotBlank;

public class HardwareCommandLab {

    @NotBlank(message = "Name must not be empty")
    private String name;

    public String getName() {
        return name;
    }
}
