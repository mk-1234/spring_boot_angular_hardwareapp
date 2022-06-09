package hr.tvz.krznar.hardwareapp.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {

    List<Review> findAll();

    List<Review> findAllByHardwareCode(String code);

    //List<Review> findByTextContainsIgnoreCase(@Param("text") String text);

}
