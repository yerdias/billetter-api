package com.hackload.billetter_api.service;

import com.hackload.billetter_api.dto.event.ListEventsResponseItem;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<ListEventsResponseItem> listEvents(String query, LocalDate date, int page, int pageSize);
}

