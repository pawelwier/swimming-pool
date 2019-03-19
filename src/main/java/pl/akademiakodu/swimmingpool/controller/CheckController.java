package pl.akademiakodu.swimmingpool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.swimmingpool.data.BookingBase;
import pl.akademiakodu.swimmingpool.model.Booking;
import pl.akademiakodu.swimmingpool.service.BookingService;
import pl.akademiakodu.swimmingpool.service.CheckService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CheckController {

    int prevSum = 0;

    @GetMapping("/check")
    public String showReservations() {

        return "checkavailable";
    }

    @RequestMapping("/checkdate")
    public String sonfirmHowManyAvailable(ModelMap modelMap,
                                        @RequestParam String time,
                                        @RequestParam String month,
                                        @RequestParam String day) {

        modelMap.put("time", BookingService.showTime(time));
        modelMap.put("month", BookingService.showMonthNumber(month));
        modelMap.put("day", day);
        return "redirect:/date/"+BookingService.showTime(time)+BookingService.showMonthNumber(month)+day;
    }

    @RequestMapping("/date/{dateid}")
    public String checkHowManyAvailable(@PathVariable Integer dateid,
                                        ModelMap modelMap) {

       List<Booking> bookings = CheckService.CheckForPreviousBookings(dateid);

        modelMap.put("previousbookings", bookings);

            modelMap.put("available", BookingBase.getMaxUsers() - CheckService.updatePrevSum(dateid, prevSum));
            modelMap.put("track1", CheckService.countTrack1(CheckService.updatePrevSum(dateid, prevSum)));
            modelMap.put("track2", CheckService.countTrack2(CheckService.updatePrevSum(dateid, prevSum)));
            modelMap.put("track3", CheckService.countTrack3(CheckService.updatePrevSum(dateid, prevSum)));

            prevSum = 0;

            return "datedetails";
    }

    @RequestMapping("/booking/{bookname}/{booknum}/{dateid}/delete")
    public String deleteSelectedBooking(@PathVariable String bookname,
                                @PathVariable Integer booknum,
                                @PathVariable Integer dateid,
                                RedirectAttributes redirectAttributes) {

        CheckService.deleteBooking(bookname, booknum, dateid);

        redirectAttributes.addFlashAttribute("deletedbooking", "Usunięto rezerwację.");

        return "redirect:/";
    }
}
