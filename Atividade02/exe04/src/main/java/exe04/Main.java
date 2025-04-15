package exe04;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
                System.out.println("1 - Depositar (+num)");
                System.out.println("2 - Sacar (-num)");
                System.out.println("3 - Listar Transações");
                System.out.println("4 - Sair");
                int action = scanner.nextInt();
                scanner.nextLine();

                if (action == 1) {
                    System.out.println("Informe o valor a ser depositado:");
                    float deposit = scanner.nextFloat();
                    scanner.nextLine();

                    setBalance(deposit, user, true);
                    System.out.println("Depósito realizado com sucesso!");
                } else if (action == 2) {
                    System.out.println("Informe o valor a ser sacado:");
                    float deposit = scanner.nextFloat();
                    scanner.nextLine();

                    if (user.balance < deposit) {
                        System.out.println("Saldo insuficiente!");
                    } else {
                        setBalance(deposit, user, false);
                        System.out.println("Saque realizado com sucesso!");
                    }
                } else if (action == 3) {
                    getTransictions(username);
                } else if (action == 4) {
                    System.out.println("Saindo...");
                    break;
                }
            }
        }
        scanner.close();
    }

    private static void getTransictions(String username) {
        List<User> users = getJSON();

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("Transações:");
                for (Transactions transaction : u.transactions) {
                    System.out.println("Data: " + transaction.date + ", Valor: " + transaction.amount);
                }
                System.out.println();
                break;

            }
        }
    }

    private static void setBalance(float deposit, User user, boolean isDeposit) {
        if (!isDeposit) {
            deposit = -deposit;
        }
        user.balance += deposit;
        Transactions transactions = new Transactions();
        transactions.date = java.time.LocalDate.now().toString();
        transactions.amount = deposit;
        user.transactions.add(transactions);

        List<User> users = getJSON();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                u.balance = user.balance;
                u.transactions = user.transactions;
                break;
            }
        }

        // Inserindo no arquivo JSON

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = Paths.get("Atividade02/exe04/bank.json").toFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
            e.printStackTrace();
        }

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