package com.oddschecker.model;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@IdClass(OddComplexKey.class)
public class Odds implements Serializable {

    @Id
    private Long betId;

    @Id
    private String userId;

    @Pattern(regexp = "^([1-9][0-9]*\\/[1-9][0-9]*|[Ss][Pp]){1}$")
    private String odds;

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

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds.toUpperCase();
    }


}
