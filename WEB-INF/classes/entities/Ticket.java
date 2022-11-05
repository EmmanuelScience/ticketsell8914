package entities;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
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

    @Column(name = "user", nullable = false)
    private Integer user;

    @Column(name = "event", nullable = false)
    private Integer event;

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
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    public void setTicketOwnerName(String ticketOwnerName) {
        this.ticketOwnerName = ticketOwnerName;
    }

    public String getTicketOwnerName() {
        return ticketOwnerName;
    }
}