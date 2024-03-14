package com.jetbrains.gemmy.punishment.controller;

import com.jetbrains.gemmy.punishment.model.Btc;
import com.jetbrains.gemmy.punishment.service.BtcService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BtcController {
    private final BtcService btcService;

    public BtcController(BtcService btcService) {
        this.btcService = btcService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> get() {
        return ResponseEntity.status(HttpStatus.OK).body(btcService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(btcService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        btcService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully!");
    }

    @PostMapping("/")
    public ResponseEntity<Object> addBtc(@RequestBody Btc btc) {
        btcService.add(btc);
        return ResponseEntity.status(HttpStatus.OK).body("Added btc data successfully!");
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> modifyBtc(@PathVariable int id, @RequestBody Btc btc) {
        btcService.modify(id, btc);
        return ResponseEntity.status(HttpStatus.OK).body("Modified successfully!");
    }
}
