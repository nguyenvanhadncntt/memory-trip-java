package com.memorytrip.vn.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.memorytrip.vn.domain.Timeline} entity.
 */
public class TimelineDTO implements Serializable {
    
    private Long id;

    private String title;

    private String description;

    private Instant startTime;

    private Instant endTime;

    private Integer order;


    private Long locationId;

    private Long travelTripId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getTravelTripId() {
        return travelTripId;
    }

    public void setTravelTripId(Long travelTripId) {
        this.travelTripId = travelTripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimelineDTO)) {
            return false;
        }

        return id != null && id.equals(((TimelineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TimelineDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", order=" + getOrder() +
            ", locationId=" + getLocationId() +
            ", travelTripId=" + getTravelTripId() +
            "}";
    }
}
