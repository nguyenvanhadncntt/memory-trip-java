package com.memorytrip.vn.service.impl;

import com.memorytrip.vn.service.TravelTripService;
import com.memorytrip.vn.domain.TravelTrip;
import com.memorytrip.vn.repository.TravelTripRepository;
import com.memorytrip.vn.service.dto.TravelTripDTO;
import com.memorytrip.vn.service.mapper.TravelTripMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TravelTrip}.
 */
@Service
@Transactional
public class TravelTripServiceImpl implements TravelTripService {

    private final Logger log = LoggerFactory.getLogger(TravelTripServiceImpl.class);

    private final TravelTripRepository travelTripRepository;

    private final TravelTripMapper travelTripMapper;

    public TravelTripServiceImpl(TravelTripRepository travelTripRepository, TravelTripMapper travelTripMapper) {
        this.travelTripRepository = travelTripRepository;
        this.travelTripMapper = travelTripMapper;
    }

    @Override
    public TravelTripDTO save(TravelTripDTO travelTripDTO) {
        log.debug("Request to save TravelTrip : {}", travelTripDTO);
        TravelTrip travelTrip = travelTripMapper.toEntity(travelTripDTO);
        travelTrip = travelTripRepository.save(travelTrip);
        return travelTripMapper.toDto(travelTrip);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TravelTripDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TravelTrips");
        return travelTripRepository.findAll(pageable)
            .map(travelTripMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TravelTripDTO> findOne(Long id) {
        log.debug("Request to get TravelTrip : {}", id);
        return travelTripRepository.findById(id)
            .map(travelTripMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TravelTrip : {}", id);
        travelTripRepository.deleteById(id);
    }
}
