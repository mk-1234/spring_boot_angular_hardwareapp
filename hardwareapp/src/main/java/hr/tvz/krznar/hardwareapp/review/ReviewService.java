package hr.tvz.krznar.hardwareapp.review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);

    //List<ReviewDTO> findByText(String text);

}
