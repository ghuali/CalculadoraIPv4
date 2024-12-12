package calculadorainterfaz;

import Vista.CalculadoraVista;
import controlador.ControladorCalculadora;


public class CalculadoraInterfaz {

    
    public static void main(String[] args) {
       // Crear la vista
        CalculadoraVista vista = new CalculadoraVista();
        vista.setVisible(true);

        // Crear el controlador
        new ControladorCalculadora(vista);
    }
    
}
