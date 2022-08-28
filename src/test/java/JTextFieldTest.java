import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JTextFieldTest implements Runnable {

    private GridModel model;

    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new JTextFieldTest());
    }

    public JTextFieldTest() {
        this.model = new GridModel();
    }

    @Override
    public void run() {
        frame = new JFrame("Crossword Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createGrid());

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createGrid() {
        JPanel panel = new JPanel();
        char[][] grid = model.getGrid();
        panel.setLayout(new GridLayout(0, grid.length));

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                JTextField field = new JTextField(2);
                field.setText(Character.toString(grid[i][j]));
                panel.add(field);
                field.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        super.keyReleased(e);
                        System.out.println(field.getText());
                    }
                });
            }
        }

        return panel;
    }

    public class GridModel {

        private char[][] grid;

        public GridModel() {
            this.grid = new char[15][15];
        }

        public char[][] getGrid() {
            return grid;
        }

    }

}