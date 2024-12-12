package controlador;

import Vista.CalculadoraVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.IPv4Address;

public class ControladorCalculadora {
     private CalculadoraVista vista;

    public ControladorCalculadora(CalculadoraVista vista) {
        this.vista = vista;
        


        // Añadir listener al botón
        this.vista.jButtonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        try {
            // Obtener valores de la vista
            String ip = vista.jTextFieldIP.getText();
            String mascara = vista.jTextFieldMascara.getText();

            // Verificar que la máscara esté en el formato CIDR y separar
            int mascaraInt = 0;
            if (mascara.contains("/")) {
                String[] partes = mascara.split("/");
                ip = partes[0];
                mascara = partes[1];
                mascaraInt = Integer.parseInt(mascara);  // Convierto la máscara a int
            } else {
                mascaraInt = Integer.parseInt(mascara); // Si ya está como un número, lo convierto
            }

            // Crear modelo con los datos ingresados (IP y máscara como String y máscara como int)
            IPv4Address direccion = new IPv4Address(ip, mascaraInt);

            // Mostrar los resultados en el área de texto
            String resultados = String.format(
                "Dirección IP: %s (%s)\n" +
                "Máscara de red: %s (%s)\n" +
                "Dirección de red: %s\n" +
                "Dirección de broadcast: %s\n" +
                "Primer host: %s\n" +
                "Último host: %s\n" +
                "Máximo número de hosts: %.0f\n" +
                "Tipo: %s\n",
                direccion.getIPAddress(), direccion.getBinaryAddress(),
                direccion.getDecimalMask(), direccion.getBinaryMask(),
                direccion.getDecimalNetwork(),
                direccion.getDecimalBroadcast(),
                direccion.getDecimalFirstHost(),
                direccion.getDecimalLastHost(),
                direccion.getMaxHosts(),
                direccion.getType()
            );

            vista.jTextArea1.setText(resultados);

        } catch (Exception ex) {
            vista.jTextArea1.setText("Error: " + ex.getMessage());
        }
    }
}
