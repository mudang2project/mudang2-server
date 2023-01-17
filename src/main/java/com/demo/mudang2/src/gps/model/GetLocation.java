package com.demo.mudang2.src.gps.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetLocation {
    private List<Bus> busList;
}

