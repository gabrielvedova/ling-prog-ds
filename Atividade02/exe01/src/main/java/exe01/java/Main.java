package exe01.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // JSON → Objeto (usando arquivo de resources)
        InputStream is = Main.class.getClassLoader().getResourceAsStream("tasks.json");
        if (is == null) {
            throw new IOException("Arquivo 'tasks.json' não encontrado em src/main/resources");
        }

        List<Task> tasks = mapper.readValue(is, new TypeReference<List<Task>>() {
        });

        readTasks(tasks);
    }

    public static void readTasks(List<Task> task) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        task.stream()
            .sorted(Comparator.comparing(t -> LocalDate.parse(t.date, formatter)))
            .forEach(t -> System.out.println(t.task + " - " + t.date));
    }
}

class Task {
    public String task;
    public String date;
}
