package hr.tvz.krznar.hardwareapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${jwtAdmin}")
    String jwtAdmin;

    @Value("${jwtUser}")
    String jwtUser;

    @Test
    void getAllHardware() throws Exception {
        this.mockMvc.perform(
                        get("/hardware")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .header("Authorization", "Bearer " + jwtAdmin)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("Asus TUF RTX 3080"))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getHardwareByCode() throws Exception {
        String code = "111111";
        String name = "Asus TUF RTX 3080";
        Double price = 1599.0;

        this.mockMvc.perform(
                        get("/hardware/" + code)
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .header("Authorization", "Bearer " + jwtAdmin)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.price").value(price));
    }

    @Test
    //@DirtiesContext
    void save_asUser() throws Exception {
        String name = "MEGA GRAPHICS CARD";
        String code = "987654";
        Double price = 456.7;
        Type type = Type.GPU;
        Integer stock = 55;

        HardwareCommand hardwareCommand = new HardwareCommand(name, code, price, type, stock);

        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("user").password("user").roles("USER"))
                                .header("Authorization", "Bearer " + jwtUser)
                                .with(csrf())
                                .contentType("application/json;charset=UTF-8")
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept("application/json;charset=UTF-8")
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @DirtiesContext
    void save_asAdmin() throws Exception {
        String name = "MEGA GRAPHICS CARD";
        String code = "987654";
        Double price = 456.7;
        Type type = Type.GPU;
        Integer stock = 55;

        HardwareCommand hardwareCommand = new HardwareCommand(name, code, price, type, stock);

        this.mockMvc.perform(
                post("/hardware")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .header("Authorization", "Bearer " + jwtAdmin)
                        .with(csrf())
                        .contentType("application/json;charset=UTF-8")
                        .content(objectMapper.writeValueAsString(hardwareCommand))
                        .accept("application/json;charset=UTF-8")
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.code").value(code));
    }
}