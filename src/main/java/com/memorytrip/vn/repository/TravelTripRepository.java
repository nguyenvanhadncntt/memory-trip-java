package com.memorytrip.vn.repository;

import com.memorytrip.vn.domain.TravelTrip;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the TravelTrip entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelTripRepository extends JpaRepository<TravelTrip, Long> {

    @Query("select travelTrip from TravelTrip travelTrip where travelTrip.user.login = ?#{principal.username}")
    List<TravelTrip> findByUserIsCurrentUser();
}
