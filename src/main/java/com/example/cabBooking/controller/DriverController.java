package com.example.cabBooking.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.example.cabBooking.exception.ErrorMessage;
import com.example.cabBooking.exception.InvalidRequestException;
import com.example.cabBooking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping("/")
    @ApiOperation("This is the Cab booking platform")
    public String hello() {
        return "Book cabs now!!";
    }

    @PostMapping("/api/v1/driver/register")
    @ApiOperation("Register a driver.")
    public ResponseEntity<String> driverRegistration(@RequestBody String requestBody, @RequestHeader (value = "content-type") String contentTypeHeader) throws Exception {

        log.debug("Request for driver registration: " + requestBody + "\nheaders: " + contentTypeHeader);
        if (!contentTypeHeader.equalsIgnoreCase("application/json")) {
            throw new InvalidRequestException(ErrorMessage.INVALID_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        }

        String driverRegistrationResponse = driverService.registerDriver(requestBody);

        log.debug("Response for driver registration: " + driverRegistrationResponse);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(driverRegistrationResponse, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/driver/{id}/sendLocation")
    @ApiOperation("Save location of a given driver.")
    public ResponseEntity<String> saveDriverLocation(@PathVariable (value = "id") Long id, @RequestBody String requestBody, @RequestHeader (value = "content-type") String contentTypeHeader) throws Exception {

        log.debug("Request for saving driver " + id + "'s location: " + requestBody + "\nheaders: " + contentTypeHeader);
        if (!contentTypeHeader.equalsIgnoreCase("application/json")) {
            throw new InvalidRequestException(ErrorMessage.INVALID_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        }

        String saveDriverLocationResponse = driverService.saveDriverLocation(requestBody, id);

        log.debug("Response for saving driver : " + id + "'s location: " + saveDriverLocationResponse);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(saveDriverLocationResponse, responseHeaders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/v1/driver/all")
    @ApiOperation("Get all drivers.")
    public ResponseEntity<String> getAllDrivers() throws Exception {

        log.debug("Request received for getting all drivers.");

        String getAllDriversResponse = driverService.getAllDrivers();

        log.debug("Response for getting all drivers." + getAllDriversResponse);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(getAllDriversResponse, responseHeaders, HttpStatus.OK);
    }
}
