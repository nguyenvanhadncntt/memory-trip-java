package com.memorytrip.vn.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.memorytrip.vn.domain.TravelTrip} entity.
 */
public class TravelTripDTO implements Serializable {
    
    private Long id;

    private String title;

    private String description;


    private Long travelImageId;

    private Long userId;
    
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

    public Long getTravelImageId() {
        return travelImageId;
    }

    public void setTravelImageId(Long fileId) {
        this.travelImageId = fileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelTripDTO)) {
            return false;
        }

        return id != null && id.equals(((TravelTripDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelTripDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", travelImageId=" + getTravelImageId() +
            ", userId=" + getUserId() +
            "}";
    }
}
