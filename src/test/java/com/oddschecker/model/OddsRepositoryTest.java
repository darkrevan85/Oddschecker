package com.oddschecker.model;

import com.oddschecker.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@DataJpaTest
public class OddsRepositoryTest {

    @Autowired
    private OddsRepository oddsRepository;

    @Test
    public void findAllByBetIdReturnsOneEntity(){

        // given an entity is persisted in the db
        Odds toSave = TestHelper.createOdd(1L,"userId_1","SP");
        oddsRepository.save(toSave);

        // when I retrieve all entities with a particular bet id
        List<Odds> found = oddsRepository.findAllByBetId(1L);

        // then I get from the db the entity wanted
        assertThat(found , contains(allOf(hasProperty("betId", is(1L)),
                hasProperty("userId", is("userId_1")),
                hasProperty("odds", is("SP")))));
    }

}