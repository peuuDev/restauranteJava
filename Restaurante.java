import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    Scanner sc = new Scanner(System.in);
    public ArrayList <Mesa> mesas = new ArrayList<>();
    public ArrayList <Reserva> reservas = new ArrayList<>();
    public ArrayList <Mesa> mesasDisponiveis = new ArrayList<>();

    public Restaurante() {
    mesas = new ArrayList<>();
    reservas = new ArrayList<>();
    }   

    public void cancelarReserva(Cliente cliente){
        ArrayList <Integer> indiceReserva = new ArrayList<>(); // lista pra armazenar os indices das reservas com o nome do cliente
        int countReservas = 0; //reservas feitas no nome do cliente 
        for(Reserva reserva : reservas){
            if(reserva.getCliente() == cliente) {
                countReservas++;
                indiceReserva.add(reservas.indexOf(reserva));
            }
        }
        if (countReservas == 1) {
            System.out.println("Esta é a sua única reserva. Deseja mesmo cancelar?\n[1] Sim | [0] Não");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    reservas.get(indiceReserva.get(0)).getMesa().liberar();
                    reservas.remove((int)indiceReserva.get(0));
                    System.out.println("Reserva cancelada");
                    break;
                    default:
                        break;
                    }
                }else if (countReservas > 1) {
                    System.out.println("Suas reservas:");
                    verReservasCancel(cliente);
                    System.out.println("Digite o número da mesa que deseja cancelar a reserva: ");
                    int n = sc.nextInt();
                    ArrayList<Reserva> reservasParaRemover = new ArrayList<>();
                    for(Reserva reserva : reservas){
                        if(reserva.getMesa().getNumero() == n && reserva.getCliente() == cliente){
                            reservasParaRemover.add(reserva);
                        }
                    }//foram gerados dois foreach para que nao ocasionasse em ConcurrentModificationException(erro onde um item é removido do array enquanto o for esta sendo operado)
                    for(Reserva reserva : reservasParaRemover){
                        reserva.getMesa().liberar();
                        reservas.remove(reserva);
                        System.out.println("Reserva da mesa " + reserva.getMesa().getNumero() + " cancelada!");
                    }
                }else{
                    System.out.println("Não há reservas pra cancelar em seu nome.");
                }
        indiceReserva.clear();
    }

    public void verReservasCancel(Cliente cliente){
        for(Reserva reserva : reservas){
            if(reserva.getCliente() == cliente){
                System.out.println(reserva.toString());
            }
        }
    }

    public void verReservas(){ 
        for(int i = 0; i < reservas.size(); i++){
            System.out.println(reservas.get(i).listaReservas(i + 1));           
            //checa a posição i do array reservas e chama o listaReservas passando o indice de i como parametro 
            // e somando 1 pra acessar o valor real da posicao daquele array
        }
    }

    public void adicionarMesa(Mesa mesa){
        mesas.add(mesa);
    }

    public void listarMesas(){
        for(Mesa mesa : mesas){
            System.out.println(mesa.toString());
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

    public void fazerReserva(Cliente cliente){
        int m, q, count = 0;
        System.out.println("Deseja uma mesa para quantas pessoas, " + cliente.getNome() + "?");
        q = sc.nextInt();
        for(Mesa mesa : mesas){
            if(!mesa.isReservada() && mesa.getCapacidade() >= q){               //verifica se a mesa está reservada e se a capacidade dela é maior ou igual ao valor dado pelo usuario
                mesasDisponiveis.add(mesa);
                count++;
            }
        }
        Mesa mesaSelecionada = null;
        if (count == 1) {
            mesaSelecionada = mesasDisponiveis.get(0);
            System.out.println("Esta é a sua mesa: " + mesaSelecionada.toString());
            System.out.println("Que horário deseja marcar a sua reserva?(XX:XX): ");
            sc.nextLine();
            String h = sc.nextLine();
            Reserva reserva = new Reserva(cliente, mesaSelecionada, h);
            mesaSelecionada.reservar();  
            reservas.add(reserva);
            System.out.println("Tudo certo! Essa é sua reserva: " + reserva.toString());
            mesasDisponiveis.clear();
        } else if (count > 1) {
            System.out.println("Mesas disponíveis: ");
            for(Mesa mesa : mesasDisponiveis){
                System.out.println(mesa.toString());
            }
            System.out.println("Digite o número da mesa que deseja: ");
            m = sc.nextInt();
            sc.nextLine();
            for(Mesa mesa : mesasDisponiveis){
                if(mesa.getNumeroMesa() == m){
                    mesaSelecionada = mesa;
                    break;
                }
            }
            System.out.println("Que horário deseja marcar a sua reserva?(XX:XX): ");
            String h = sc.nextLine();
            Reserva reserva = new Reserva(cliente, mesaSelecionada, h);
            mesaSelecionada.reservar();  // Marca como reservada
            reservas.add(reserva);
            System.out.println("Tudo certo! Essa é sua reserva: " + reserva.toString());
            mesasDisponiveis.clear();
        } else {
            System.out.println("Nenhuma mesa disponível para " + q + " pessoas.");
            return;
        }
    }
}
