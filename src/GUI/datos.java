package GUI;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class datos extends JFrame{
    private JPanel datosPanel;
    private JLabel titulo;
    private JSlider trabajo_equipo;
    private JSlider analisis_sintesis;
    private JSlider solucion_problemas;
    private JSlider conocimientos_practica;
    private JSlider hab_investigacion;
    private JSlider trajar_autonoma;
    private JSlider diseñar_gestionar;
    private JSlider busqueda_logro;
    private JButton evaluarButton;
    private JButton limpiarButton;
    private JLabel lbTrajo_equipo;
    private JLabel lbAnalisis_sintesis;
    private JLabel lbSolucion_problemas;
    private JLabel lbConocimientos_practica;
    private JLabel lbHab_inves;
    private JLabel lbHab_autonoma;
    private JLabel lbCap_diseñar;
    private JLabel lbBusqueda_logro;

    public datos() {
        setSize(700,500);
        setTitle("Equipo: Alitas");
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarTodo();
            }
        });
        evaluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evaluar();
            }
        });
    }
    private void evaluar(){
        //variables linguisticas en X
        int[] var_en_x=new int[8];
        var_en_x[0]=trabajo_equipo.getValue();
        var_en_x[1]=solucion_problemas.getValue();
        var_en_x[2]=diseñar_gestionar.getValue();
        var_en_x[3]=busqueda_logro.getValue();
        var_en_x[4]=conocimientos_practica.getValue();
        var_en_x[5]=hab_investigacion.getValue();
        var_en_x[6]=trajar_autonoma.getValue();
        var_en_x[7]=analisis_sintesis.getValue();
        for (int i=0; i<8; i++){
            System.out.println("Var ling en x: "+var_en_x[i]);
        }
    }
    private void borrarTodo(){
        trabajo_equipo.setValue(50);
        analisis_sintesis.setValue(50);
        solucion_problemas.setValue(50);
        conocimientos_practica.setValue(50);
        hab_investigacion.setValue(50);
        trajar_autonoma.setValue(50);
        diseñar_gestionar.setValue(50);
        busqueda_logro.setValue(50);
    }
    private void initComponents(){
        datosPanel=new JPanel();
        datosPanel.setLayout(new GridLayout(0,2,5,7));
        datosPanel.setBackground(Color.getHSBColor(65,49,53));
        trabajo_equipo=new JSlider();
        trabajo_equipo.setPaintLabels(true);
        trabajo_equipo.setMajorTickSpacing(10);
        analisis_sintesis=new JSlider();
        analisis_sintesis.setPaintLabels(true);
        analisis_sintesis.setMajorTickSpacing(10);
        solucion_problemas=new JSlider();
        solucion_problemas.setPaintLabels(true);
        solucion_problemas.setMajorTickSpacing(10);
        conocimientos_practica=new JSlider();
        conocimientos_practica.setPaintLabels(true);
        conocimientos_practica.setMajorTickSpacing(10);
        hab_investigacion=new JSlider();
        hab_investigacion.setPaintLabels(true);
        hab_investigacion.setMajorTickSpacing(10);
        trajar_autonoma=new JSlider();
        trajar_autonoma.setPaintLabels(true);
        trajar_autonoma.setMajorTickSpacing(10);
        diseñar_gestionar=new JSlider();
        diseñar_gestionar.setPaintLabels(true);
        diseñar_gestionar.setMajorTickSpacing(10);
        busqueda_logro=new JSlider();
        busqueda_logro.setPaintLabels(true);
        busqueda_logro.setMajorTickSpacing(10);
        evaluarButton=new JButton("Evaluar");
        limpiarButton=new JButton("Borrar");
        titulo=new JLabel("DESARROLLOS INTELIGENTES");
        lbTrajo_equipo=new JLabel("Trabajo en equipo: ");
        lbAnalisis_sintesis=new JLabel("Capacidad Análisis y Síntesis: ");
        lbSolucion_problemas=new JLabel("Solución de problemas: ");
        lbConocimientos_practica=new JLabel("Aplicar conocimientos en Práctica: ");
        lbHab_inves=new JLabel("Habilidad de Investigación: ");
        lbHab_autonoma=new JLabel("Habilidad de trajar forma autónoma: ");
        lbCap_diseñar=new JLabel("Capacidad de diseñar y gestionar proyectos: ");
        lbBusqueda_logro=new JLabel("Búsqueda del logro");

        datosPanel.add(titulo);
        datosPanel.add(new JLabel());
        datosPanel.add(lbTrajo_equipo);
        datosPanel.add(trabajo_equipo);
        datosPanel.add(lbSolucion_problemas);
        datosPanel.add(solucion_problemas);
        datosPanel.add(lbCap_diseñar);
        datosPanel.add(diseñar_gestionar);
        datosPanel.add(lbBusqueda_logro);
        datosPanel.add(busqueda_logro);
        datosPanel.add(lbConocimientos_practica);
        datosPanel.add(conocimientos_practica);
        datosPanel.add(lbHab_inves);
        datosPanel.add(hab_investigacion);
        datosPanel.add(lbHab_autonoma);
        datosPanel.add(trajar_autonoma);
        datosPanel.add(lbAnalisis_sintesis);
        datosPanel.add(analisis_sintesis);

        datosPanel.add(limpiarButton);
        datosPanel.add(evaluarButton);
        this.getContentPane().add(datosPanel);
    }
    public static void main(String[] args){
        datos gui=new datos();
    }
}
