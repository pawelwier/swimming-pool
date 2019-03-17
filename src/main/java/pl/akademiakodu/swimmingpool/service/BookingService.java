package pl.akademiakodu.swimmingpool.service;

import pl.akademiakodu.swimmingpool.controller.CheckController;
import pl.akademiakodu.swimmingpool.data.BookingBase;
import pl.akademiakodu.swimmingpool.model.Booking;

import java.util.List;

public class BookingService {

    int prevSum = 0;

    public static List<Booking> bookDate(Integer date, Integer num, String name) {

        Booking booking = new Booking(date, num, name);

        BookingBase.list.add(booking);

        return BookingBase.list;

    }

//    public Integer showPrevSum(Integer dateId) {
//
//
//        for (Booking b : BookingBase.list) {
//            if (dateId.equals(b.getDate())) {
//                CheckController.prevSum += b.getPersonNum();
//            }
//        }
//    } return prevSum;
//}

}
