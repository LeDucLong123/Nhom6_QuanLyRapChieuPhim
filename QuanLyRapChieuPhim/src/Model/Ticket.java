/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */
public class Ticket {
    private Showtime showtime;
    private String customerName;

    public Ticket(Showtime showtime, String customerName) {
        this.showtime = showtime;
        this.customerName = customerName;
    }

    // Getters và setters
    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "showtime=" + showtime +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    public Object getUsername() {
        return "temp";
    }
}
