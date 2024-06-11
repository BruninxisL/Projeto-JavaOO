package com.example.AppPastelaria.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");

    public List<String> formatDates(List<LocalDateTime> dates) {
        List<String> formattedDates = new ArrayList<>();
        for (LocalDateTime date : dates) {
            formattedDates.add(FORMATTER.format(date));
        }
        return formattedDates;
    }
}
