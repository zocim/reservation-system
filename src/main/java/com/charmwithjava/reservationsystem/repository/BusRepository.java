package com.charmwithjava.reservationsystem.repository;

import com.charmwithjava.reservationsystem.entity.BusDetails;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface BusRepository extends JpaRepository<BusDetails, Long> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<BusDetails> findWithLockingById(Long id);
}
