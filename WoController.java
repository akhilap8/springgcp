package com.strawberry.practgcp.controller;

import com.strawberry.practgcp.model.Wo;
import com.strawberry.practgcp.service.WoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wo")
public class WoController {

    @Autowired
    private WoService woService;

    private static final Logger logger = LoggerFactory.getLogger(WoController.class);

    @GetMapping
    public ResponseEntity<List<Wo>> findAll() {
        try {
            List<Wo> woList = woService.getAll();
            return ResponseEntity.ok(woList);
        } catch(Exception e) {
            logger.error("Error retrieving workouts", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{start}/{end}")
    public ResponseEntity<List<Wo>> findDateRange(@PathVariable String start, @PathVariable String end) {
        try {
            List<Wo> woList2 = woService.findDateRange(start, end);
            return ResponseEntity.ok(woList2);
        } catch(Exception e) {
            logger.error("Error retrieving workouts", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}