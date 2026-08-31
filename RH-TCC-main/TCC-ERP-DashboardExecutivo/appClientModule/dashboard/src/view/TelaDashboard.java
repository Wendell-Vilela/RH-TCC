package view;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class TelaDashboard extends JFrame {

    public TelaDashboard() {

        setTitle("Dashboard Executivo");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(600, 500);

        setBackground(Color.BLACK);

        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        

        List<Double> dadosAbsenteismo = Arrays.asList(
    10.0, 
    15.0, 
    8.0, 
    20.0, 
    12.0, 
    25.0, 
    18.0, 
    22.0, 
    16.0, 
    30.0, 
    19.0, 
    14.0  
);

       
        abas.addTab("Tendência de Absenteismo", new TendenciaAbsenteismo(dadosAbsenteismo));
        abas.addTab("Gestão de Férias", new GestaoFerias());
        abas.addTab("Custo Mensal de Folha", new CustoMensal());
        add(abas);
    }
}