package com.oddschecker.model;

import java.io.Serializable;

public class OddComplexKey implements Serializable {

    private Long betId;
    private String userId;

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}