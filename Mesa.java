public class Mesa {
    private int numero;
    private int capacidade;
    private boolean reservada;
    
    public Mesa(int numero, int capacidade) {
    this.numero = numero;
    this.capacidade = capacidade;
    this.reservada = false;
}

    public int getNumeroMesa() {
        return numero;
    }

    public boolean isReservada() {
        return reservada;
    }

    public String toString(){
        return "Número da mesa: " + numero + "\nCapacidade: " + capacidade + "Reservada?: " + (this.reservada ? "Sim" : "Não");
    }

    public void reservar(){
        this.reservada = true;
    }

    public void liberar(){
        this.reservada = false;
    }
}
