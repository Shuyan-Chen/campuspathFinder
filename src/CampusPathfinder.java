import javax.swing.*;
import java.awt.*;

public class CampusPathfinder extends JFrame {
    private Dijkstra dijkstra;
    private JTextField startField;
    private JTextField endField;
    private JTextArea resultArea;
    private JLabel campusImageLabel;

    public CampusPathfinder() {
        dijkstra = new Dijkstra();
        initializeGraph();

        setTitle("CampusFinder");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        campusImageLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/img.png")));
        JScrollPane scrollPane = new JScrollPane(campusImageLabel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        startField = new JTextField(10);
        endField = new JTextField(10);
        JButton findPathButton = new JButton("find path");
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);


        findPathButton.addActionListener(e -> {
            String start = startField.getText().toUpperCase();
            String end = endField.getText().toUpperCase();
            Dijkstra.PathResult result = dijkstra.findShortestPath(start, end);
            if (result.distance == Integer.MAX_VALUE) {
                resultArea.setText("Unable reach the distance");
            } else {
                resultArea.setText("Shortest path：" + result.path + "\ntotal distance：" + result.distance + " meter");
            }
        });


        inputPanel.add(new JLabel("start:"));
        inputPanel.add(startField);
        inputPanel.add(new JLabel("final:"));
        inputPanel.add(endField);
        inputPanel.add(findPathButton);


        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);
    }

    private void initializeGraph() {
        dijkstra.addEdge("A1", "A2", 100);
        dijkstra.addEdge("A1", "A3", 200);
        dijkstra.addEdge("A1", "A4", 50);
        dijkstra.addEdge("A2", "A10", 300);
        dijkstra.addEdge("A2", "A29", 150);
        dijkstra.addEdge("A2", "A12", 170);
        dijkstra.addEdge("A2", "A9", 100);
        dijkstra.addEdge("A2", "A7", 190);
        dijkstra.addEdge("A4", "A29", 150);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CampusPathfinder app = new CampusPathfinder();
            app.setVisible(true);
        });
    }
}

