package pl.akademiakodu.swimmingpool.service;

import pl.akademiakodu.swimmingpool.data.BookingBase;
import pl.akademiakodu.swimmingpool.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public static List<Booking> searchByName(String name) {

        List<Booking> list = new ArrayList<>();

        for (Booking b : BookingBase.list) {
            if (b.getName().equals(name)) {
                list.add(b);
            }
        }
        return list;
    }



}
