package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eventID", nullable = false)
    private Integer id;

    @Column(name = "eventName", nullable = false, length = 45)
    private String eventName;

    @Column(name = "venue", nullable = false, length = 45)
    private String venue;

    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @Column(name = "country", nullable = false, length = 45)
    private String country;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "category", nullable = false, length = 45)
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}