package hello;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BookingsService {

    public List<Booking> allBookings() {
        RestTemplate restTemplate = new RestTemplate();
        Booking[] response = restTemplate.getForObject("http://localhost:8190/projects/1/bookings", Booking[].class);
        return Arrays.asList(response);
    }
}
