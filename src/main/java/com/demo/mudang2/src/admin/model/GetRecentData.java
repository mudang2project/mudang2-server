package com.demo.mudang2.src.admin.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetRecentData {
    private List<GetRecentGps> getGpsList;
    private int headCount;
    private Date createdAt;
    private String time;
}
