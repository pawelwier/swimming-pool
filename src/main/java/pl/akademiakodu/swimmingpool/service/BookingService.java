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

    public static String showMonthNumber(String month) {


        String monthNum = (month.equals("styczeń") ? "01" :
                (month.equals("luty") ? "02" :
                        (month.equals("marzec") ? "03" :
                                (month.equals("kwiecień") ? "04" :
                                        (month.equals("maj") ? "05" :
                                                (month.equals("czerwiec") ? "06" :
                                                        (month.equals("lipiec") ? "07" :
                                                                (month.equals("sierpień") ? "08" :
                                                                        (month.equals("wrzesień") ? "09" :
                                                                                (month.equals("październik") ? "10" :
                                                                                        (month.equals("listopad") ? "11" : "12"
                                                                                        )))))))))));
     return monthNum;
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
