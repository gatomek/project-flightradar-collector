package pl.gatomek.flightradar.radar.poc.radarcollector.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gatomek.flightradar.radar.poc.radarcollector.application.domain.model.AircraftNotification;
import pl.gatomek.flightradar.radar.poc.radarcollector.application.port.in.LogPort;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AircraftLogRestController {
    private final LogPort logPort;

    @GetMapping(value = "/logs")
    public ResponseEntity<AircraftNotification> getAircraftNotification() {
        AircraftNotification aircraftNotification = logPort.getLogs();
        log.info("REST request for logs: {}", aircraftNotification.getTotal());
        return ResponseEntity.ok( aircraftNotification);
    }
}
