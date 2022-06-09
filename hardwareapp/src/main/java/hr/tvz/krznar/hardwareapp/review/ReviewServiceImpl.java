package hr.tvz.krznar.hardwareapp.review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewJpaRepository reviewJpaRepository;

    public ReviewServiceImpl(ReviewJpaRepository reviewJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewJpaRepository.findAll().stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByHardwareCode(String code) {
        return reviewJpaRepository.findAllByHardwareCode(code).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    /*@Override
    public List<ReviewDTO> findByText(String text) {
        return reviewJpaRepository.findByTextContainsIgnoreCase(text).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }*/

    private ReviewDTO mapReviewToDTO(final Review review) {
        return new ReviewDTO(review.getTitle(), review.getText(), review.getRating());
    }
}
