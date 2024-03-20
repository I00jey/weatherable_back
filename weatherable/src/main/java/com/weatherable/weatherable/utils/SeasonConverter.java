package com.weatherable.weatherable.utils;
import org.springframework.core.convert.converter.Converter;
import com.weatherable.weatherable.enums.Season;
import java.util.ArrayList;


public class SeasonConverter implements Converter<ArrayList<String>, Season> {

    @Override
    public Season convert(ArrayList<String> source) {
        System.out.println("Converting season array: " + source);

        if (source == null || source.isEmpty()) {
            System.out.println("Empty or null source, returning null");
            return null;
        }

        String seasonString = source.get(0);
        System.out.println("Extracted season string: " + seasonString);

        for (Season season : Season.values()) {
            if (season.name().equalsIgnoreCase(seasonString)) {
                System.out.println("Converted season: " + season);
                return season;
            }
        }

        throw new IllegalArgumentException("Unsupported season value: " + seasonString);
    }
}
