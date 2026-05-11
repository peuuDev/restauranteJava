import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    Scanner sc = new Scanner(System.in);
    private ArrayList <Mesa> mesas = new ArrayList<>();
    private ArrayList <Reserva> reservas = new ArrayList<>();

    public Restaurante() {
    mesas = new ArrayList<>();
    reservas = new ArrayList<>();
}

    public void adicionarMesa(Mesa mesa){
        mesas.add(mesa);
    }

    public void listarMesas(){
        for(Mesa mesa : mesas){
            mesa.toString();
        }
    }

    public boolean verificarMesas(int numMesa){
        for(Mesa mesa : mesas){
            if (mesa.getNumeroMesa() == numMesa) {
                if (mesa.isReservada()) {
                    System.out.println("Mesa " + numMesa + " já possui uma reserva.");
                    return false;
                } else {
                    System.out.println("Mesa " + numMesa + " está disponível.");
                    mesa.reservar();
                    return true;
                }
            }
        }
        System.out.println("Mesa " + numMesa + " não encontrada.");
        return false;
    }

    public void fazerReserva(){
        System.out.println("Olá!\nDigite o seu nome para reserva: ");
        String n = sc.nextLine();
        Cliente p = new Cliente(n);
        int m, q;
        boolean mesaDisponivel = false;
        System.out.println("Bem-Vindo, " + n + "!");
        do {
            System.out.println("Digite o número da mesa que deseja: ");
            m = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha
            mesaDisponivel = verificarMesas(m);
        } while (!mesaDisponivel);
        System.out.println("Para quantas pessoas será a mesa?: ");
        q = sc.nextInt();
        sc.nextLine();
        Mesa mesa = new Mesa(m, q);
        System.out.println("Que horário deseja marcar a sua reserva?(XX:XX): ");
        String h = sc.nextLine();

        Reserva reserva = new Reserva(p, mesa, h);
        System.out.println("Tudo certo! " + reserva.toString());
        reservas.add(reserva);
    }
}
