package bsu.rfe.java.group6.lab3.IZhibul.varB7;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private final Double[] coefficients;
    private final Double from;
    private final Double to;
    private final Double step;

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
// В данной модели два(ха уже 3) столбца
        return 3;
    }

    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        if (col == 0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        } else {
            double result = 0.0;
            for (int i = 0; i < coefficients.length; i++) {
                result += Math.pow(x, coefficients.length - 1 - i) * coefficients[i];
            }
            if (col == 1) {
                return result;
            } else {
                return checkSequences(result);
            }
        }
    }

    private boolean checkSequences(double result) {
        String s = String.format("%.5g%n", result);
        boolean ans = false;
        for (int i = 2; i < s.length(); i++) {
            boolean localAns = true;
            for (int j = i - 1; j <= i; j++) {
                if (!(s.codePointAt(j) == s.codePointAt(j - 1) + 1)) {
                    localAns = false;
                    break;
                }
            }
            ans |= localAns;
        }

        return ans;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена";
            default:
                return "Последовательный ряд?";
        }

    }


    public Class<?> getColumnClass(int col) {
        if (col == 2){
            return Boolean.class;
        }

// И в 1-ом и во 2-ом столбце находятся значения типа Double
        return Double.class;
    }
}
