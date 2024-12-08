package ru.yandex.practicum.service;

import ru.yandex.practicum.model.hub.HubEvent;

public interface HubEventService {

    void collect(HubEvent hubEvent);
}
