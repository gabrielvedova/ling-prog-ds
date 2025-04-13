package exe03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Digite a ação que deseje realizar: ");
            System.out.println("1 - Listar os assentos");
            System.out.println("2 - Reservar um assento");
            System.out.println("3 - Cancelar a reserva");
            System.out.println("4 - Sair");
            Integer action = scanner.nextInt();
            scanner.nextLine();

            if (action == 1) {
                showSeats();
            } else if (action == 2) {
                System.out.println("Digite o nome do assento que deseja reservar: ");
                String seatName = scanner.nextLine();
                putSeat(seatName);
            } else if (action == 3) {
                System.out.println("Digite o nome do assento que deseja cancelar: ");
                String seatName = scanner.nextLine();
                removeSeat(seatName);

            } else if (action == 4) {
                break;
            }
        }
        scanner.close();
        
    }

    private static void putSeat(String seatName) {
        Map<String, List<Seat>> seats = readJSON();

        if(seats != null) {
            for (Map.Entry<String, List<Seat>> entry : seats.entrySet()) {
                List<Seat> seatList = entry.getValue();
                for (Seat seat : seatList) {
                    if (seat.name.equals(seatName)) {
                        if (seat.isAvailable) {
                            seat.isAvailable = false;
                            System.out.println("Assento " + seatName + " reservado com sucesso.");
                        } else {
                            System.out.println("Assento " + seatName + " já está ocupado.");
                        }
                        break;
                    }
                }
            }
            // Salvar as alterações de volta no arquivo JSON
            ObjectMapper mapper = new ObjectMapper();
            try {
                File file = Paths.get("Atividade02/exe03/Seats.json").toFile();
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, seats);
                System.out.println("Assento " + seatName + " reservado!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo JSON: " + e.getMessage());
            }
        }


    }

    private static void removeSeat(String seatName) {
        Map<String, List<Seat>> seats = readJSON();

        if(seats != null) {
            for (Map.Entry<String, List<Seat>> entry : seats.entrySet()) {
                List<Seat> seatList = entry.getValue();
                for (Seat seat : seatList) {
                    if (seat.name.equals(seatName)) {
                        if (!seat.isAvailable) {
                            seat.isAvailable = true;
                            System.out.println("Assento " + seatName + " cancelado com sucesso.");
                        } else {
                            System.out.println("Assento " + seatName + " não está reservado.");
                        }
                        break;
                    }
                }
            }
            // Salvar as alterações de volta no arquivo JSON
            ObjectMapper mapper = new ObjectMapper();
            try {
                File file = Paths.get("Atividade02/exe03/Seats.json").toFile();
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, seats);
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo JSON: " + e.getMessage());
            }
        }


    }

    private static void showSeats() {
         // Ler o JSON e mapear para a estrutura correta
         Map<String, List<Seat>> seats = readJSON();

         if (seats != null) {
             // Exibir os assentos organizados por linha
             for (Map.Entry<String, List<Seat>> entry : seats.entrySet()) {
                 for (Seat seat : entry.getValue()) {
                     if (seat.isAvailable) {
                         System.out.print(seat.name + " ");
                     } else {
                         System.out.print("## ");
                     }
                 }
                 System.out.println();
             }
             System.out.println();
         }
    }

    private static Map<String, List<Seat>> readJSON() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("Atividade02/exe03/Seats.json");

        if (!file.exists()) {
            System.out.println("Arquivo não encontrado.");
            return null;
        }

        Map<String, List<Seat>> seats = null;
        try {
            // Mapear o JSON diretamente para um Map<String, List<Seat>>
            seats = mapper.readValue(file, new TypeReference<Map<String, List<Seat>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seats;
    }
}

class Seat {
    public String name;
    public Boolean isAvailable;
}