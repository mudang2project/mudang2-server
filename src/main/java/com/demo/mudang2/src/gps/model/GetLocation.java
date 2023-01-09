package com.demo.mudang2.src.gps.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class GetLocation {
    private int busIdx;
    private String lat;
    private String lon;
}