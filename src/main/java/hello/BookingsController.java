package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Controller
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @GetMapping("/bookings")
    public String bookings(Model model) {
        List<Booking> bookings = bookingsService.allBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("booking", new Booking());

        RestTemplate restTemplate = new RestTemplate();
        Project[] response = restTemplate.getForObject("http://localhost:8190/projects", Project[].class);
        model.addAttribute("projects", response);

        return "booking";
    }

    @PostMapping("/bookings")
    public String createBooking(@ModelAttribute Booking booking) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8190/projects/" + booking.getProjectId() + "/bookings";
        restTemplate.postForLocation(url, booking);
        return "redirect:/bookings";
    }

}
