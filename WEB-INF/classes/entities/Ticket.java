package entities;

import javax.persistence.*;
import entities.Event;
import entities.User;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticketID", nullable = false)
    private Integer id;

    @Column(name = "ticketCode", nullable = false, length = 45)
    private String ticketCode;

    @Column(name = "category", nullable = false, length = 45)
    private String category;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @Column(name = "ticketOwnerName", nullable = false)
    private String ticketOwnerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUser() {
        return user.getId();
    }

    public void setUser(Integer user) { this.user.setId(user); }

    public Integer getEvent() {
        return event.getId();
    }

    public void setEvent(Integer event) {
        this.event.setId(event);
    }

    public void setTicketOwnerName(String ticketOwnerName) {
        this.ticketOwnerName = ticketOwnerName;
    }

    public String getTicketOwnerName() {
        return ticketOwnerName;
    }
}