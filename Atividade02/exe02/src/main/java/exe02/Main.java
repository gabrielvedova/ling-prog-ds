package exe02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Para a separação do decimal ser com ponto (" . ")

        System.out.println("Digite a ação que deseje realizar:");
        System.out.println("1 - Adicionar um novo produto");
        System.out.println("2 - Listar o estoque");
        System.out.println("3 - Sair");
        int action = scanner.nextInt();
        scanner.nextLine();

        if (action == 1) {

            // Input do Usuário
            System.out.println("Nome: ");
            String name = scanner.nextLine();
            System.out.println("Quantidade: ");
            Integer quantity = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Preço: ");
            Double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Tipo de Produto: ");
            String typeProduct = scanner.nextLine();

            // Criando um novo item seguindo o modelo de ItemStock

            ItemStock newItem = new ItemStock();
            newItem.name = name;
            newItem.quantity = quantity;
            newItem.price = price;
            newItem.typeProduct = typeProduct;

            // Realizando o processo para adicionar no arquivo JSON
            addItem(newItem);
        } else if (action == 2) {
            try {
                readStock();
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void addItem(ItemStock argStocks) throws IOException {
        List<ItemStock> stock = readJSON();
        stock.add(argStocks);

        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = Paths.get("Atividade02/exe02/Stock.json").toFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, stock);
            System.out.println("Item adicionado ao estoque!");
        } catch (IOException e) {
            System.out.println("Erro ao adicionar o item: " + e.getMessage());
        }
    }

    private static void readStock() throws IOException {

        List<ItemStock> stock = readJSON();

        final float[] totalPrice = {0};
        stock.stream().collect(Collectors.groupingBy(item -> item.typeProduct))
                .forEach((type, items) -> {
                    System.out.println("----------------------------------");
                    System.out.println("Tipo: " + type);
                    for (ItemStock item : items) {
                        System.out.println("Nome: " + item.name + " | Quantidade: " + item.quantity + " | Preço: " + item.price);
                        totalPrice[0] += item.price*item.quantity;
                    }
                });
        System.out.println("Valor total do estoque: R$" + totalPrice[0]);
    }

    private static List<ItemStock> readJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("Atividade02/exe02/Stock.json");
        if (!file.exists()) {
            throw new IOException("Arquivo não encontrado na pasta raiz" );
        }

        List<ItemStock> stock = objectMapper.readValue(file, new TypeReference<List<ItemStock>>(){});
        return stock;
    }

}

class ItemStock {
    public String name;
    public Integer quantity;
    public Double price;
    public String typeProduct;
}