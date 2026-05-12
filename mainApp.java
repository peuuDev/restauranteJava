import java.util.Scanner;

public class mainApp{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Restaurante r = new Restaurante();
        Cliente cliente = new Cliente("Pedro");
        int op;
        // Dados das mesas: [número, capacidade]
        int[][] mesasData = {
            {1, 4},
            {2, 4},
            {3, 8},
            {4, 2},
            {5, 6}
        };
        
        for (int[] data : mesasData) {
            Mesa mesa = new Mesa(data[0], data[1]);
            r.adicionarMesa(mesa);
        }
        
        System.out.println("Bem-Vindo ao Restaurante Java!");
        do{
            System.out.println("Menu:" +
            "\n1 - Ver mesas" +
            "\n2 - Fazer reserva" +
            "\n3 - Cancelar reserva" +
            "\n4 - Ver reservas" +
            "\n0 - Sair");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    r.listarMesas();
                    break;
                case 2:
                    r.fazerReserva(cliente);
                    break;
                case 3:
                    break;
                case 4:
                    if (r.reservas.isEmpty()) {
                        System.out.println("Não há reservas");
                    } else {
                        r.verReservas();
                    }
                case 0:
                    break;
            }
        }while(op != 0);
    }
}