package presentation;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for generating a JTable model from a list of objects using reflection
 */

public class TableGenerator {

    /**
     * Generates a TableModel for a JTable based on a list of objects
     * Uses reflection to extract field names and values
     */
    public static <T> TableModel generateTable(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new DefaultTableModel();
        }

        T first = list.get(0);
        Field[] fields = first.getClass().getDeclaredFields();

        String[] columnNames = Arrays.stream(fields)
                .map(Field::getName)
                .toArray(String[]::new);

        Object[][] data = new Object[list.size()][fields.length];

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                try {
                    data[i][j] = fields[j].get(item);
                } catch (IllegalAccessException e) {
                    data[i][j] = "N/A";
                }
            }
        }

        return new DefaultTableModel(data, columnNames);
    }

}
