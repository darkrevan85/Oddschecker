package com.oddschecker;

import com.oddschecker.model.Odds;

public class TestHelper {

    //we could even directly create a builder for this entity instead of using a method like this,
    // as we have only 3 fields I preferred using setters
    public static Odds createOdd(Long betId, String userId, String odds){
        Odds odd = new Odds();
        odd.setBetId(betId);
        odd.setUserId(userId);
        odd.setOdds(odds);

        return odd;
    }
}
