package pl.akademiakodu.swimmingpool.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.akademiakodu.swimmingpool.data.BookingBase;
import pl.akademiakodu.swimmingpool.model.Booking;
import pl.akademiakodu.swimmingpool.service.CheckService;

import java.util.List;

@Controller
public class MainController {

    int prevSum = 0;
    private String auth;
    public boolean flag = false;

    @GetMapping("/")
    public String showMain(Model model) {

        SecurityContext context = SecurityContextHolder.getContext();
        auth = context.getAuthentication().getName();
        if (auth.equals("admin")) {flag = true;}

        model.addAttribute("flag", flag);

        return "main";
    }

    @RequestMapping("/date/{dateid}")
    public String checkHowManyAvailable(@PathVariable Integer dateid,
                                        ModelMap modelMap) {

        SecurityContext context = SecurityContextHolder.getContext();
        auth = context.getAuthentication().getName();
        if (auth.equals("admin")) {flag = true;}

        modelMap.addAttribute("flag", flag);

        List<Booking> bookings = CheckService.CheckForPreviousBookings(dateid);

        modelMap.put("previousbookings", bookings);

        modelMap.put("available", BookingBase.getMaxUsers() - CheckService.updatePrevSum(dateid, prevSum));
        modelMap.put("track1", CheckService.countTrack1(CheckService.updatePrevSum(dateid, prevSum)));
        modelMap.put("track2", CheckService.countTrack2(CheckService.updatePrevSum(dateid, prevSum)));
        modelMap.put("track3", CheckService.countTrack3(CheckService.updatePrevSum(dateid, prevSum)));

        prevSum = 0;

        return "datedetails";
    }

}
