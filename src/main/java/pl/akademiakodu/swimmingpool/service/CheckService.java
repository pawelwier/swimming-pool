package pl.akademiakodu.swimmingpool.service;

import pl.akademiakodu.swimmingpool.data.BookingBase;
import pl.akademiakodu.swimmingpool.model.Booking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CheckService {

    public static void deleteBooking(String bookname, Integer booknum, String dateid) {
        for (Iterator<Booking> iterator = BookingBase.list.iterator(); iterator.hasNext(); ) {
            Booking b = iterator.next();
            if (b.getDate().equals(dateid) && b.getPersonNum().equals(booknum) && b.getName().equals(bookname)) {
                iterator.remove();
            }
        }
    }

    public static List<Booking> CheckForPreviousBookings(String dateid) {
        List<Booking> bookings = new ArrayList<>();

        for (Booking b : BookingBase.list) {
            if (dateid.equals(b.getDate()))  bookings.add(b);
        }
        return bookings;
    }

    public static int countTrack1(int people) {
        int track1 = 0;
        if (people % 3 == 0) track1 = people/3;
        else if (people % 3 == 1 || people % 3 == 2) track1 = people/3 + 1;

        return track1;
    }

    public static int countTrack2(int people) {
        int track2 = 0;
        if (people % 3 == 0 || people % 3 == 1) track2 = people/3;
        else if (people % 3 == 2) track2 = people/3 + 1;

        return track2;
    }

    public static int countTrack3(int people) {
        int track3 = people/3;

        return track3;
    }

    public static Integer updatePrevSum(String dateid, Integer prevSum) {
        for (Booking b : BookingBase.list) {
            if (dateid.equals(b.getDate())) {
                prevSum += b.getPersonNum();
            }
        } return prevSum;
    }

    public String getDateNoSlash(String word) {
        char[] array = word.toCharArray();

        String result = "";
        for (int i = 0; i < array.length ; i++) {
            if(array[i] != '/') {
                result += array[i];
            }
        } return result;
    }

}
