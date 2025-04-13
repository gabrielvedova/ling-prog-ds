package exe01.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // JSON → Objeto (usando arquivo de resources)
        File file = new File("Atividade02/exe01/tasks.json");
        if (!file.exists()) {
            throw new IOException("Arquivo não encontrado na pasta raiz" );
        }

        List<Task> tasks = mapper.readValue(file, new TypeReference<List<Task>>() {
        });

        // User input
        UserInput(tasks);
    }

    public static void UserInput(List<Task> tasks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a ação que deseje realizar:");
        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Marcar uma tarefa como conluída");
        System.out.println("4 - Sair");
        int action = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        
        // Estrutura de condição para verificar a ação
        
        if (action == 1) {
            System.out.println("Digite o nome da tarefa:");
            String taskName = scanner.nextLine();
            System.out.println("Digite a data da tarefa (yyyy-MM-dd):");
            String date = scanner.nextLine();
            
            addTask(tasks, taskName, date);
        } else if (action == 2) {
            // Listar tarefas
            readTasks(tasks);
        } else if (action == 3) {
            // Marcar tarefa como concluída
            System.out.println("Digite o nome da tarefa a ser marcada como concluída:");
            String taskName = scanner.nextLine();

            markTaskAsCompleted(tasks, taskName);
        }
        scanner.close();
    }

    private static void addTask(List<Task> task, String taskName, String date) {
        Task newTask = new Task();
        newTask.task = taskName;
        newTask.date = date;
        newTask.status = "Pendente";
        task.add(newTask);

        // Salvar a lista de tarefas de volta no arquivo JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = Paths.get("Atividade02/exe01/tasks.json").toFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, task);
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a tarefa:" + e.getMessage());
        }
    }

    private static void readTasks(List<Task> task) {
        System.out.println("Tarefas:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        task.stream()
            .sorted(Comparator.comparing(t -> LocalDate.parse(t.date, formatter)))
            .forEach(t -> {
                if (t.status.equals("Concluída")) {
                    System.out.println("Tarefa: " + t.task + " | Data: " + t.date + " | Status: " + t.status);
                } else {
                    System.out.println("Tarefa: " + t.task + " | Data: " + t.date);
                }
            });
    }

    private static void markTaskAsCompleted(List<Task> tasks, String taskName) {
        for (Task task : tasks) {
            if (task.task.equals(taskName)) {
                task.status = "Concluída";
                System.out.println("Tarefa marcada como concluída: " + taskName);

                // Salvar a lista de tarefas de volta no arquivo JSON
                ObjectMapper mapper = new ObjectMapper();
                try {
                    File file = Paths.get("Atividade02/exe01/tasks.json").toFile();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
                } catch (IOException e) {
                    System.out.println("Erro ao marcar a tarefa como concluída:" + e.getMessage());
                }
                break;
            }
        }
    }
}

class Task {
    public String task;
    public String date;
    public String status;
}
