import com.marruky.garage.model.Cliente;
import com.marruky.garage.model.Mecanico;
import com.marruky.garage.model.Peca;

public class Main{
    public static void main(String[] args){
        Cliente c1 = new Cliente(1,"Joao Silva", "913124786", "joao@gmail.com");
        Cliente c2 = new Cliente(2,"Elder Souza", "938958481", "marruky@gmail.com");

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

        try{
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
        try{
            Peca pInvalida = new Peca(3, "iib", "algo", 10,-5);
        }catch (IllegalArgumentException e){
            System.out.println("Erro (stock negativo): " + e.getMessage());
        }

        try{
            Peca pInvalida2 = new Peca(4, "", "olha", 2,5);
        }catch(IllegalArgumentException e){
            System.out.println("Erro(referência vazia): " + e.getMessage());
        }

    }
}