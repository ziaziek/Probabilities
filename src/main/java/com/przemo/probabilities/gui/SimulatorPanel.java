/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.probabilities.gui;

import com.przemo.probabilities.gui.simulation.BettingGuiSimulator;
import com.przemo.probabilities.series.simulation.BettingSeriesSimulator;
import com.przemo.probabilities.series.simulation.Simulators;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Przemo
 */
public class SimulatorPanel extends javax.swing.JPanel {
    
    //This should be a part of a separate class called teo-state button, for example
    boolean runOrStop = true;

    private SwingWorker sw;

    private ChartPanel p;

    private Map<Integer, Double> chartData;

    private int simulations;

    private double odds, probability;

    /**
     * Creates new form SimulatorPanel
     */
    public SimulatorPanel() {
        initComponents();
        setupGUI();
    }

    public void simulate() {
        if (runOrStop) {
            getSimulationParameters();

            sw = new BettingGuiSimulator(createSimulator(), probability) {

                @Override
                protected void process(List chunks) { //chunks is a List<Map<Integer.Double>> (account)
                    chartData = (Map<Integer, Double>) chunks.get(chunks.size() - 1);
                    SimulatorPanel.this.simulationStepForAccountTaken();
                }
            };
            SwingUtilities.invokeLater(() -> {
                initCharts();
            });
            sw.execute();
            runOrStop=false;
        } else {
            if (sw != null) {
                sw.cancel(true);        
            }
            runOrStop=true;
        }
    }

    private void initCharts() {
        lblBankrupt.setVisible(false);
        mainChartPanel.removeAll();
        addMainChart();
        //adddBettingChart();
        //validate();
    }

    private void addMainChart() {
        JFreeChart chart = ChartFactory.createBarChart("Account balance simulation", "Iteration", "Balance", null);
        chart.getCategoryPlot().getDomainAxis().setVisible(false);
        p = new ChartPanel(chart);
        p.setPreferredSize(new Dimension(mainChartPanel.getWidth() - 2, mainChartPanel.getHeight() - 20));
        chart.getCategoryPlot().setBackgroundPaint(Color.WHITE);
        mainChartPanel.setLayout(new FlowLayout());
        mainChartPanel.add(p);
        //mainChartPanel.validate();
    }

    private CategoryDataset buildChartData() {
        DefaultCategoryDataset set = new DefaultCategoryDataset();
        chartData.keySet().forEach((i) -> {
            set.setValue(chartData.get(i), "", i);
        });
        return set;
    }

    protected void simulationStepForAccountTaken() {

        if (chartData != null && !chartData.isEmpty()) {
            SwingUtilities.invokeLater(() -> {
                if (chartData.get(chartData.size() - 1) <= 0) {
                    lblBankrupt.setVisible(true);
                }
                this.iterationLab.setText(String.valueOf(chartData.size()));
                if (p != null) {
                    p.getChart().getCategoryPlot().setDataset(buildChartData());
                }
            });
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainChartPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        iterationLab = new javax.swing.JLabel();
        lblBankrupt = new javax.swing.JLabel();
        parametersPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSimulatorType = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtProbability = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtOdds = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSimulations = new javax.swing.JTextField();

        javax.swing.GroupLayout mainChartPanelLayout = new javax.swing.GroupLayout(mainChartPanel);
        mainChartPanel.setLayout(mainChartPanelLayout);
        mainChartPanelLayout.setHorizontalGroup(
            mainChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainChartPanelLayout.setVerticalGroup(
            mainChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        iterationLab.setText("0");

        lblBankrupt.setBackground(new java.awt.Color(255, 255, 255));
        lblBankrupt.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblBankrupt.setForeground(new java.awt.Color(255, 0, 51));
        lblBankrupt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBankrupt.setText("BANKRUPT!!!");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBankrupt)
                .addGap(91, 91, 91)
                .addComponent(iterationLab, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iterationLab)
                    .addComponent(lblBankrupt))
                .addContainerGap())
        );

        jLabel1.setText("Simulator Type:");

        cmbSimulatorType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Probability:");

        txtProbability.setText("0.0");
        txtProbability.setToolTipText("");

        jLabel3.setText("Odds:");

        txtOdds.setText("0.0");

        jLabel4.setText("No. of simulations:");

        txtSimulations.setText("0");

        javax.swing.GroupLayout parametersPanelLayout = new javax.swing.GroupLayout(parametersPanel);
        parametersPanel.setLayout(parametersPanelLayout);
        parametersPanelLayout.setHorizontalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(cmbSimulatorType, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProbability, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOdds, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSimulations, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        parametersPanelLayout.setVerticalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSimulatorType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtOdds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSimulations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(parametersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(parametersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbSimulatorType;
    private javax.swing.JLabel iterationLab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBankrupt;
    private javax.swing.JPanel mainChartPanel;
    private javax.swing.JPanel parametersPanel;
    private javax.swing.JTextField txtOdds;
    private javax.swing.JTextField txtProbability;
    private javax.swing.JTextField txtSimulations;
    // End of variables declaration//GEN-END:variables

    private void getSimulationParameters() {
        probability = Double.valueOf(txtProbability.getText());
        odds = Double.valueOf(txtOdds.getText());
        simulations = Integer.valueOf(txtSimulations.getText());
    }

    private void setupGUI() {
        cmbSimulatorType.setModel(new DefaultComboBoxModel(Simulators.values()));
        lblBankrupt.setVisible(false);
    }

    private BettingSeriesSimulator createSimulator() {
        try {
            return (BettingSeriesSimulator) ((Simulators) cmbSimulatorType.getSelectedItem()).getType().getDeclaredConstructor(int.class, double.class).newInstance(simulations, odds);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(SimulatorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
;

}
