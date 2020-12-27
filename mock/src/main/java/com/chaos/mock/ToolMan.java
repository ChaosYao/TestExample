package com.chaos.mock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToolMan {

    private String  name;

    private Leg     leg;

    private Foot    foot;

    public Long getLegLength() {
        return leg.getLength();
    }
}
