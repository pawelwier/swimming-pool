package pl.akademiakodu.swimmingpool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.swimmingpool.data.BookingBase;
import pl.akademiakodu.swimmingpool.model.Booking;
import pl.akademiakodu.swimmingpool.service.BookingService;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {

    public int prevSum = 0;

    @GetMapping("/book")
    public String showBookingForm() {

        return "addbooking";
    }

    @RequestMapping("/bookdate")
    public String ConfirmBooking(ModelMap modelMap,
                                 @RequestParam String year,
                                 @RequestParam String month,
                                 @RequestParam String day,
                                 @RequestParam String bookname,
                                 @RequestParam Integer booknum) {

        modelMap.put("year", year);
        modelMap.put("month", month);
        modelMap.put("day", day);
        modelMap.put("bookname", bookname);
        modelMap.put("booknum", booknum);
        return "bookingdate";
    }

    @RequestMapping("/bookdate/{dateid}/{bookname}/{booknum}")
    public String AddBooking(@PathVariable Integer dateid,
                             @PathVariable String bookname,
                             @PathVariable Integer booknum,
                             ModelMap modelMap) {

        List<Booking> bookings = new ArrayList<>();

        for (Booking b : BookingBase.list) {
            if (dateid.equals(b.getDate()) && booknum <= (BookingBase.getMaxUsers() - prevSum)) {
                prevSum += b.getPersonNum();
                //bookings.add(b);
            }
        }

        for (Booking b : BookingBase.list) {
            if (dateid.equals(b.getDate()))  bookings.add(b);
        }

        modelMap.put("previousbookings", bookings);

        if (booknum <= (BookingBase.getMaxUsers() - prevSum)) {

            prevSum += booknum;

            BookingService.bookDate(dateid, booknum, bookname);

            modelMap.put("bookingmade", "Rezerwacja na nazwisko " + bookname + ". Liczba miejsc: " + booknum);
            modelMap.put("available", "Pozostało wolne: " + (BookingBase.getMaxUsers() - prevSum));
        } else {
            modelMap.put("bookingmade", "Rezerwacja niemożliwa.");
            modelMap.put("available", "Liczba rezerwowanych miejsc (" +
                    booknum + ") przewyższa liczbę wolnych miejsc w tym dniu (" + (BookingBase.getMaxUsers() - prevSum) + ").");
        }
        prevSum = 0;
        return "bookdatedetails";
    }

}
