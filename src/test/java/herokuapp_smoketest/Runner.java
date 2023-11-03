package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                C01_CreateBooking.class,
                C02_GetBookingById.class,
                C03_UpdateBooking.class,
                C04_PartialUpdateBooking.class,
                C05_DeleteBooking.class,
                C06_GetBookingById_NegativeTest.class
        }
)

public class Runner {


}
