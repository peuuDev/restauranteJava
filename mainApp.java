public class mainApp{

    public static void main(String[] args){
        Restaurante r = new Restaurante();
        
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
        
        r.fazerReserva();
    }
}