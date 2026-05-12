public class Mesa {
    private int numero;
    private int capacidade;
    private boolean reservada;
    
    public Mesa(int numero, int capacidade) {
    this.numero = numero;
    this.capacidade = capacidade;
    this.reservada = false;
}

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getNumeroMesa() {
        return numero;
    }

    public boolean isReservada() {
        return reservada;
    }

    public String toString(){
        return "Número da mesa: " + numero + " | Capacidade: " + capacidade + " pessoas | Reservada?: " + (this.reservada ? "Sim\n" : "Não\n");
    }

    public void reservar(){
        this.reservada = true;
    }

    public void liberar(){
        this.reservada = false;
    }
}
