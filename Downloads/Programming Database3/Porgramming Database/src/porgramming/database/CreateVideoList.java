package porgramming.database;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import java.io.*;

public class CreateVideoList extends JFrame
                  implements ActionListener {
    public FileWriter outFile;
    JTextField videoNo = new JTextField(2);
    TextArea information = new TextArea(6, 50);
    JTextField filmName = new JTextField(12);
    JTextField directorName = new JTextField (12);
    JButton list = new JButton("Add Film to Playlist");
    JButton finished = new JButton ("Execute Chosen Check Boxes & Exit");
    JButton existCheck = new JButton ("Check Video");
    JTable grid = new JTable(); //Does this do anything check *******    
    JCheckBox playCheck = new JCheckBox("Play Playlist");
    JCheckBox saveCheck = new JCheckBox("Save PlayList"); 
    
    int arrayCounter = 0;
    int arrayCounter2 = 0;
    String[][] playCountArray  = new String [arrayCounter][arrayCounter2];
    boolean implementArray = false;
    int arrayLength = playCountArray.length;
    
    public CreateVideoList() {
        setLayout(new BorderLayout());
        setBounds(100, 100, 600, 230);
        setTitle("Create A Playlist");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel top = new JPanel();
        top.add(new JLabel("Enter Video Number:"));
        top.add(videoNo);
        top.add(new JLabel("Film Name:"));
        top.add(filmName);
        top.add(new JLabel("Director:"));
        top.add(directorName);
        
        add("North", top);
        
        JPanel bottom = new JPanel();
        bottom.add(playCheck);
        bottom.add(saveCheck);
        bottom.add(finished);
        add("South", bottom);
        
        JPanel middle = new JPanel();
        
        middle.add(list);
        middle.add(existCheck);
        middle.add(information);
        
        add("Center", middle);
        
        list.addActionListener(this);
        existCheck.addActionListener(this);
        finished.addActionListener(this);
       // playCheck.checkImage(null, middle); ***huh?*************************
        
        videoNo.setText("00");
        filmName.setText("N/A");
        directorName.setText("N/A");
        
        setResizable(false);
        setVisible(true);
        filmName.setEditable(false);
        directorName.setEditable(false);
    }
    

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == existCheck) { 
            existCheckVideo();
           } else if (e.getSource() == list){
            addToPlaylist();
        } else if(e.getSource() == finished){
            finshPlay();
        }
        }
    
    
    public void existCheckVideo(){
        String key = videoNo.getText(); 
        String name = VideoData.getName(key);
        if (name == null) { 
                showMessageDialog(null, "No such video number");
                filmName.setText(VideoData.getName(key));
                    directorName.setText(VideoData.getDirector(key));
                    
                } else if(name != null){ 
                    filmName.setText(VideoData.getName(key));
                    directorName.setText(VideoData.getDirector(key));
    }
}

    
    public void addToPlaylist(){
        String key = videoNo.getText(); 
        String name = VideoData.getName(key);
//        if (information == null){
//            VideoData.setNewPlayCount(key);
//                
//        } else #
        if (information != null) {
            information.append(name + " - " + VideoData.getDirector(key) + " Play count:" + VideoData.getPlayCount(key) + "\n" );
            VideoData.setNewPlayCount(key);
           
    }
}
    
    public void finshPlay(){
        PlayPlayList();
        SavePlaylist();
       
        
    }
    public void SavePlaylist(){
        //find values again due to playing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (saveCheck.isSelected()){
            String playlistName = showInputDialog("What would you like to call the playlist?");
            try{
        outFile = new FileWriter(playlistName + ".txt", true);
        outFile.write(information.getText()); //BUG!!!! SAVES wrong PLAYCount!********************!*!*!*!*!**!*!*!*!**!*!*!*!*!*!*!*!*!*!**!*!*!*!*!*!*!*!*!*!*!**!*!
        outFile.close();
        }catch(IOException e){
            System.err.println("File Error:" + e);
            System.exit(1);
        }
        } 
    }
    
    public void PlayPlayList(){
        if (playCheck.isSelected()){ // SORT OUT THIS PROCEDURE TO NOT BE ABLE TO PLAY EMPTY Playlist
            if (arrayLength != 0){
            for (int counter = 1; counter == arrayLength; counter++){
            int temp = counter + 1;
            int setPlayCountValue = Integer.parseInt(playCountArray[counter][temp]);
            VideoData.setPlayCount(Integer.toString(counter), Integer.toString(setPlayCountValue));
        }
        }
        }
        //ASK **********************************************************************************
        
    }
}

    
     
    
   
