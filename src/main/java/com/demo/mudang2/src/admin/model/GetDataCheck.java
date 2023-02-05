package com.demo.mudang2.src.admin.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetDataCheck {
    private int busIdx;
    private String dayData;
    private int monthData;

    public String toString(){
        return "busIdx : "+busIdx+"    dayData : "+ dayData + "     monthData :" + monthData ;
    }
}
