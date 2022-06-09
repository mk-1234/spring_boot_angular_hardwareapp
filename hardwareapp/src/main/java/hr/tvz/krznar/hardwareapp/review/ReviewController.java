package hr.tvz.krznar.hardwareapp.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping(params = "hardwareCode")
    public List<ReviewDTO> getReviewByHardwareCode(@RequestParam final String hardwareCode) {
        return reviewService.findAllByHardwareCode(hardwareCode);
    }

    /*@GetMapping("/vj/{text}")
    public List<ReviewDTO> getReviewByText(@PathVariable final String text) {
        return reviewService.findByText(text);
    }*/
}
