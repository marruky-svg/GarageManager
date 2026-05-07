import com.marruky.garage.model.Cliente;
import com.marruky.garage.model.Mecanico;
import com.marruky.garage.model.Peca;
import com.marruky.garage.model.Veiculo;
import com.marruky.garage.model.Carro;
import com.marruky.garage.model.Moto;
import com.marruky.garage.model.Caminhao;
import com.marruky.garage.model.Reparacao;
import com.marruky.garage.enums.EstadoReparacao;
import com.marruky.garage.interfaces.Faturavel;
import com.marruky.garage.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main {

    public static void main(String[] args) {
        testarEntidadesBase();
        testarEnumEstadoReparacao();
        testarPolimorfismoVeiculos();
        testarCicloReparacao();
        testarPolimorfismoFaturavel();
        testarRepositorioClientes();
    }

    // ==========================================================================
    // 1. ENTIDADES BASE — Cliente, Mecanico, Peca
    // ==========================================================================
    private static void testarEntidadesBase() {
        System.out.println("\n=== ENTIDADES BASE (Cliente, Mecanico, Peca) ===");

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

        try {
            Peca pInvalida = new Peca(3, "iib", "algo", 10, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro (stock negativo): " + e.getMessage());
        }

        try {
            Peca pInvalida2 = new Peca(4, "", "olha", 2, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro (referência vazia): " + e.getMessage());
        }
    }

    // ==========================================================================
    // 2. ENUM ESTADO REPARACAO
    // ==========================================================================
    private static void testarEnumEstadoReparacao() {
        System.out.println("\n=== ENUM ESTADO DE REPARAÇÃO ===");

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

        System.out.println("\n==== TODOS OS ESTADOS POSSÍVEIS ====");
        for (EstadoReparacao e : EstadoReparacao.values()) {
            System.out.println("- " + e);
        }
    }

    // ==========================================================================
    // 3. POLIMORFISMO COM VEICULOS
    // ==========================================================================
    private static void testarPolimorfismoVeiculos() {
        System.out.println("\n=== POLIMORFISMO COM VEÍCULOS ===");

        Carro carro1 = new Carro("AA-12-BB", "Toyota", "Corolla", 2020, 5);
        Moto moto1 = new Moto("MO-23-CC", "Honda", "CB 500", 2022, 500);
        Caminhao caminhao1 = new Caminhao("CC-34-DD", "Volvo", "FH 460", 2019, 12000.0, 4);

        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(carro1);
        veiculos.add(moto1);
        veiculos.add(caminhao1);

        System.out.println("\n--- Taxas de Inspeção ---");
        for (Veiculo v : veiculos) {
            System.out.println(v.descricaoBase() + " → " + v.calcularTaxaInspecao() + "€");
        }

        System.out.println("\n--- Detalhes Completos ---");
        for (Veiculo v : veiculos) {
            System.out.println(v);
        }

        double totalInspecoes = 0;
        for (Veiculo v : veiculos) {
            totalInspecoes += v.calcularTaxaInspecao();
        }
        System.out.println("\n--- Receita Total de Inspeções ---");
        System.out.println("Total: " + totalInspecoes + "€");
    }

    // ==========================================================================
    // 4. CICLO COMPLETO DE REPARACAO
    // ==========================================================================
    private static Reparacao testarCicloReparacao() {
        System.out.println("\n=== CICLO COMPLETO DE UMA REPARAÇÃO ===");

        Cliente clienteRep = new Cliente(10, "António Magalhães", "938958481", "antonio@gmail.com");
        Carro carroRep = new Carro("BB-99-CC", "Renault", "Clio", 2010, 5);
        Mecanico mecanicoRep = new Mecanico(10, "João Mendes", "Mecânica Geral", 20.0);

        Peca pFiltro = new Peca(10, "FILT-AR-RC1", "Filtro de ar", 18.50, 15);
        Peca pVelas = new Peca(12, "VELAS-NGK4", "Jogo velas NGK", 32.00, 12);

        Reparacao rep = new Reparacao(100, clienteRep, carroRep, mecanicoRep, 0);
        System.out.println("Reparação criada (estado inicial):");
        System.out.println(" " + rep);
        System.out.println(" Estado: " + rep.getEstado());
        System.out.println(" Data abertura: " + rep.getDataAbertura());

        System.out.println("\n--- Adicionando Peças ---");
        rep.adicionarPeca(pFiltro);
        System.out.println("Peças totais: " + rep.getPecas().size());

        System.out.println("\n--- iniciarTrabalho() ---");
        rep.iniciarTrabalho();
        System.out.println("Novo estado: " + rep.getEstado());

        System.out.println("\n--- Adicionando peça (estado EM_CURSO) ---");
        rep.adicionarPeca(pVelas);
        System.out.println("Peças totais: " + rep.getPecas().size());

        rep.setHorasTrabalho(3.0);
        System.out.println("\nHoras de trabalho registadas: " + rep.getHorasTrabalho() + "h");

        System.out.println("\n--- Cálculo de preço (em curso) ---");
        System.out.println("Mão-de-obra: " + (rep.getHorasTrabalho() * mecanicoRep.getPrecoHora()) + "€");
        System.out.println("Peças (com IVA): " + (rep.calcularPrecoTotal() - rep.getHorasTrabalho() * mecanicoRep.getPrecoHora()) + "€");
        System.out.println("TOTAL: " + rep.calcularPrecoTotal() + "€");

        System.out.println("\n--- concluirTrabalho() ---");
        rep.concluirTrabalho();
        System.out.println("Novo estado: " + rep.getEstado());
        System.out.println("Data conclusão: " + rep.getDataConclusao());

        System.out.println("\n--- Tentativa inválida: adicionar peça em CONCLUIDA ---");
        try {
            rep.adicionarPeca(new Peca(13, "EXTRA", "Peça extra", 10, 5));
        } catch (IllegalStateException e) {
            System.out.println("Erro apanhado (esperado): " + e.getMessage());
        }

        System.out.println("\n--- Tentativa inválida: concluirTrabalho() em CONCLUIDA ---");
        try {
            rep.concluirTrabalho();
        } catch (IllegalStateException e) {
            System.out.println("Erro apanhado (esperado): " + e.getMessage());
        }

        System.out.println("\n--- faturar() ---");
        rep.faturar();
        System.out.println("Estado final: " + rep.getEstado());

        System.out.println("\n--- Tentativa inválida: faturar() em FATURADA ---");
        try {
            rep.faturar();
        } catch (IllegalStateException e) {
            System.out.println("Erro apanhado (esperado): " + e.getMessage());
        }

        System.out.println("\n=== FATURA SIMULADA ===");
        System.out.println("Reparação #" + rep.getId());
        System.out.println("Cliente: " + rep.getCliente().getNome());
        System.out.println("Veículo: " + rep.getVeiculo().descricaoBase());
        System.out.println("Mecânico: " + rep.getMecanico().getNome() + " (" + mecanicoRep.getPrecoHora() + "€/h)");
        System.out.println("Horas: " + rep.getHorasTrabalho() + "h");
        System.out.println("Peças aplicadas: " + rep.getPecas().size());
        for (Peca p : rep.getPecas()) {
            System.out.println("  - " + p.getNome() + ": " + p.calcularPrecoComIVA() + "€ (com IVA)");
        }
        System.out.println("---------------------------------");
        System.out.println("TOTAL A PAGAR: " + rep.calcularPrecoTotal() + "€");
        System.out.println("Estado: " + rep.getEstado());

        return rep;  // ← DEVOLVE para o método seguinte usar
    }

    // ==========================================================================
    // 5. POLIMORFISMO COM INTERFACE FATURAVEL
    // ==========================================================================
    private static void testarPolimorfismoFaturavel() {
        System.out.println("\n=== POLIMORFISMO COM INTERFACE FATURAVEL ===");

        // Cria uma reparação rapidamente para este teste
        Cliente cli = new Cliente(20, "Cliente Teste", "910000000", "teste@example.pt");
        Carro car = new Carro("XX-99-XX", "Teste", "Modelo", 2020, 5);
        Mecanico mec = new Mecanico(20, "Mecanico Teste", "Geral", 15.0);
        Reparacao repFat = new Reparacao(200, cli, car, mec, 2.0);
        repFat.adicionarPeca(new Peca(20, "REF-001", "Peça teste", 50.0, 10));
        repFat.iniciarTrabalho();
        repFat.concluirTrabalho();

        Faturavel itemFaturavel = repFat;

        System.out.println("Número da fatura: " + itemFaturavel.getNumeroFatura());
        System.out.println("Total: " + itemFaturavel.calcularTotal() + "€");
        System.out.println("Pode ser faturado? " + itemFaturavel.podeSerFaturado());

        System.out.println("IVA aplicado: " + (Faturavel.IVA_NORMAL * 100) + "%");

        List<Faturavel> documentosFaturaveis = new ArrayList<>();
        documentosFaturaveis.add(repFat);

        System.out.println("\n--- Iteração polimórfica de documentos ---");
        double receitaTotal = 0;
        for (Faturavel doc : documentosFaturaveis) {
            System.out.println("- " + doc.getNumeroFatura() + ": " + doc.calcularTotal() + "€");
            receitaTotal += doc.calcularTotal();
        }
        System.out.println("Receita total: " + receitaTotal + "€");

        System.out.println("\n--- A mesma Reparacao vista por 2 contratos diferentes ---");
        System.out.println("Como Reparacao (toString completo):");
        System.out.println("  " + repFat);
        System.out.println("Como Faturavel (só o que a interface expõe):");
        System.out.println("  Número: " + itemFaturavel.getNumeroFatura());
        System.out.println("  Total: " + itemFaturavel.calcularTotal() + "€");
    }

    // ==========================================================================
    // 6. REPOSITORIO DE CLIENTES
    // ==========================================================================
    private static void testarRepositorioClientes() {
        System.out.println("\n=== REPOSITORIO DE CLIENTES ===");

        ClienteRepository clienteRepo = new ClienteRepository();
        System.out.println("Repositório criado. Total de clientes: " + clienteRepo.contar());

        clienteRepo.adicionar(new Cliente(101, "Maria Silva", "910000001", "maria@example.pt"));
        clienteRepo.adicionar(new Cliente(102, "Carlos Pereira", "910000002", "carlos@example.pt"));
        clienteRepo.adicionar(new Cliente(103, "Beatriz Costa", "910000003", "beatriz@example.pt"));
        System.out.println("Após adicionar 3 clientes. Total: " + clienteRepo.contar());

        System.out.println("\n--- Tentativa de duplicado ---");
        try {
            clienteRepo.adicionar(new Cliente(101, "Outro Maria", "999999999", "outra@email.pt"));
        } catch (IllegalStateException e) {
            System.out.println("Erro apanhado (esperado): " + e.getMessage());
        }

        System.out.println("\n--- Tentativa de null ---");
        try {
            clienteRepo.adicionar(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro apanhado (esperado): " + e.getMessage());
        }

        System.out.println("\n--- Encontrar id 102 ---");
        Optional<Cliente> achado = clienteRepo.encontrarPorId(102);
        if (achado.isPresent()) {
            System.out.println("Cliente encontrado: " + achado.get().getNome());
        } else {
            System.out.println("Cliente não encontrado");
        }

        System.out.println("\n--- Encontrar id 999 (não existe) ---");
        Optional<Cliente> naoAchado = clienteRepo.encontrarPorId(999);
        if (naoAchado.isPresent()) {
            System.out.println("Cliente: " + naoAchado.get().getNome());
        } else {
            System.out.println("Cliente não encontrado (Optional vazio)");
        }

        System.out.println("\n--- Encontrar id 999 com orElse ---");
        String nomeCliente = clienteRepo.encontrarPorId(999)
                .map(Cliente::getNome)
                .orElse("Desconhecido");
        System.out.println("Nome retornado: " + nomeCliente);

        System.out.println("\n--- Listar todos ---");
        List<Cliente> todos = clienteRepo.listarTodos();
        for (Cliente c : todos) {
            System.out.println("  - " + c);
        }

        System.out.println("\n--- Remover id 102 ---");
        boolean removeu1 = clienteRepo.remover(102);
        System.out.println("Removeu? " + removeu1 + " | Total agora: " + clienteRepo.contar());

        System.out.println("\n--- Remover id 999 (não existe) ---");
        boolean removeu2 = clienteRepo.remover(999);
        System.out.println("Removeu? " + removeu2 + " | Total agora: " + clienteRepo.contar());

        System.out.println("\n--- Estado final do repositório ---");
        clienteRepo.listarTodos().forEach(c -> System.out.println("  - " + c.getNome()));
    }
}

