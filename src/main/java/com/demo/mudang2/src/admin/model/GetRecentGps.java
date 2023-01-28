package com.demo.mudang2.src.admin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class GetRecentGps {
    private int busIdx;
    private String lat;
    private String lon;
    private Date createdAt;
    private String update;

}
