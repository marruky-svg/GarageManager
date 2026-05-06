import com.marruky.garage.model.Cliente;
import com.marruky.garage.model.Mecanico;
import com.marruky.garage.model.Peca;
import com.marruky.garage.enums.EstadoReparacao;
import com.marruky.garage.model.Veiculo;
import com.marruky.garage.model.Carro;
import com.marruky.garage.model.Moto;
import com.marruky.garage.model.Caminhao;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Cliente c1 = new Cliente(1, "Joao Silva", "913124786", "joao@gmail.com");
        Cliente c2 = new Cliente(2, "Elder Souza", "938958481", "marruky@gmail.com");

        System.out.println(c1);
        System.out.println(c2);

        System.out.println("Nome do cliente: " + c1.getNome());
        System.out.println("Nome do cliente: " + c2.getNome());

        c1.setNome("João Silva Medeiros");
        System.out.println(c1.getNome());

        System.out.println("\n--- Mecânicos ---");
        Mecanico m1 = new Mecanico(1, "Pedro Costa", "Mecânica geral", 15.5);
        Mecanico m2 = new Mecanico(2, "Ana Ferreira", "Eletricidade", 18.0);
        System.out.println(m1);
        System.out.println(m2);

        try {
            Mecanico mInvalido = new Mecanico(3, "Teste", "teste", -10);
        } catch (Exception e) {
            System.out.println("Erro encontrado: " + e.getMessage());
        }

        System.out.println("\n--- Peças ---");
        Peca p1 = new Peca(1, "FILT-OIL-001", "Filtro de óleo", 12.50, 20);
        Peca p2 = new Peca(2, "PASTL-FRT-MX5", "Pastilhas de travão", 45.00, 8);
        System.out.println(p1);
        System.out.println(p2);

        System.out.println("Preço sem IVA: " + p1.getPrecoUnitario() + "€");
        System.out.println("Preço com IVA: " + p1.calcularPrecoComIVA() + "€");

        //TESTE DE VALIÇÃO
        try {
            Peca pInvalida = new Peca(3, "iib", "algo", 10, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro (stock negativo): " + e.getMessage());
        }

        try {
            Peca pInvalida2 = new Peca(4, "", "olha", 2, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro(referência vazia): " + e.getMessage());
        }

        EstadoReparacao estado = EstadoReparacao.ABERTA;
        System.out.println("Estado atual: " + estado);

        estado = EstadoReparacao.EM_CURSO;
        System.out.println("Novo estado: " + estado);

        switch (estado) {
            case ABERTA -> System.out.println("Reparação ainda não começou");
            case EM_CURSO -> System.out.println("Mecânico a trabalhar");
            case CONCLUIDA -> System.out.println("Pronta para faturar");
            case FATURADA -> System.out.println("Cliente já pagou");
        }

        System.out.println("==== TODOS OS ESTADOS POSSÍVEIS ====");
        for (EstadoReparacao e : EstadoReparacao.values())
            System.out.println("- " + e);

        System.out.println("\n=== POLIMORFISMO COM VEÍCULOS ===");

        // 1. Tentar criar um Veiculo diretamente — vai dar ERRO de compilação
        // Veiculo v = new Veiculo("AA-12-BB", "Toyota", "Corolla", 2020);  // descomenta para ver o erro

        // 2. Criar instâncias concretas
        Carro carro1 = new Carro("AA-12-BB", "Toyota", "Corolla", 2020, 5);
        Moto moto1 = new Moto("MO-23-CC", "Honda", "CB 500", 2022, 500);
        Caminhao caminhao1 = new Caminhao("CC-34-DD", "Volvo", "FH 460", 2019, 12000.0, 4);

        // 3. Lista heterogénea de Veiculo
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(carro1);
        veiculos.add(moto1);
        veiculos.add(caminhao1);

        // 4. Iterar polimorficamente — cada objeto chama o SEU calcularTaxaInspecao
        System.out.println("\n--- Taxas de Inspeção ---");
        for (Veiculo v : veiculos) {
            System.out.println(v.descricaoBase() + " → " + v.calcularTaxaInspecao() + "€");
        }

// 5. toString() polimórfico — cada um imprime o seu formato próprio
        System.out.println("\n--- Detalhes Completos ---");
        for (Veiculo v : veiculos) {
            System.out.println(v);
        }

// 6. Calcular total de taxas de inspeção (lógica que não precisa saber o tipo)
        double totalInspecoes = 0;
        for (Veiculo v : veiculos) {
            totalInspecoes += v.calcularTaxaInspecao();
        }
        System.out.println("\n--- Receita Total de Inspeções ---");
        System.out.println("Total: " + totalInspecoes + "€");

    }
}




