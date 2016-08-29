package app;

import domain.BookingService;
import dto.BookingCalendar;
import dto.BookingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private BookingCalendar bookingCalendar;

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/booking_calendar")
    public BookingCalendar bookingCalendar() {
        return bookingCalendar;
    }

    @RequestMapping(value = "/booking_calendar", method = RequestMethod.PUT,
            headers = {"Content-Type=application/json", "Accept=application/json"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public
    @ResponseBody
    BookingCalendar bookingCalendar(@RequestBody BookingRequest bookingRequest) {
        LOG.info("bookingRequest = \n" + bookingRequest.toString());

        bookingService.updateBookingCalendar(bookingRequest);

        LOG.info("bookingCalendar = \n" + bookingCalendar.toString());

        return bookingCalendar;
    }

}
