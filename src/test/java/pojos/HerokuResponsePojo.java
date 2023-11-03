package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HerokuResponsePojo {

    private HerokuBookingPojo booking;

    public HerokuResponsePojo(HerokuBookingPojo booking) {
        this.booking = booking;
    }

    public HerokuResponsePojo() {
    }

    public HerokuBookingPojo getBooking() {
        return booking;
    }

    public void setBooking(HerokuBookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerokuPojo{" +
                "booking=" + booking +
                '}';
    }
}
