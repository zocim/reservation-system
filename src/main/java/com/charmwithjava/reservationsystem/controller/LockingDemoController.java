package com.charmwithjava.reservationsystem.controller;

import com.charmwithjava.reservationsystem.entity.BusDetails;
import com.charmwithjava.reservationsystem.repository.BusRepository;
import com.charmwithjava.reservationsystem.service.BookingService;
import org.apache.commons.lang3.Functions;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
public class LockingDemoController {

    private BookingService bookingService;

    private BusRepository busRepository;

    public LockingDemoController(BookingService bookingService, BusRepository busRepository) {
        this.bookingService = bookingService;
        this.busRepository = busRepository;
    }

    @GetMapping("/bookTicket")
    public void bookTicket(){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(run(bookingService::bookTicket));
        executor.execute(run(bookingService::bookTicket1));
        executor.shutdown();
    }

    private Runnable run(Functions.FailableRunnable<Exception> runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

    }

    @PostMapping("/addBus")
    public void addBus(@RequestParam String number, @RequestParam int capacity) {
        BusDetails busDetails = new BusDetails();
        busDetails.setCapacity(capacity);
        busDetails.setBusNumber(number);
        busDetails.setDepartureDateTime(LocalDateTime.now());

        busRepository.save(busDetails);
    }
}
