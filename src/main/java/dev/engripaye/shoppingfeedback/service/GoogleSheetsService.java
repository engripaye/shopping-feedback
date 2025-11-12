package dev.engripaye.shoppingfeedback.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;
import dev.engripaye.shoppingfeedback.model.Feedback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class GoogleSheetsService {

    @Value("${google.sheets.service.account.path}")
    private Resource serviceAccountResource; // Spring Resource handles classpath

    @Value("${google.sheets.spreadsheet.id}")
    private String spreadsheetId;

    @Value("${google.sheets.sheet.name}")
    private String sheetName;

    private static final String APPLICATION_NAME = "Shopping feedback";

    private Sheets sheetsService;

    public GoogleSheetsService() {}

    // Initialize sheets service after properties are injected
    @Value("${google.sheets.service.account.path}") // Dummy injection to trigger init
    private void initSheetsService(Resource ignored) throws GeneralSecurityException, IOException {
        sheetsService = getSheetsService();
    }

    private Sheets getSheetsService() throws GeneralSecurityException, IOException {
        try (InputStream serviceAccountStream = serviceAccountResource.getInputStream()) {
            ServiceAccountCredentials credentials = (ServiceAccountCredentials) ServiceAccountCredentials.fromStream(serviceAccountStream)
                    .createScoped(Arrays.asList("https://www.googleapis.com/auth/spreadsheets"));

            return new Sheets.Builder(
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

