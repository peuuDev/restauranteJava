import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class mainApp{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random rdm = new Random();
        Restaurante r = new Restaurante();
        int op;
        // Dados das mesas: [número, capacidade]
        int[][] mesasDefault = {
            {1, 4},
            {2, 4},
            {3, 8},
            {4, 2},
            {5, 6},
            {6, 6},
        };
        
        for (int[] data : mesasDefault) {
            Mesa mesa = new Mesa(data[0], data[1]);
            boolean reserva = rdm.nextBoolean();
            if (reserva == true) {

                mesa.reservar();
            }
            r.adicionarMesa(mesa);
        }

        String[] horariosDefault = {
            "20:00", "18:30", "11:45", "10:50", "17:15", "21:20", "18:45"
        };

        String[] clientesDefault = {
            "Adriana", "Erick", "Júlia"
        };

        for(int i = 0; i < clientesDefault.length; i++){
            String nomeClienteDefault = clientesDefault[rdm.nextInt(clientesDefault.length)];
            Cliente clienteDefault = new Cliente(nomeClienteDefault);
            int capacidadeMesa = rdm.nextInt(6 * 2) + 1;      //Gera valores aleatorios, de numeros pares, a partir de 2 até 12
            Mesa mesaDefault = new Mesa(r.mesas.size() + 1, capacidadeMesa);
            mesaDefault.reservar();
            r.adicionarMesa(mesaDefault);
            String horarioRandom = horariosDefault[rdm.nextInt(horariosDefault.length)];
            Reserva reserva = new Reserva(clienteDefault, mesaDefault, horarioRandom);
            r.reservas.add(reserva);
        }
        
        System.out.println("Bem-Vindo ao Restaurante Java!\nDigite o seu nome para começar!:");
        String n = sc.nextLine();
        String nUpper = n.substring(0,1).toUpperCase() + n.substring(1).toLowerCase(); //deixa apenas a primeira letra em upper e o resto em lower
        Cliente cliente = new Cliente(nUpper);
        System.out.println("Olá, " + nUpper + "!\n");
        do{
            System.out.println("Menu:" +
            "\n1 - Ver mesas" +
            "\n2 - Fazer reserva" +
            "\n3 - Cancelar reserva" +
            "\n4 - Ver reservas" +
            "\n0 - Sair");
            
            try {
                op = sc.nextInt();
                switch (op) {
                    case 1:
                        r.listarMesas();
                        break;
                    case 2:
                        r.fazerReserva(cliente);
                        break;
                    case 3:
                        r.cancelarReserva(cliente);
                        break;
                    case 4:
                        if (r.reservas.isEmpty()) {
                            System.out.println("Não há reservas");
                        } else {
                            r.verReservas();
                        }
                    case 0:
                        break;
                    default:
                        System.out.println("Digite um número válido!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine(); // descarta a entrada inválida
                op = -1; // garante que o loop continue
                continue;
            }
        }while(op != 0);
    }
}