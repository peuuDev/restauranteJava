public class Reserva {
    private Cliente cliente;
    private Mesa mesa;
    private String horario;

    public Reserva(Cliente cliente, Mesa mesa, String horario){
        this.cliente = cliente;
        this.mesa = mesa;
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public String getHorario() {
        return horario;
    }

    public String listaReservas(int indice){
        return indice + "º Cliente: " + cliente.getNome() + "\nMesa: " + mesa.getNumeroMesa() + "\nHorário: " + this.getHorario() + "\n------------";
    }

    public String toString(){
        return "Esta é a sua reserva:\nCliente: " + cliente.getNome() + "\nMesa: " + mesa.getNumeroMesa() + "\nHorário: " + this.getHorario();
    }
}
