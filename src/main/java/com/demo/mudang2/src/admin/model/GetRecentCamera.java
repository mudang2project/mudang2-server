package com.demo.mudang2.src.admin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetRecentCamera {
    private String location;
    private int headCount;
    private Date createdAt;
    private String update;
}
