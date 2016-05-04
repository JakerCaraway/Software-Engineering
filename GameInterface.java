package AntGame;
import AntGame.Player;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.round;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class to create an interface between players and the model
 * 
 * @author oab24
 */
public class GameInterface extends javax.swing.JFrame {
    Player player1;
    Player player2;
    boolean gamerunning;
    File game_world;
    
    
    /**
     * Creates new form GameInterface
     */
    public GameInterface() {
        initComponents();
        initialiseComponents();      
        player1 = null;
        player2= null;
        gamerunning = false;
    }
    
    /**
     * Secondary initialisation of components. Will create/ adjust many components in ways not easily manageable through the IDE 
     * GUI maker
     */
    private void initialiseComponents(){
        button_ChoosePlayer1Brain.setText("<html>Choose ant <br/>brain<html>");
        button_ChoosePlayer2Brain.setText("<html>Choose ant <br/>brain<html>");
        button_chooseWorld.setText("<html>Choose<br/>world<html>");
        check_randomWorlds.setSelected(true);
        button_chooseWorld.setEnabled(!check_randomWorlds.isSelected());
        label_worldDisplay.setVisible(true);
        int width = speedSlider.getMajorTickSpacing();
        //Create the label table        
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer(width), new JLabel("1") );
        labelTable.put( new Integer(2*width), new JLabel("10") );
        labelTable.put( new Integer(3*width), new JLabel("50") );
        labelTable.put( new Integer(4*width), new JLabel("100") );
        labelTable.put( new Integer(5*width), new JLabel("1,000") );
        labelTable.put( new Integer(6*width), new JLabel("10,000") );
        speedSlider.setLabelTable( labelTable );
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (gamerunning){
                    JOptionPane.showMessageDialog(speedSlider, "Game is running, can't change the speed. \nPlease wait till the match finishes before you change the speed.");
                }
            }
        });
        
        
        width = slide_fontSize.getMajorTickSpacing();
        Hashtable labelTable_fontSize = new Hashtable();
        labelTable_fontSize.put( new Integer(width), new JLabel("5") );
        labelTable_fontSize.put( new Integer(2*width), new JLabel("8") );
        labelTable_fontSize.put( new Integer(3*width), new JLabel("10") );
        labelTable_fontSize.put( new Integer(4*width), new JLabel("11") );
        labelTable_fontSize.put( new Integer(5*width), new JLabel("13") );
        labelTable_fontSize.put( new Integer(6*width), new JLabel("16") );
        
        slide_fontSize.setLabelTable( labelTable_fontSize );
        slide_fontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int location = slide_fontSize.getValue();
                //Dictionary h = slide_fontSize.getLabelTable();
                Object o = labelTable_fontSize.get(new Integer(location));
                String s = ((JLabel)o).getText();
                int i = Integer.parseInt(s);
                //String s = (String)slide_fontSize.getLabelTable().get(new Integer(location));
                
                Font f = new Font(label_worldDisplay.getFont().getFontName(), Font.PLAIN, i);
                label_worldDisplay.setFont(f);
                label_worldDisplay.updateUI();
                  
            }
        });
        
    }
    

    /**
     * Runs the file getter for an Ant brain. Will open a file chooser dialog to do so.
     * @return The File_String object that contains both the FSM and the error message if the FSM did not pass the generation
     */
    private File_String getAntBrainFile(){
        //Create a file chooser
        JFileChooser fc = new JFileChooser();
        //In response to a button click:
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return new File_String(fc.getSelectedFile(), null);
        }else if (returnVal== JFileChooser.CANCEL_OPTION){
            return new File_String(null, "Cancelled");
        } else {        
            return null;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        button_chooseWorld = new javax.swing.JButton();
        slide_fontSize = new javax.swing.JSlider();
        label_MapFontSize = new javax.swing.JLabel();
        speedSlider = new javax.swing.JSlider();
        panel_player1 = new javax.swing.JPanel();
        textField_PlayerRed = new javax.swing.JTextField();
        label_Player1Score = new javax.swing.JLabel();
        button_ChoosePlayer1Brain = new javax.swing.JButton();
        label_roundsPerRefresh = new javax.swing.JLabel();
        check_randomWorlds = new javax.swing.JCheckBox();
        panel_player2 = new javax.swing.JPanel();
        textField_PlayerBlack = new javax.swing.JTextField();
        label_Player2Score = new javax.swing.JLabel();
        button_ChoosePlayer2Brain = new javax.swing.JButton();
        button_mainMenu = new javax.swing.JButton();
        scrollPane_WorldDisplay = new javax.swing.JScrollPane();
        label_worldDisplay = new javax.swing.JLabel();
        label_winner = new javax.swing.JLabel();
        button_runGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_chooseWorld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_chooseWorldActionPerformed(evt);
            }
        });

        slide_fontSize.setMajorTickSpacing(1);
        slide_fontSize.setMaximum(6);
        slide_fontSize.setMinimum(1);
        slide_fontSize.setPaintLabels(true);
        slide_fontSize.setPaintTicks(true);
        slide_fontSize.setSnapToTicks(true);
        slide_fontSize.setToolTipText("");
        slide_fontSize.setValue(2);
        slide_fontSize.setName("Map size changer"); // NOI18N

        label_MapFontSize.setText("Map Font Size");

        speedSlider.setMajorTickSpacing(1);
        speedSlider.setMaximum(6);
        speedSlider.setMinimum(1);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setToolTipText("Higher numbers = more rounds pass before the map is updated again");
        speedSlider.setValue(1);

        panel_player1.setBorder(javax.swing.BorderFactory.createTitledBorder("Player 1"));
        panel_player1.setName("Player 1"); // NOI18N

        label_Player1Score.setText("Score:");

        button_ChoosePlayer1Brain.setText(" ");
        button_ChoosePlayer1Brain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChoosePlayer1BrainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_player1Layout = new javax.swing.GroupLayout(panel_player1);
        panel_player1.setLayout(panel_player1Layout);
        panel_player1Layout.setHorizontalGroup(
            panel_player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_player1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_player1Layout.createSequentialGroup()
                        .addComponent(textField_PlayerRed)
                        .addContainerGap())
                    .addGroup(panel_player1Layout.createSequentialGroup()
                        .addComponent(label_Player1Score)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(panel_player1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(button_ChoosePlayer1Brain)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_player1Layout.setVerticalGroup(
            panel_player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_player1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField_PlayerRed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Player1Score)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_ChoosePlayer1Brain)
                .addContainerGap())
        );

        label_roundsPerRefresh.setText("Rounds per refresh");

        check_randomWorlds.setText("Random Worlds");
        check_randomWorlds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_randomWorldsActionPerformed(evt);
            }
        });

        panel_player2.setBorder(javax.swing.BorderFactory.createTitledBorder("Player 2"));
        panel_player2.setName("Player 1"); // NOI18N

        textField_PlayerBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_PlayerBlackActionPerformed(evt);
            }
        });

        label_Player2Score.setText("Score:");

        button_ChoosePlayer2Brain.setText(" ");
        button_ChoosePlayer2Brain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChoosePlayer2BrainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_player2Layout = new javax.swing.GroupLayout(panel_player2);
        panel_player2.setLayout(panel_player2Layout);
        panel_player2Layout.setHorizontalGroup(
            panel_player2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_player2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_player2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_player2Layout.createSequentialGroup()
                        .addComponent(textField_PlayerBlack)
                        .addContainerGap())
                    .addGroup(panel_player2Layout.createSequentialGroup()
                        .addComponent(label_Player2Score)
                        .addGap(0, 129, Short.MAX_VALUE))))
            .addGroup(panel_player2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(button_ChoosePlayer2Brain)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_player2Layout.setVerticalGroup(
            panel_player2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_player2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField_PlayerBlack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Player2Score)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_ChoosePlayer2Brain)
                .addContainerGap())
        );

        button_mainMenu.setText("Main Menu");
        button_mainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_mainMenuActionPerformed(evt);
            }
        });

        label_worldDisplay.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        label_worldDisplay.setToolTipText("");
        label_worldDisplay.setAutoscrolls(true);
        label_worldDisplay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label_worldDisplay.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        scrollPane_WorldDisplay.setViewportView(label_worldDisplay);

        label_winner.setText("Winner: ");

        button_runGame.setText("Run Game");
        button_runGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_runGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane_WorldDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 1596, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panel_player1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panel_player2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))
                            .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(label_winner))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(button_runGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button_mainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(slide_fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(check_randomWorlds)
                                        .addGap(18, 18, 18)
                                        .addComponent(button_chooseWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(19, 19, 19))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(label_MapFontSize))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_roundsPerRefresh)
                        .addGap(79, 79, 79)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(panel_player1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(panel_player2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(label_MapFontSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slide_fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_chooseWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(check_randomWorlds)
                                .addGap(8, 8, 8)))
                        .addGap(37, 37, 37)
                        .addComponent(label_roundsPerRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(label_winner)
                        .addGap(18, 18, 18)
                        .addComponent(button_runGame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button_mainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(scrollPane_WorldDisplay, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1884, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // sets actions of pressing the main menu button
    /**
     * Even upon the main menu being pressed. Will return the user to the main menu and crash the 
     * @param evt the event of the main menu button being pressed.
     */
    private void button_mainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_mainMenuActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MainScreen.main_screen.setVisible(true);        
    }//GEN-LAST:event_button_mainMenuActionPerformed

     // sets the actions of the choose player 1 brain - comnstructs player 1 object
    private void button_ChoosePlayer1BrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChoosePlayer1BrainActionPerformed
        // if the player already exists
        if  ( (player1 != null) &&(player1.getName().equals(textField_PlayerRed.getText())) ){
            try {
                File_String file_string_object = getAntBrainFile();
                File f = file_string_object.f;
                if (file_string_object.getS()== null){
                    AntBrain b= new AntBrain(f);
                    if (b.getErrorMessage() == null)
                        player1.setAntBrain(b);            
                    else 
                        JOptionPane.showMessageDialog(rootPane, b.getErrorMessage());
                } else if (file_string_object.s.equals("Cancelled"))
                    JOptionPane.showMessageDialog(rootPane, "");
                else if (file_string_object == null){
                    JOptionPane.showMessageDialog(this, "No Brain selected");
                } 
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Something is wrong with the file. Could not be uploaded");
            }
        } else if (textField_PlayerRed.getText().equals("")){
            JOptionPane.showMessageDialog(this, "There is no player name for the red player, please put a name first");
        } else {
            try {
                File_String file_string_object = getAntBrainFile();
                File f = file_string_object.f;
                if (file_string_object.getS()== null){
                    AntBrain b= new AntBrain(f);
                    if (b.getErrorMessage() == null)
                        player1 = new Player(textField_PlayerRed.getText(), b);            
                    else 
                        JOptionPane.showMessageDialog(rootPane, b.getErrorMessage());                     
                } else if (file_string_object.s.equals("Cancelled"))
                    JOptionPane.showMessageDialog(rootPane, "");
                else if (file_string_object == null){
                    JOptionPane.showMessageDialog(this, "No Brain selected");
                } 
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Something is wrong with the file. Could not be uploaded");
            }
        }  
    }//GEN-LAST:event_button_ChoosePlayer1BrainActionPerformed

    // sets the actions of the choose player 2 brain - comnstructs player 2 object
    private void button_ChoosePlayer2BrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChoosePlayer2BrainActionPerformed
        if  ( (player2 != null) &&(player2.getName().equals(textField_PlayerBlack.getText())) ){
            try {
                File_String file_string_object = getAntBrainFile();
                File f = file_string_object.f;
                if (file_string_object.getS()== null){
                    AntBrain b= new AntBrain(f);
                    if (b.getErrorMessage() == null)
                        player2.setAntBrain(b);            
                    else 
                        JOptionPane.showMessageDialog(rootPane, b.getErrorMessage());                    
                } else if (file_string_object.s.equals("Cancelled"))
                    JOptionPane.showMessageDialog(rootPane, "");
                else if (file_string_object == null){
                    JOptionPane.showMessageDialog(this, "No Brain selected");
                } 
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Something is wrong with the file. Could not be uploaded");
            }
        } else if (textField_PlayerBlack.getText().equals("")){
            JOptionPane.showMessageDialog(this, "There is no player name for the red player, please put a name first");
        } else {
            try {
                File_String file_string_object = getAntBrainFile();
                File f = file_string_object.f;
                if (file_string_object.getS()== null){
                    AntBrain b= new AntBrain(f);
                    if (b.getErrorMessage() == null)
                        player2 = new Player(textField_PlayerBlack.getText(), b);            
                    else 
                        JOptionPane.showMessageDialog(rootPane, b.getErrorMessage());                    
                } else if (file_string_object.s.equals("Cancelled"))
                    JOptionPane.showMessageDialog(rootPane, "");
                else if (file_string_object == null){
                    JOptionPane.showMessageDialog(this, "No Brain selected");
                } 
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Something is wrong with the file. Could not be uploaded");
            }
        }   
    }//GEN-LAST:event_button_ChoosePlayer2BrainActionPerformed

    // IGNORE
    private void textField_PlayerBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_PlayerBlackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_PlayerBlackActionPerformed

    // sets the actions of run game button
    private void button_runGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_runGameActionPerformed
        // TODO add your handling code here:
        if (player1==null || player2 ==null){ 
            JOptionPane.showMessageDialog(rootPane, "Please add some players before running a game");
        } else {
            player1.incrScore();
            World w;            
            if (game_world == null && check_randomWorlds.isSelected()){
                w = new World(null);                
                w.getRandomWorld(150,150);
            } else if (game_world == null){
                JOptionPane.showMessageDialog(rootPane, "There is no world selected. Please either turn random worlds on\n or select your own world");
            }else{
                label_winner.setText("");                
                gamerunning = true;
                w = new World(game_world);       
                // construct game instance
                Game game_instance = new Game(w, player1, player1);
                // show map                 
                String map = w.GetCurrentWorld();
                label_worldDisplay.setText(map);
                label_worldDisplay.setVisible(true);
                int speed = getActualSpeed();
                /*
                Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {      
                            for (int i = 0; i < (300000/speed);i++ ){
                                game_instance.runSection(speed);       
                                System.out.println("The game has run round " + Integer.toString(i)+ " of " + Integer.toString(300000/speed));
                                String s2 = setupWorldForDisplay(game_instance.getGameWorld().GetCurrentWorld());
                                label_worldDisplay.setText(s2);                   
                                System.out.println("ui_updated");
                            }   
                            
                        }     
                    });
                t.run();*/
                for (int i = 0; i < (300000/speed);i++ ){
                    game_instance.runSection(speed);       
                    System.out.println("The game has run round " + Integer.toString(i)+ " of " + Integer.toString(300000/speed));
                    String s2 = setupWorldForDisplay(game_instance.getGameWorld().GetCurrentWorld());
                    label_worldDisplay.setText(s2);                   
                    System.out.println("ui_updated");
                }
                
                 /*
                    map = game_instance.getGameWorld().GetCurrentWorld();
                    String s2 = setupWorldForDisplay(map);

                  //  label_worldDisplay.setVisible(false);
                    label_worldDisplay.setText("");
                    label_worldDisplay.setText(s2);                    
                    label_worldDisplay.updateUI();
                            */
                
                int count_red= 0;
                int count_black = 0;
                for (int i =0; i <254;i++){
                    if ( game_instance.Ants[i].getColour().equals("red") ) 
                        count_red = count_red+1;
                    else
                        count_black = count_black +1;
                }
                
                String s2 = setupWorldForDisplay(game_instance.getGameWorld().GetCurrentWorld());
                label_worldDisplay.setText(s2);
                    
                    
                    
                System.out.println("The game has finished the match");
                String s = game_instance.getWinner();
                if (s.equals("red")){
                    player1.incrScore();
                    label_winner.setText("Winner is: "+ player1.getName());
                } else if (s.equals("black")){
                    player2.incrScore();
                    label_winner.setText("Winner is: "+ player2.getName());
                }else {
                    label_winner.setText("Draw");
                }
                
                    
                
                label_Player1Score.setText("Score: "+ (Integer.toString(player1.getScore()))) ;
                label_Player2Score.setText("Score: "+ (Integer.toString(player2.getScore()))) ;        
                gamerunning = false;   
            }
        }
    }//GEN-LAST:event_button_runGameActionPerformed

    
    private int getActualSpeed(){
        int speed = speedSlider.getValue();
        switch (speed){
                case 1: 
                    return 1;
                case 2:
                    return 10;
                case 3:
                    return 50;
                case 4:
                    return 100;
                case 5:
                    return 1000;
                case 6:
                    return 10000;
                default:
                    return 1;
        }
    }
    
    // sets up the choose world funciton. Will use a file chooser to get the file
    private void button_chooseWorldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_chooseWorldActionPerformed
        JFileChooser fc = new JFileChooser();        
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {            
            game_world = fc.getSelectedFile();
            World w = new World(game_world);
            String s = w.GetCurrentWorld();
            //"<html>Choose<br/>world<html>"
            String s2 = setupWorldForDisplay(s);
            label_worldDisplay.setText(s2);
            label_worldDisplay.setVisible(true);
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Something went wrong");
        }
    }//GEN-LAST:event_button_chooseWorldActionPerformed

    // sets up action of the check box for random world
    private void check_randomWorldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_randomWorldsActionPerformed
        // TODO add your handling code here:
        if (check_randomWorlds.isSelected()){
            button_chooseWorld.setEnabled(false);
            game_world = null;
        }else{
            button_chooseWorld.setEnabled(true);
        }
    }//GEN-LAST:event_check_randomWorldsActionPerformed
   
    // function to format the world string so it can be displayed properly
    private String setupWorldForDisplay(String s){
        s = "<html>"+s+"<html>";
        String[] sA = s.split("\n");
            String s2 = "";
            for (int i =0; i < sA.length; i++){
                if (i%2== 0)                    
                    s2 = s2 + sA[i] + "<br/>";
                else
                    s2 = s2 + " " + sA[i] + "<br/>";
            }
            return s2;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ChoosePlayer1Brain;
    private javax.swing.JButton button_ChoosePlayer2Brain;
    private javax.swing.JButton button_chooseWorld;
    private javax.swing.JButton button_mainMenu;
    private javax.swing.JButton button_runGame;
    private javax.swing.JCheckBox check_randomWorlds;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_MapFontSize;
    private javax.swing.JLabel label_Player1Score;
    private javax.swing.JLabel label_Player2Score;
    private javax.swing.JLabel label_roundsPerRefresh;
    private javax.swing.JLabel label_winner;
    private javax.swing.JLabel label_worldDisplay;
    private javax.swing.JPanel panel_player1;
    private javax.swing.JPanel panel_player2;
    private javax.swing.JScrollPane scrollPane_WorldDisplay;
    private javax.swing.JSlider slide_fontSize;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JTextField textField_PlayerBlack;
    private javax.swing.JTextField textField_PlayerRed;
    // End of variables declaration//GEN-END:variables
    
    // subclass that stores a file and a string
    private class File_String{
        private File f;
        private String s;        

        public File_String(File f, String s) {
            this.f = f;
            this.s = s;            
        }

        public File getF() {
            return f;
        }

        public String getS() {
            return s;
        }  
    }

    
}