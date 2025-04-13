package exe04;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        // Login
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite seu nome de usuário:");
        String username = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String password = scanner.nextLine();

        User user = Login(username, password);

        if (user.getUsername().equals(username)) {
            while (true) {
                System.out.println("Bem vindo " + user.getUsername() + "!");
                System.out.println("Saldo: " + user.balance);
                System.out.println("Digite a ação que deseje realizar:");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("3 - Listar Transações");
                System.out.println("4 - Sair");
                int action = scanner.nextInt();
                scanner.nextLine();

                if (action == 1) {
                    System.out.println("Informe o valor a ser depositado:");
                    float deposit = scanner.nextFloat();
                    scanner.nextLine();
                    setBalance(deposit, user);
                }
            }
        }
    }

    private static void setBalance(float deposit, User user) {
        user.balance += deposit;

    }

    private static User Login(String username, String password) {
        List<User> users = getJSON();

        User user = null;

        for (User u : users) {
            if ((u.getUsername().equals(username)) && (u.getPassword().equals(password))) {
                System.out.println("Login realizado com Sucesso!");
                System.out.println();
                user = u;
                break;
            } else {
                System.out.println("Nome ou senha incorretos.");
            }
        }
        return user;
    }

    private static List<User> getJSON() {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("Atividade02/exe04/bank.json");

        if (!file.exists()) {
            System.out.println("Arquivo não encontrado");
            return null;
        }

        try {
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
            return users;
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

class User {
    private String username;
    private String password;
    public Integer account;
    public float balance;
    public List<Transactions> transactions;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Transactions {
    public String date;
    public float amount;
}