package pl.akademiakodu.swimmingpool.service;

import pl.akademiakodu.swimmingpool.data.BookingBase;
import pl.akademiakodu.swimmingpool.model.Booking;

import java.util.List;

public class BookingService {

    public static List<Booking> bookDate(String date, Integer num, String name) {

        Booking booking = new Booking(date, num, name);

        BookingBase.list.add(booking);

        return BookingBase.list;

    }


    public static String showTime(String hour) {


        String hourNum = (hour.equals("17:00") ? "1700" :
                (hour.equals("18:00") ? "1800" :
                        (hour.equals("19:00") ? "1900" :
                                (hour.equals("20:00") ? "2000" : "2100"
                                ))));
        return hourNum;
    }

}
