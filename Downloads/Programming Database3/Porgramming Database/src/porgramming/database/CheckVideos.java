package porgramming.database;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckVideos extends JFrame
                  implements ActionListener {
    JTextField videoNo = new JTextField(2);
    TextArea information = new TextArea(6, 50);
    JButton list = new JButton("List All Videos");
    JButton check = new JButton("Check Video");
    public CheckVideos() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 400, 200);
        setTitle("Check Library");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        top.add(new JLabel("Enter Video Number:"));
        top.add(videoNo);
        top.add(check);
        top.add(list);
        list.addActionListener(this);
        check.addActionListener(this);
        add("North", top);
        JPanel middle = new JPanel();
        information.setText(VideoData.listAll());
        middle.add(information);
        add("Center", middle);

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == list) {
            information.setText(VideoData.listAll());
        } else {
            String key = videoNo.getText();
            String name = VideoData.getName(key);
            if (name == null) {
                information.setText("No such video number");
            } else {
                information.setText(name + " - " + VideoData.getDirector(key));
                information.append("\nRating: " + stars(VideoData.getRating(key)));
                information.append("\nPlay count: " + VideoData.getPlayCount(key));
            }
        }
    }

    private String stars(int rating) {
        String stars = "";
        for (int i = 0; i < rating; ++i) {
            stars += "*";
        }
        return stars;
    }
}