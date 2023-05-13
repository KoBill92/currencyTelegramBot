package com.example.telegrambotcurrenyuz.service;

import com.example.telegrambotcurrenyuz.model.CurrencyModel;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CurrencyService {
    public static String getCurrencyRate(String message, CurrencyModel model) throws IOException, ParseException {

        URL url = new URL("https://cbu.uz/ru/arkhiv-kursov-valyut/json/" +
                message + "/" + LocalDateTime.now() + "/");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        StringBuilder result = new StringBuilder();

        while (scanner.hasNext()) {
            result.append(scanner.hasNextLine());
        }
        JSONObject object = new JSONObject(result);

        model.setCurr_Id((object.getInt("Cur_ID")));
        model.setDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(object.getString("Date")));
        model.setCurrAbbreviation((object.getString("Cur_Abbreviation")));
        model.setCurrScale((object.getInt("Cur_Scale")));
        model.setCurrName(object.getString("Cur_Name"));
        model.setCurrOfficialRate(object.getDouble("Cur_OfficialRate"));

        return "Official rate of BYN to " + model.getCurrAbbreviation() + "\n" +
                "on the date: " + getFormatDate(model) + "\n" +
                "is: " + model.getCurrOfficialRate() + " BYN per " + model.getCurrScale() + " " + model.getCurrAbbreviation();

    }

    private static String getFormatDate(CurrencyModel model) {
        return new SimpleDateFormat("dd MMM yyyy").format(model.getDate());
    }

}
