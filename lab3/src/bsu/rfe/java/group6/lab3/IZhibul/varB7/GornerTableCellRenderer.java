package bsu.rfe.java.group6.lab3.IZhibul.varB7;
// for good test //123 123
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class GornerTableCellRenderer implements TableCellRenderer {
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();
    // Ищем ячейки, строковое представление которых совпадает с needle
// (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
// стога сена - таблица
    private String needle = null;
    private final DecimalFormat formatter =
            (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {
// Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
// Не использовать группировку (т.е. не отделять тысячи
// ни запятыми, ни пробелами), т.е. показывать число как "1000",
// а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
// Установить в качестве разделителя дробной части точку, а не
// запятую. По умолчанию, в региональных настройках
// Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble =
                formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
// Разместить надпись внутри панели
        panel.add(label);
// Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {
// Преобразовать double в строку с помощью форматировщика
        if (col != 2) {
            String formattedDouble = formatter.format(value);
// Установить текст надписи равным строковому представлению числа
            label.setText(formattedDouble);
            if (col == 1 && Math.signum((double)value) != Math.signum((double)table.getValueAt(row, 0))){
                panel.setBackground(Color.MAGENTA);
            } else
            if (col == 0 && needle != null && needle.equals(formattedDouble)) {

                panel.setBackground(Color.RED);
            } else {
// Иначе - в обычный белый
                panel.setBackground(Color.WHITE);
            }
        } else {
            table.setValueAt(value, row, col);
            panel.setBackground(Color.WHITE);
        }
        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
