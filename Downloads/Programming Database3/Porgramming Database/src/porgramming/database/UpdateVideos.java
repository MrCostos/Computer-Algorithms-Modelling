package porgramming.database;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class UpdateVideos extends JFrame
                  implements ActionListener {
    JTextField videoNo = new JTextField(2);
    TextArea information = new TextArea(6, 50);
    JButton list = new JButton("List All Videos");
    JButton check = new JButton("Check Video");
    JTextField videoName = new JTextField(12);
    JTextField videoDirector = new JTextField(12);
    JTextField videoRating = new JTextField(12);
    JButton saveChanges = new JButton("Save Changes");
    
    //JTable table = new JTable (2,5);
    boolean filmExist = false;
    String existKey;
    
    public UpdateVideos() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 400, 200);
        setTitle("Update Library");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        top.add(new JLabel("Enter Video Number:"));
        top.add(videoNo);
        top.add(check);
        top.add(saveChanges);
        
        
        add("North", top);
        JPanel middle = new JPanel();
        middle.add(videoName);
        middle.add(videoDirector);
        middle.add(videoRating);
//        information.setText(VideoData.listAll());
//        middle.add(information);
    //    middle.add(table);
        add("Center", middle);

        list.addActionListener(this);
        check.addActionListener(this);
        saveChanges.addActionListener(this);
        setResizable(false);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == check) { 
            checkVideoExist();
           } else if (e.getSource() == saveChanges){
            EditValues();
        }
    }
        
    
    public void checkVideoExist (){
        String key = videoNo.getText();
            existKey = key;
            String name = VideoData.getName(key);
            String value = Integer.toString(VideoData.getRating(key));
            if (name == null) {
                videoName.setText("No such video number");
                videoDirector.setText("No such video number");
                videoRating.setText("No such video number") ;
                filmExist = false;
               
            } else {
                videoName.setText(VideoData.getName(key));
                videoDirector.setText(VideoData.getDirector(key));
                videoRating.setText(value);
                filmExist = true;
            } 
    }
    
    public void EditValues(){
            if (filmExist = true){
//               VideoData.setName(existKey,  videoName.getText());
//               VideoData.setName(existKey,  videoDirector.getText());
//               VideoData.setName(existKey,  videoRating.getText());
               
               VideoData.setVideo(existKey, videoName.getText(), videoRating.getText());
               
               showMessageDialog(null, "Changes have been made, click Refresh button to see changes");
            } else{ 
                showMessageDialog(null, "Video does not exist, Impossible to save changes!");}
    }

    private String stars(int rating) { // is this required here ******************************************************************
        String stars = "";
        for (int i = 0; i < rating; ++i) {
            stars += "*";
        }
        return stars;
    }
}