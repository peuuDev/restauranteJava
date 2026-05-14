import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class mainApp{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random rdm = new Random();
        Restaurante r = new Restaurante();
        int op;
        ArrayList<String> clientesDefault = new ArrayList<>();
        clientesDefault.add("Adriana");
        clientesDefault.add("Erick");
        clientesDefault.add("Júlia");
        clientesDefault.add("Eduardo");
        clientesDefault.add("Fátima");
        clientesDefault.add("João");
        clientesDefault.add("Carol");
        clientesDefault.add("Elton");
        clientesDefault.add("Cris");
        clientesDefault.add("Ricardo");
        clientesDefault.add("Vinicius");
        clientesDefault.add("Emily");
        // Cria 12 mesas no total
        for (int i = 1; i <= 12; i++) {
            int capacidadeMesa = rdm.nextInt(6) * 2 + 2; // 2, 4, 6, 8, 10 ou 12
            Mesa mesa = new Mesa(i, capacidadeMesa);
            r.adicionarMesa(mesa);
        }

        String[] horariosDefault = {
            "20:00", "18:30", "11:45", "10:50", "17:15", "21:20", "18:45"
        };

        int numClientesDefault = rdm.nextInt(12) + 1; // de 1 a 12 clientes random
        for (int i = 0; i < numClientesDefault; i++) {
            int indexNome = rdm.nextInt(clientesDefault.size());
            String nomeClienteDefault = clientesDefault.remove(indexNome);
            Cliente clienteDefault = new Cliente(nomeClienteDefault);
            String horarioRandom = horariosDefault[rdm.nextInt(horariosDefault.length)];

            ArrayList<Mesa> mesasDisponiveis = new ArrayList<>();
            for (Mesa mesa : r.mesas) {
                if (!mesa.isReservada()) {
                    mesasDisponiveis.add(mesa);
                }
            }
            if (mesasDisponiveis.isEmpty()) {
                break;
            }
            Mesa mesaDefault = mesasDisponiveis.get(rdm.nextInt(mesasDisponiveis.size()));
            mesaDefault.reservar();
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