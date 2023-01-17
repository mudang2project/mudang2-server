package com.demo.mudang2.src.gps.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Bus {
    private int busIdx;
    private String lat;
    private String lon;
    private Long interval;
}
