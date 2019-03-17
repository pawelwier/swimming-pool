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

@Controller
public class CheckController {

    BookingService bookingService = new BookingService();

    int prevSum = 0;

    @GetMapping("/check")
    public String showReservations() {

        return "checkavailable";
    }

    @RequestMapping("/checkdate")
    public String sonfirmHowManyAvailable(ModelMap modelMap,
                                        @RequestParam String year,
                                        @RequestParam String month,
                                        @RequestParam String day) {

        modelMap.put("year", year);
        modelMap.put("month", month);
        modelMap.put("day", day);
        return "checkdate";
    }

    @RequestMapping("/date/{dateid}")
    public String CheckHowManyAvailable(@PathVariable Integer dateid,
                                        ModelMap modelMap) {

        for (Booking b : BookingBase.list) {
            if (dateid.equals(b.getDate())) {
                prevSum += b.getPersonNum();
            }
        }
            modelMap.put("available", BookingBase.getMaxUsers() - prevSum);

            prevSum = 0;

            return "datedetails";
    }
}
