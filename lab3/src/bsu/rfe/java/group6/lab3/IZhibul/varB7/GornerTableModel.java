package bsu.rfe.java.group6.lab3.IZhibul.varB7;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
// В данной модели два столбца
        return 2;
    }

    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        double[] result = new double[3];
        if (col == 0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        } else if (col == 1) {
            result[0] = 0.0;
            for (int i = 0; i < coefficients.length; i++) {
                result[0] += Math.pow(x, coefficients.length - 1 - i) * coefficients[i];
            }
            return result[0];
        } else if (col == 2) {
            result[1] = 0.0;
            int p = coefficients.length - 1;
            for (int i = 0; i < coefficients.length; i++) {
                result[1] += Math.pow(x, coefficients.length - 1 - i) * coefficients[p--];
            }
            return result[1];
        } else
            return result[2] = result[1] - result[0];
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            default:
// Название 2-го столбца
                return "Значение многочлена";
        }
    }

    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        return Double.class;
    }
}
