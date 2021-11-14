package com.oddschecker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddschecker.TestHelper;
import com.oddschecker.OddsCheckerMavenWebappApplication;
import com.oddschecker.model.Odds;
import com.oddschecker.service.OddsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;


import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * We'll start the whole context, but not the server. We'll mock the REST calls instead.
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = OddsCheckerMavenWebappApplication.class)
class OddsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OddsService oddsService;

    @ParameterizedTest
    @MethodSource("betIdAndJsonOutputProvider")
    public void retrieveOdds(Long betId, String jsonOutput) throws Exception {

        //- _Given_ I am a user
        Stream.of(TestHelper.createOdd(1L,"userId_1","1/2"),
                        TestHelper.createOdd(1L,"userId_2","SP"),
                        TestHelper.createOdd(2L,"userId_3","4/2")).
                forEach(odd-> oddsService.addOdd(odd));

         //- _When_ I request to see odds for an ID
        //- _Then_ I can see all of the odds for that ID
        this.mockMvc.perform(get("/odds/" + betId))
                .andExpect(status().isOk()).andExpect(content().json(jsonOutput));

    }

    //showing here data is handy for having logic and data in one place, to make this more tidy we could move json string to external files
    static Stream<Arguments> betIdAndJsonOutputProvider() {
        return Stream.of(
                arguments(1L, "[{\"betId\":1,\"userId\":\"userId_1\",\"odds\":\"1/2\"},{\"betId\":1,\"userId\":\"userId_2\",\"odds\":\"SP\"}]"),
                arguments(2L, "[{\"betId\":2,\"userId\":\"userId_3\",\"odds\":\"4/2\"}]"
                ));
    }

    @Test
    public void oddsNotFound() throws Exception {

        //- _Given_ I am a user
        Stream.of(TestHelper.createOdd(1L,"userId_1","1/2")).
                forEach(odd-> oddsService.addOdd(odd));

        //- _When_ I request to see odds for an ID that is not present
        //- _Then_ I get not found response
        this.mockMvc.perform(get("/odds/2" ))
                .andExpect(status().isNotFound());

    }


    @ParameterizedTest
    @ValueSource(strings = {"1/2","2/1","SP","sp","Sp","sP","2/21"})
    public void handleValidOdds(String oddsString) throws Exception {

        //- _Given_ I am a user
        Odds odd = new Odds();
        odd.setUserId("userId_1");
        odd.setBetId(1L);

        //- _When_ I submit valid odds for an ID
        odd.setOdds(oddsString);

        //- _Then_ my odds are accepted
        this.mockMvc.perform( post("/odds")
                .content(asJsonString(odd))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1//2","2","SP/","/sp","S/p","","£","1/2s","s2/7","null","0/1","1/0","3\\2","1/2/3","SP/1"})
    public void handleNotValidOdds(String oddsString) throws Exception {

        //- _Given_ I am a user
        Odds odd = new Odds();
        odd.setUserId("userId_1");
        odd.setBetId(1L);

        //- _When_ I submit valid odds for an ID
        odd.setOdds(oddsString);

        //- _Then_ my odds are accepted
        this.mockMvc.perform( post("/odds")
                        .content(asJsonString(odd))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

     static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}