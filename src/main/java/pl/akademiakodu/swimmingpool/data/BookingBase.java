package pl.akademiakodu.swimmingpool.data;

import org.springframework.stereotype.Component;
import pl.akademiakodu.swimmingpool.model.Booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class BookingBase {

    public static Integer maxUsers = 12;

    public static Integer getMaxUsers() {
        return maxUsers;
    }

    public static List<Booking> list = new LinkedList<>();

        static {
        Booking booking1 = new Booking(18000406, 3, "Kowalski");
        Booking booking2 = new Booking(18000406, 2, "Janiak");
        Booking booking3 = new Booking(20000310, 2, "Nowak");
        Booking booking4 = new Booking(19000213, 6, "Małysz");

        list.add(booking1);
        list.add(booking2);
        list.add(booking3);
        list.add(booking4);

    }

    public static List<Booking> getList() {
        return list;
    }

    public static Booking findBookingByDate (Integer date) {

            Booking booking = new Booking();

            for (Booking b : BookingBase.list) {
                if (b.getDate().equals(date)) {
                    booking = b;
                }
            }

            return booking;
    }

}
