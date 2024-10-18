package com.edu.chmnu.ki_123.c3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.awt.*;
import static java.lang.Math.*;

public class MathCalcGraph extends JFrame {

    public MathCalcGraph(String title) {
        super(title);
        XYSeries series = new XYSeries("f(x)");

        double a = 2.5, b = 7.7, c = -4.32;

        double minX = Double.NaN, minF = Double.NaN, maxX = Double.NaN, maxF = Double.NaN;
        boolean first = true;

        System.out.printf("%-10s %-10s\n", "x", "f(x)");
        System.out.println("--------------------------");

        for (double x = -10; x <= 10; x += 0.1) {
            double f;
            try {
                f = exp(a * cos(x + 2)) - (exp(-sin(b * x))) / (c - cbrt(x));

                if (Double.isNaN(f) || Double.isInfinite(f)) {
                    continue;
                }

                series.add(x, f);

                System.out.printf("%-10.2f %-10.2f\n", x, f);

                if (first || f < minF) {
                    minX = x;
                    minF = f;
                }
                if (first || f > maxF) {
                    maxX = x;
                    maxF = f;
                }

                first = false;
            } catch (Exception e) {
                System.err.println("Error calculating function value at x = " + x + ": " + e.getMessage());
            }
        }

        System.out.println("--------------------------");
        System.out.printf("Найдені екстремуми:%nМінімум: x = %.2f, f(x) = %.2f%nМаксимум: x = %.2f, f(x) = %.2f%n", minX, minF, maxX, maxF);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Графік функції з екстремумами",
                "x", "f(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MathCalcGraph chart = new MathCalcGraph("Побудова графіку та екстремуми");
        });
    }
}