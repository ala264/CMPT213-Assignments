import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class App {
    static ArrayList<LoanInformation> loanInformation = new ArrayList<>();
    static final String SAVE_FILE_PATH = "./list.json";

    static Gson myGson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
        new TypeAdapter<LocalDate>() {
            @Override
            public void write(JsonWriter jsonWriter,
                LocalDate localDate) throws IOException {
                    jsonWriter.value(localDate.toString());
            }
            @Override
            public LocalDate read(JsonReader jsonReader) throws IOException {
                return LocalDate.parse(jsonReader.nextString());
            }
        }).create();

    public static void main(String[] args) throws Exception {
        loadFromFile();

        boolean done = false;
        TextMenu textMenu = new TextMenu("Armans super cool menu");
        textMenu.addMenuOption("List all items");
        textMenu.addMenuOption("Add an item");
        textMenu.addMenuOption("Remove an item");
        textMenu.addMenuOption("List overdue items ");
        textMenu.addMenuOption("List upcoming items");
        textMenu.addMenuOption("Exit");
        do {
            textMenu.displayMenuTitle();
            int userInput = textMenu.readUserInput();
            if(userInput == 6){
                System.out.println("Saving the list to ./list.json...\n \nThanks for using Loan Items Tracker!");
                saveToFile();
                done = true;
            }
        } while (!done);
    }

    private static void loadFromFile() {
        Path path = Paths.get(SAVE_FILE_PATH);
        if (!Files.exists(path)) {
            return;
        }
        try (FileReader fileReader = new FileReader(SAVE_FILE_PATH)) {
            Type type = new TypeToken<ArrayList<LoanInformation>>(){}.getType();
            ArrayList<LoanInformation> loaded = myGson.fromJson(fileReader, type);
            if (loaded != null) {
                loanInformation = loaded;
            }
        } catch (IOException e) {
            System.out.println("Could not load saved list:");
        }
    }

    private static void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH)) {
            myGson.toJson(loanInformation, fileWriter);
        } catch (IOException e) {
            System.out.println("Could not save list:");
        }
    }
}
