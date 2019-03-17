package pl.akademiakodu.swimmingpool.model;

public class Booking {

    private Integer date;

    private Integer personNum;

    private String name;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Booking(Integer date, Integer personNum, String name) {
        this.date = date;
        this.personNum = personNum;
        this.name = name;
    }

    public Booking() {

    }
}
