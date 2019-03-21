package pl.akademiakodu.swimmingpool.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    public String dateIndex = "";

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/book")
    public String showBookingForm() {

        return "addbooking";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/bookdate")
    public String confirmBooking(ModelMap modelMap,
                                 @RequestParam String month,
                                 @RequestParam String day,
                                 @RequestParam String time,
                                 @RequestParam String bookname,
                                 @RequestParam Integer booknum) {

        dateIndex = BookingService.showTime(time) + BookingService.showMonthNumber(month) + day;

        if (bookname!=null && !bookname.equals("") && booknum!=null) {modelMap.put("bookname", bookname);
        modelMap.put("time", BookingService.showTime(time));
        modelMap.put("month", BookingService.showMonthNumber(month));
        modelMap.put("day", day);
        modelMap.put("booknum", booknum);
        return "redirect:/bookdate/"+dateIndex+"/"+bookname+"/"+booknum;}
        else {modelMap.put("noanswer","");
            return "addbooking";}
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/bookdate/{dateid}/{bookname}/{booknum}")
    public String addBooking(@PathVariable Integer dateid,
                             @PathVariable String bookname,
                             @PathVariable Integer booknum,
                             ModelMap modelMap) {

        for (Booking b : BookingBase.list) {
            if (dateid.equals(b.getDate()) && booknum <= (BookingBase.getMaxUsers() - prevSum)) {
                prevSum += b.getPersonNum();
            }
        }

        if (booknum <= (BookingBase.getMaxUsers() - prevSum)) {

            prevSum += booknum;

            BookingService.bookDate(dateid, booknum, bookname);

            modelMap.put("bookingmade", "Rezerwacja na nazwisko: " + bookname);
            modelMap.put("bookingnum", "Liczba miejsc: " + booknum);
            modelMap.put("available", "Pozostało wolne: " + (BookingBase.getMaxUsers() - prevSum));
        } else {
            modelMap.put("bookingmade", "Rezerwacja niemożliwa.");
            modelMap.put("bookingnum", "");
            modelMap.put("available", "Liczba rezerwowanych miejsc (" +
                    booknum + ") przewyższa liczbę wolnych miejsc w tym dniu (" + (BookingBase.getMaxUsers() - prevSum) + ").");
        }
        prevSum = 0;
        return "bookdatedetails";
    }



}
