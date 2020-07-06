package com.memorytrip.vn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TravelTrip.
 */
@Entity
@Table(name = "travel_trip")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TravelTrip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private File travelImage;

    @ManyToOne
    @JsonIgnoreProperties(value = "travelTrips", allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "travelTrip")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Timeline> timelines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public TravelTrip title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public TravelTrip description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getTravelImage() {
        return travelImage;
    }

    public TravelTrip travelImage(File file) {
        this.travelImage = file;
        return this;
    }

    public void setTravelImage(File file) {
        this.travelImage = file;
    }

    public User getUser() {
        return user;
    }

    public TravelTrip user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Timeline> getTimelines() {
        return timelines;
    }

    public TravelTrip timelines(Set<Timeline> timelines) {
        this.timelines = timelines;
        return this;
    }

    public TravelTrip addTimelines(Timeline timeline) {
        this.timelines.add(timeline);
        timeline.setTravelTrip(this);
        return this;
    }

    public TravelTrip removeTimelines(Timeline timeline) {
        this.timelines.remove(timeline);
        timeline.setTravelTrip(null);
        return this;
    }

    public void setTimelines(Set<Timeline> timelines) {
        this.timelines = timelines;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelTrip)) {
            return false;
        }
        return id != null && id.equals(((TravelTrip) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelTrip{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
