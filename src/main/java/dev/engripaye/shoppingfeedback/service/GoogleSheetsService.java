package dev.engripaye.shoppingfeedback.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;
import dev.engripaye.shoppingfeedback.model.Feedback;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class GoogleSheetsService {

    @Value("${GOOGLE_SERVICE_ACCOUNT_JSON}")
    private String serviceAccountJson;

    @Value("${SPREADSHEET_ID}")
    private String spreadsheetId;

    @Value("${SHEET_NAME}")
    private String sheetName;

    private static final String APPLICATION_NAME = "Shopping feedback";

    private Sheets sheetsService;

    @PostConstruct
    private void init() throws GeneralSecurityException, IOException {
        try (InputStream stream = new ByteArrayInputStream(serviceAccountJson.getBytes(StandardCharsets.UTF_8))) {
            ServiceAccountCredentials credentials = (ServiceAccountCredentials) ServiceAccountCredentials.fromStream(stream)
                    .createScoped(Arrays.asList("https://www.googleapis.com/auth/spreadsheets"));

            sheetsService = new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
    }

    public void appendFeedback(Feedback feedback) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(Arrays.asList(
                        feedback.getName(),
                        feedback.getContact(),
                        feedback.getRating(),
                        feedback.getItemsNotFound(),
                        feedback.getPriceToReduce(),
                        feedback.getImprovementSuggestion(),
                        feedback.getCreatedAt().format(formatter)
                )));
        sheetsService.spreadsheets().values()
                .append(spreadsheetId, sheetName + "!A1", body)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }
}

