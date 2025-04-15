package exe05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Digite a ação que deseje fazer:");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar contato pelo nome");
            System.out.println("4 - sair");
            int action = scanner.nextInt();
            scanner.nextLine();

            if (action == 1) {
                System.out.println("Informe o nome do contato:");
                String name = scanner.nextLine();
                System.out.println("Informe o telefone do contato no seguinte modelo (xx)9xxxx-xxxx:");
                String phoneNumber = scanner.nextLine();
                System.out.println("Informe o email do contato:");
                String email = scanner.nextLine();

                Contact contact = new Contact();
                contact.name = name;
                contact.email = email;
                contact.phoneNumber = phoneNumber;
                addContact(contact);
            } else if (action == 2) {
                getContacts();

            } else if (action == 3) {
                System.out.println("Informe o nome do contato:");
                String name = scanner.nextLine();
                getContact(name);

            } else if (action == 4) {
                System.out.println("Saindo...");
                scanner.close();
                break;
            }
        }
        scanner.close();
    }

    private static void getContact(String name) {
        List<Contact> contacts = getJSON();

        if (contacts == null) {
            System.out.println("Nenhum contato encontrado.");
        }

        int findContact = 0;

        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                System.out.println("Contato encontrado:");
                System.out.println(contact.name + " - " + contact.phoneNumber + " - " + contact.email);
                findContact += 1;
                break;
            }
        }
        if (findContact != 1) {
            System.out.println("Contato não encontrado.");
        }
        findContact = 0;
        System.out.println();

    }

    private static void getContacts() {
        List<Contact> contacts = getJSON();

        if (contacts == null) {
            System.out.println("Nenhum contato encontrado.");
        }

        System.out.println("Contatos:");
        for (Contact contact : contacts) {
            System.out.println(contact.name + " - " + contact.phoneNumber + " - " + contact.email);
        }
        System.out.println();
    }

    private static void addContact(Contact contact) {
        List<Contact> contacts = getJSON();

        contacts.add(contact);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = Paths.get("Atividade02/exe05/contacts.json").toFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, contacts);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<Contact> getJSON() {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("Atividade02/exe05/contacts.json");

        if (!file.exists()) {
            System.out.println("Arquivo não encontrado");
            return null;
        }

        try {
            List<Contact> contacts = objectMapper.readValue(file, new TypeReference<List<Contact>>() {
            });
            return contacts;
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

class Contact {
    public String name;
    public String phoneNumber;
    public String email;
}