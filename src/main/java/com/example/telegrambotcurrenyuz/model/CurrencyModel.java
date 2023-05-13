package com.example.telegrambotcurrenyuz.model;

import lombok.Data;

import java.util.Date;

@Data
public class CurrencyModel {
    Integer curr_Id;
    Integer currScale;
    Date date;
    String currAbbreviation;
    String currName;
    Double currOfficialRate;
}
