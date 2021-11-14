package com.oddschecker.service;

import com.oddschecker.model.Odds;
import com.oddschecker.model.OddsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OddsServiceTest {

    @Mock
    private OddsRepository oddsRepository;

    @InjectMocks
    private OddsService oddsService;

    @Test
    void serviceCallsFindAllByBetId() {
        oddsService.findAllByBetId(2L);
        verify(oddsRepository).findAllByBetId(2L);
    }

    @Test
    void serviceCallsSaveOdd() {
        Odds odd = new Odds();
        oddsService.addOdd(odd);
        verify(oddsRepository).save(odd);
    }
}