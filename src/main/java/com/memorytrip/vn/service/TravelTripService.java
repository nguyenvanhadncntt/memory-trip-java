package com.memorytrip.vn.service;

import com.memorytrip.vn.service.dto.TravelTripDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.memorytrip.vn.domain.TravelTrip}.
 */
public interface TravelTripService {

    /**
     * Save a travelTrip.
     *
     * @param travelTripDTO the entity to save.
     * @return the persisted entity.
     */
    TravelTripDTO save(TravelTripDTO travelTripDTO);

    /**
     * Get all the travelTrips.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TravelTripDTO> findAll(Pageable pageable);


    /**
     * Get the "id" travelTrip.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TravelTripDTO> findOne(Long id);

    /**
     * Delete the "id" travelTrip.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
