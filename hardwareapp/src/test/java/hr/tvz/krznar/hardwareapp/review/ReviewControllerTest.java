package hr.tvz.krznar.hardwareapp.review;

import hr.tvz.krznar.hardwareapp.security.command.LoginCommand;
import hr.tvz.krznar.hardwareapp.security.domain.Authority;
import hr.tvz.krznar.hardwareapp.security.domain.User;
import hr.tvz.krznar.hardwareapp.security.dto.LoginDTO;
import hr.tvz.krznar.hardwareapp.security.repository.UserRepository;
import hr.tvz.krznar.hardwareapp.security.service.AuthenticationService;
import hr.tvz.krznar.hardwareapp.security.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${jwtAdmin}")
    String jwt;

    @Test
    void getAllReviews() throws Exception {
        /*String username = "admin";
        String password = "admin";

        Set<Authority> tempAuthorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName("ROLE_ADMIN");
        tempAuthorities.add(authority);
        User user = new User(2L, "admin", "$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG", tempAuthorities);
        System.out.println("-------------------");
        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getAuthorities());

        //String jwt = jwtService.createJwt(user);

        Long accessTokenValiditySeconds = 604800L;
        String secretKey = "ZjMzYzM4NzYzMTg0OWE4ZTY5M2ZlYjZhMDIyMjA4ZGQzYTNlZmQ4ZmU0MzU3ZThhNTc1Yzg1NTZiYzEyY2VlNzM5ZGM2MjdkMGY5NWMyY2Y5NmRiMDg3ZGFkOTM3NWEwODA5ODJjZWQ3NGI0MDdiMzZmYzlhOGU0NTEyODFmYzg=";

        Instant expiration = Instant.now().plusSeconds(accessTokenValiditySeconds);
        String authorities = user.getAuthorities()
                .stream()
                .map(Authority::getName)
                .collect(Collectors.joining(","));

        System.out.println("AUTHORITIES:");
        System.out.println(authorities);
        System.out.println("-------------------------");

        String jwt = Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setSubject(user.getUsername())
                .setExpiration(new Date(expiration.toEpochMilli()))
                .setIssuedAt(new Date())
                .claim("authorities", authorities)
                .compact();



        //String token = "$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG";
        System.out.println("\n\n\n ----------------\n\n TOKEN:");
        System.out.println(jwt);
        System.out.println("\n ---------------------- \n\n\n");
        this.mockMvc.perform(
                get("/review")
                        .header("Authorization", "Bearer " + jwt)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$[0].title").value("testtitle1"));
                .andExpect(jsonPath("$", hasSize(10)));*/


        //String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDc2MTM4MSwiaWF0IjoxNjU0MTU2NTgyLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.jiazy2ht4aT1YbI76OBtb8rPC1QIlQRcslRjaXN2trIrx_w4nUWhoiLrKHp3061hh7Ie6lBhEmEwLPOQbjnyHw";


        this.mockMvc.perform(
                        get("/review")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .header("Authorization", "Bearer " + jwt)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].title").value("testtitle1"))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void getReviewByHardwareCode() throws Exception {
        //String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDc2MTM4MSwiaWF0IjoxNjU0MTU2NTgyLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.jiazy2ht4aT1YbI76OBtb8rPC1QIlQRcslRjaXN2trIrx_w4nUWhoiLrKHp3061hh7Ie6lBhEmEwLPOQbjnyHw";
        this.mockMvc.perform(
                        get("/review?hardwareCode=111111")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .header("Authorization", "Bearer " + jwt)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)));
    }
}