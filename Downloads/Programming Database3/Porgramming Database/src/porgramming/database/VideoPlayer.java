package porgramming.database;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VideoPlayer extends JFrame
                  implements ActionListener {
        JButton check = new JButton("Check Videos");
        JButton playlist = new JButton("Create Video List");
        JButton update = new JButton("Update Videos");
        JButton quit = new JButton("Exit");

    public static void main(String[] args) {
        new VideoPlayer();
    }
    
    public VideoPlayer() {
        setLayout(new BorderLayout());
        setSize(450, 100);
        setTitle("Video Player");

        // close application only by clicking the quit button
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JPanel top = new JPanel();
        top.add(new JLabel("Select an option by clicking one of the buttons below"));
        add("North", top);

        JPanel bottom = new JPanel();
        bottom.add(check); check.addActionListener(this);
        bottom.add(playlist); playlist.addActionListener(this);
        bottom.add(update); update.addActionListener(this);
        bottom.add(quit); quit.addActionListener(this);
        add("South", bottom);

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == check) {
            new CheckVideos();
        } else if (e.getSource() == playlist){
            new CreateVideoList();
        }else if (e.getSource() == update){
            new UpdateVideos();
        }else if (e.getSource() == quit) {
            VideoData.close();
            System.exit(0);
        }
    }
}