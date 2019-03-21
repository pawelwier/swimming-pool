package pl.akademiakodu.swimmingpool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {

    private String date;

    private Integer personNum;

    private String name;

}
