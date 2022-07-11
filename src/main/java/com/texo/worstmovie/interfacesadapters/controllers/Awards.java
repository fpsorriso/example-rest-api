package com.texo.worstmovie.interfacesadapters.controllers;

import com.texo.worstmovie.business.usecase.GetConsecutiveAwardsByProducer;
import com.texo.worstmovie.business.usecase.presenter.dtos.ConsecutiveAwardsResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/awards")
public class Awards {
    private final GetConsecutiveAwardsByProducer getConsecutiveAwardsByProducer;

    public Awards(final GetConsecutiveAwardsByProducer getConsecutiveAwardsByProducer) {
        this.getConsecutiveAwardsByProducer = getConsecutiveAwardsByProducer;
    }

    @GetMapping(value = "/producers/consecutive", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsecutiveAwardsResponse> getConsecutiveByProducer() {
        return ResponseEntity.ok(getConsecutiveAwardsByProducer.get());
    }
}
