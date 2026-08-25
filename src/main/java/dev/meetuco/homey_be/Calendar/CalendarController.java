package dev.meetuco.homey_be.Calendar;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private static final String PRIMARY_CALENDAR_ID = "primary";

    private Calendar buildCalendarClient(OAuth2AuthorizedClient authorizedClient) throws GeneralSecurityException, IOException {
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                request -> request.getHeaders().setAuthorization("Bearer " + accessToken))
                .setApplicationName("homey-be")
                .build();
    }

    @GetMapping("/events")
    public List<Event> listEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) throws GeneralSecurityException, IOException {
        Calendar calendar = buildCalendarClient(authorizedClient);
        Events events = calendar.events().list(PRIMARY_CALENDAR_ID)
                .setMaxResults(20)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events.getItems();
    }

    @PostMapping("/events")
    public Event createEvent(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient, @RequestBody Event event) throws GeneralSecurityException, IOException {
        Calendar calendar = buildCalendarClient(authorizedClient);
        return calendar.events().insert(PRIMARY_CALENDAR_ID, event).execute();
    }

    @PutMapping("/events/{eventId}")
    public Event updateEvent(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient, @PathVariable String eventId, @RequestBody Event event) throws GeneralSecurityException, IOException {
        Calendar calendar = buildCalendarClient(authorizedClient);
        return calendar.events().update(PRIMARY_CALENDAR_ID, eventId, event).execute();
    }

    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient, @PathVariable String eventId) throws GeneralSecurityException, IOException {
        Calendar calendar = buildCalendarClient(authorizedClient);
        calendar.events().delete(PRIMARY_CALENDAR_ID, eventId).execute();
    }
}
