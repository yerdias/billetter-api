package com.hackload.billetter_api.service.impl;

import com.hackload.billetter_api.dto.event.ListEventsResponseItem;
import com.hackload.billetter_api.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StubEventService implements EventService {
    @Override
    public List<ListEventsResponseItem> listEvents(String query, LocalDate date, int page, int pageSize) {
        // Temporary stub implementation; returns empty list
        return List.of();
    }
}

