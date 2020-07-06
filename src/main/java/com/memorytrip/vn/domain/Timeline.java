package com.memorytrip.vn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Timeline.
 */
@Entity
@Table(name = "timeline")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Timeline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "jhi_order")
    private Integer order;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @OneToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<File> files = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "timelines", allowSetters = true)
    private TravelTrip travelTrip;

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

    public Timeline title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Timeline description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Timeline startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Timeline endTime(Instant endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Integer getOrder() {
        return order;
    }

    public Timeline order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Location getLocation() {
        return location;
    }

    public Timeline location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<File> getFiles() {
        return files;
    }

    public Timeline files(Set<File> files) {
        this.files = files;
        return this;
    }

    public Timeline addFiles(File file) {
        this.files.add(file);
        return this;
    }

    public Timeline removeFiles(File file) {
        this.files.remove(file);
        return this;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public TravelTrip getTravelTrip() {
        return travelTrip;
    }

    public Timeline travelTrip(TravelTrip travelTrip) {
        this.travelTrip = travelTrip;
        return this;
    }

    public void setTravelTrip(TravelTrip travelTrip) {
        this.travelTrip = travelTrip;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Timeline)) {
            return false;
        }
        return id != null && id.equals(((Timeline) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Timeline{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", order=" + getOrder() +
            "}";
    }
}
