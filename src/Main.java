import com.marruky.garage.model.Cliente;
import com.marruky.garage.model.Mecanico;
import com.marruky.garage.model.Peca;
import com.marruky.garage.enums.EstadoReparacao;
import com.marruky.garage.model.Veiculo;
import com.marruky.garage.model.Carro;
import com.marruky.garage.model.Moto;
import com.marruky.garage.model.Caminhao;
import com.marruky.garage.model.Reparacao;

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


        System.out.println("\n==== CICLO COMPLETO DE UMA REPARAÇÃO ====\n");

        //1.PREPARAR OS INTERVENIENTES
        Cliente clienteRep = new Cliente(10, "António Magalhães", "938958481", "antonio@gmail.com");
        Carro carroRep = new Carro("BB-99-CC", "Renault", "Clio", 2010, 5);
        Mecanico mecanicoRep = new Mecanico(10, "João Mendes", "Mecânica Geral", 20.0);

        Peca pFiltro = new Peca(10, "FILT-AR-RC1", "Filtro de ar", 18.50, 15);
        Peca pOleo = new Peca(11, "OLEO-5W30", "Óleo motor 5W30", 45.00, 30);
        Peca pVelas = new Peca(12, "VELAS-NGK4", "Jogo velas NGK", 32.00, 12);

        //2.CRIAR A REPARAÇÃO(ESTADO INICIAL : ABERTA)
        Reparacao rep = new Reparacao(100, clienteRep, carroRep, mecanicoRep, 0);
        System.out.println("Reparação criada (estado inicial): ");
        System.out.println(" " + rep);
        System.out.println(" Estado: " + rep.getEstado());
        System.out.println(" Data abertura: " + rep.getDataAbertura());

        //3.ADICIONAR PEÇAS NO ESTADO ABERTA - PERMITIDO
        System.out.println("\n--- Adicionando Peças ---\n");
        rep.adicionarPeca(pFiltro);
        System.out.println("Peças totais: " + rep.getPecas().size());

        //4.INICIAR TRABALHO
        System.out.println("\n--- iniciarTrabalho() ---\n");
        rep.iniciarTrabalho();
        System.out.println("Novo estado: " + rep.getEstado());

        //5.ADICIONAR UMA PEÇA em EM_CURSO - AINDA PERMITIDO
        System.out.println("\n--- Adicionando peça (estado EM_CURSO) ---\n");
        rep.adicionarPeca(pVelas);
        System.out.println("  Peças totais: " + rep.getPecas().size());

        //6.REGISTRAR HORAS DE TRABALHO (MECANICO TRABALHOU 3H)
        rep.setHorasTrabalho(3.0);
        System.out.println("\n  Horas de trabalho registadas: " + rep.getHorasTrabalho() + "h");

        // 7. Calcular preço total ANTES de concluir (sempre permitido — leitura)
        System.out.println("\n--- Cálculo de preço (em curso) ---");
        System.out.println("  Mão-de-obra: " + (rep.getHorasTrabalho() * mecanicoRep.getPrecoHora()) + "€");
        System.out.println("  Peças (com IVA): " + (rep.calcularPrecoTotal() - rep.getHorasTrabalho() * mecanicoRep.getPrecoHora()) + "€");
        System.out.println("  TOTAL: " + rep.calcularPrecoTotal() + "€");

        // 8. Concluir trabalho — transição EM_CURSO → CONCLUIDA + preenche dataConclusao
        System.out.println("\n--- concluirTrabalho() ---");
        rep.concluirTrabalho();
        System.out.println("  Novo estado: " + rep.getEstado());
        System.out.println("  Data conclusão: " + rep.getDataConclusao());

        // 9. TENTAR adicionar peça depois de CONCLUIDA — deve REJEITAR
        System.out.println("\n--- Tentativa inválida: adicionar peça em CONCLUIDA ---");
        try {
            rep.adicionarPeca(new Peca(13, "EXTRA", "Peça extra", 10, 5));
        } catch (IllegalStateException e) {
            System.out.println("  Erro apanhado (esperado): " + e.getMessage());
        }

        // 10. TENTAR pular estados — concluirTrabalho de novo
        System.out.println("\n--- Tentativa inválida: concluirTrabalho() em CONCLUIDA ---");
        try {
            rep.concluirTrabalho();
        } catch (IllegalStateException e) {
            System.out.println("  Erro apanhado (esperado): " + e.getMessage());
        }

        // 11. Faturar — transição CONCLUIDA → FATURADA
        System.out.println("\n--- faturar() ---");
        rep.faturar();
        System.out.println("  Estado final: " + rep.getEstado());

        // 12. TENTAR algo após FATURADA
        System.out.println("\n--- Tentativa inválida: faturar() de novo em FATURADA ---");
        try {
            rep.faturar();
        } catch (IllegalStateException e) {
            System.out.println("  Erro apanhado (esperado): " + e.getMessage());
        }

        // 13. RESUMO FINAL — fatura simulada
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
        System.out.println("Aberta em: " + rep.getDataAbertura());
        System.out.println("Concluída em: " + rep.getDataConclusao());
    }
}




