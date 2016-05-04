/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntGame;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author oab24
 */
public class TournamentInterface extends javax.swing.JFrame {
    World game_world;
   // ArrayList<Player> players;
   // TableModel tableModel;

    /**
     * Creates new form TournamentInterface
     */
    public TournamentInterface() {
        initComponents();
        initialiseComponents();
    }
    
    // seconday component initialisation
    /**
     * Secondary component initialisation for actions trickier to set up in the GUI IDE maker
     */
    private void initialiseComponents() {
        button_chooseNewPlayerBrain.setText("<html>Add new<br/>Player<html>");
        button_deletePlayer.setText("<html>Delete<br/>Player<html>");
        button_runTournament.setText("<html>Run<br/>Tournament<html>");
        button_selectWorld.setText("<html>Select<br/>World<html>");
        button_randomWorld.setText("<html>Generate<br/>random world<html>");
        label_worldDiagram.setVisible(false);
        //players = new ArrayList<>();
        
        table_players.setModel(new SETableModle());
        int width = slide_fontSize.getMajorTickSpacing();
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
                
                Font f = new Font(label_worldDiagram.getFont().getFontName(), Font.PLAIN, i);
                label_worldDiagram.setFont(f);
                label_worldDiagram.updateUI();
                  
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        button_mainMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_players = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_players = new javax.swing.JTable();
        panel_addPlayer = new javax.swing.JPanel();
        textField_addPlayer = new javax.swing.JTextField();
        button_chooseNewPlayerBrain = new javax.swing.JButton();
        button_deletePlayer = new javax.swing.JButton();
        button_runTournament = new javax.swing.JButton();
        button_selectWorld = new javax.swing.JButton();
        button_randomWorld = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel_World = new javax.swing.JPanel();
        label_worldDiagram = new javax.swing.JLabel();
        slide_fontSize = new javax.swing.JSlider();
        label_MapFontSize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_mainMenu.setText("Main Menu");
        button_mainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_mainMenuActionPerformed(evt);
            }
        });

        table_players.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "Score", "Participating"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_players);
        if (table_players.getColumnModel().getColumnCount() > 0) {
            table_players.getColumnModel().getColumn(0).setResizable(false);
            table_players.getColumnModel().getColumn(1).setResizable(false);
            table_players.getColumnModel().getColumn(2).setResizable(false);
        }

        table_players.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "Score", "Participating"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_players);
        if (table_players.getColumnModel().getColumnCount() > 0) {
            table_players.getColumnModel().getColumn(0).setResizable(false);
            table_players.getColumnModel().getColumn(1).setResizable(false);
            table_players.getColumnModel().getColumn(2).setResizable(false);
        }
        if (table_players.getColumnModel().getColumnCount() > 0) {
            table_players.getColumnModel().getColumn(0).setResizable(false);
            table_players.getColumnModel().getColumn(1).setResizable(false);
            table_players.getColumnModel().getColumn(2).setResizable(false);
        }

        panel_addPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder("New Player"));
        panel_addPlayer.setName("Player 1"); // NOI18N

        button_chooseNewPlayerBrain.setText("add");
        button_chooseNewPlayerBrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_chooseNewPlayerBrainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_addPlayerLayout = new javax.swing.GroupLayout(panel_addPlayer);
        panel_addPlayer.setLayout(panel_addPlayerLayout);
        panel_addPlayerLayout.setHorizontalGroup(
            panel_addPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField_addPlayer)
                .addContainerGap())
            .addGroup(panel_addPlayerLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(button_chooseNewPlayerBrain)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panel_addPlayerLayout.setVerticalGroup(
            panel_addPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField_addPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_chooseNewPlayerBrain)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        button_deletePlayer.setText("Delte");
        button_deletePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deletePlayerActionPerformed(evt);
            }
        });

        button_runTournament.setText("runTournament");
        button_runTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_runTournamentActionPerformed(evt);
            }
        });

        button_selectWorld.setText("setWorld");
        button_selectWorld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_selectWorldActionPerformed(evt);
            }
        });

        button_randomWorld.setText("randWorld");
        button_randomWorld.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_randomWorld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_randomWorldActionPerformed(evt);
            }
        });

        label_worldDiagram.setText("world");
        label_worldDiagram.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panel_WorldLayout = new javax.swing.GroupLayout(panel_World);
        panel_World.setLayout(panel_WorldLayout);
        panel_WorldLayout.setHorizontalGroup(
            panel_WorldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_WorldLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(label_worldDiagram)
                .addContainerGap(1172, Short.MAX_VALUE))
        );
        panel_WorldLayout.setVerticalGroup(
            panel_WorldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_WorldLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_worldDiagram)
                .addContainerGap(588, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(panel_World);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(panel_addPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(button_selectWorld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_randomWorld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_deletePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_runTournament, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(button_mainMenu)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(label_MapFontSize))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(slide_fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(panel_addPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(button_deletePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_MapFontSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slide_fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(button_randomWorld)
                        .addGap(50, 50, 50)
                        .addComponent(button_selectWorld)
                        .addGap(40, 40, 40)
                        .addComponent(button_runTournament)
                        .addGap(39, 39, 39)
                        .addComponent(button_mainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1826, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // main menu button funcitons. Closes the tournament interface, freeing everything to memory
    /**
     * Main menu button mover. Will result in signing the tournament and entire interface to free memory.
     * @param evt Main menu button is pressed
     */
    private void button_mainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_mainMenuActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);        
        MainScreen.main_screen.setVisible(true);
    }//GEN-LAST:event_button_mainMenuActionPerformed

    /**
     * Choose new player button. Will create a new player Entity using the name in the text field. will then use a file chooser to select the ant brain. Finally will combine player with a "false" boolean variable to 
     * store as a player_boolean class. Stores this in the table and table model.
     * @param evt the choose player button is pressed
     */
    private void button_chooseNewPlayerBrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_chooseNewPlayerBrainActionPerformed
        // create a new player
        if (!textField_addPlayer.getText().equals("")){        
            try {
                boolean valid_file = false;
                JFileChooser fc = new JFileChooser();
                //In response to a button click:
                int returnVal = fc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {  
                    AntBrain b = new AntBrain(fc.getSelectedFile());
                    if (b.getErrorMessage() == null) {
                        Player p = new Player(textField_addPlayer.getText(), b);  
                        //players.add(p);
                        ((SETableModle)table_players.getModel()).addNewPlayer(p);
                        table_players.updateUI();
                    } else 
                        JOptionPane.showMessageDialog(rootPane, b.getErrorMessage()); 
                }else if (returnVal== JFileChooser.CANCEL_OPTION){

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Something went wrong while choosing the ant brain");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Something is wrong with the file. Could not be uploaded");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Please input a player name before adding one");
        }  
    }//GEN-LAST:event_button_chooseNewPlayerBrainActionPerformed

    /**
     * Will delete the currently selected player from the table
     * @param evt delete player button is pressed
     */
    private void button_deletePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deletePlayerActionPerformed
        // TODO add your handling code here:
        ((SETableModle)table_players.getModel()).deletePlayer();
    }//GEN-LAST:event_button_deletePlayerActionPerformed

    // runs the tournament using the selected world
    /**
     * runs the tournament using the currently selected players and the currently used world. Will throw error messages if there arent enough players or the world isnt selected.
     * @param evt the run tournament button is pressed
     */
    private void button_runTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_runTournamentActionPerformed
        // TODO add your handling code here:
        ArrayList<Player> playersToCompte = new ArrayList<>();            
        for (int i = 0; i <table_players.getModel().getRowCount();i++ ) {
            if ((boolean)table_players.getModel().getValueAt(i, 2) ) {
                playersToCompte.add(((SETableModle)table_players.getModel()).getPlayer(i));
            }
        }
        if (playersToCompte.size() < 2){
            JOptionPane.showMessageDialog(rootPane, "There needs to be 2 or more players for a tournament to take place");
        }else if (game_world == null) {
            JOptionPane.showMessageDialog(rootPane, "There needs to be a world chosen");
        } else {
            Tournament t = new Tournament(playersToCompte, game_world);
            ( (SETableModle)table_players.getModel() ).sort();        
            table_players.updateUI();   
        }
    }//GEN-LAST:event_button_runTournamentActionPerformed

    /**
     * sets up the action for when a world is selected. loads a world using a world file that is selected
     * using a file chooser
     * @param evt select world button is pressed
     */
    private void button_selectWorldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_selectWorldActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();        
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {            
            File game_world_file = fc.getSelectedFile();
            World w = new World(game_world_file);
            String s = w.GetCurrentWorld();
            s = "<html>"+s+"<html>";
            String[] sA = s.split("\n");
            s = "";
            for (int i =0; i < sA.length; i++){
                if (i%2== 0)
                    s = s + sA[i] + "<br/>";
                else
                    s = s + " " + sA[i] + "<br/>";
            }
            label_worldDiagram.setText(s);
            label_worldDiagram.setVisible(true);
            game_world = w;
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Something went wrong");
        }
    }//GEN-LAST:event_button_selectWorldActionPerformed

    // sets up what the random world action does
    /**
     * generates a random world to be used in the tournament. This is displayed ready to be inspected for the tournament.
     * @param evt the generate random world button is pressed
     */
    private void button_randomWorldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_randomWorldActionPerformed
        World w = new World(null);
        w.getRandomWorld(150,150);
        String s = w.GetCurrentWorld();
            s = "<html>"+s+"<html>";
            String[] sA = s.split("\n");
            s = "";
            for (int i =0; i < sA.length; i++){
                if (i%2== 0)
                    s = s + sA[i] + "<br/>";
                else
                    s = s + " " + sA[i] + "<br/>";
            }
        label_worldDiagram.setText(s);
        label_worldDiagram.setVisible(true);
        game_world = w;
    }//GEN-LAST:event_button_randomWorldActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_chooseNewPlayerBrain;
    private javax.swing.JButton button_deletePlayer;
    private javax.swing.JButton button_mainMenu;
    private javax.swing.JButton button_randomWorld;
    private javax.swing.JButton button_runTournament;
    private javax.swing.JButton button_selectWorld;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_MapFontSize;
    private javax.swing.JLabel label_worldDiagram;
    private javax.swing.JPanel panel_World;
    private javax.swing.JPanel panel_addPlayer;
    private javax.swing.JSlider slide_fontSize;
    private javax.swing.JTable table_players;
    private javax.swing.JTextField textField_addPlayer;
    // End of variables declaration//GEN-END:variables

    
    /**
     * a subclass that is the table model to be used for the data about players. Used to store the underlying data 
     */
    class SETableModle extends AbstractTableModel{    
        private ArrayList<player_boolean> players = new ArrayList<>();
        private String[] columnNames = {"Player", "Score", "Participating" };

        private Class[] types = new Class [] {
            java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, true
        };

        /**
         * gets the row count
         * @return the number of entries in the table - int
         */
        @Override
        public int getRowCount() {
            return players.size();
        }

        /**
         * gets the number of columns in the table
         * @return int of the number of columns
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
            //return null;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
             return canEdit [columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex){
                case 0: 
                    return players.get(rowIndex).getP().getName();                        
                case 1:
                    return players.get(rowIndex).getP().getScore();                        
                case 2:
                    return players.get(rowIndex).isB();                               
                default:
                    return null;
            }
        }

        /**
         * used to set the values of the boolean part of the table. Player and brain cannnot be changed.
         * @param aValue the value to be inserted
         * @param rowIndex the row to be inserted on
         * @param columnIndex the column to be inserted on
         */
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 2:
                    if (players.get(rowIndex).isB())
                        players.get(rowIndex).setB(false);
                    else
                        players.get(rowIndex).setB(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(rootPane, "you shouldnt be here, but you're trying to change a column that can't be changed");
            }
        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
        
        /**
         * gets a player object from the table, at the specified index
         * @param index player entry to be obtained
         * @return the player that is at the index
         */
        public Player getPlayer(int index){
            return players.get(index).getP();
        }

        /**
         * used to add a new player to the table and so the model and data.
         * @param p the player to be added
         */
        public void addNewPlayer(Player p) {
            players.add(new player_boolean(p, false));
        }
        
        /**
         * used to delete a player entry from the table. Deletes the highlighted player 
         * @return int to represent error messages - 1 = success, 0 = failure 
         */
        public int deletePlayer (){
            int tbd = table_players.getSelectedRow();
            boolean b = players.remove(tbd).isB();
            table_players.updateUI();
            if ( b == true) 
                return 0;
            else 
                return 1;                
        }

        /**
         * function to call the sort sequence of the table
         */
        private void sort() {
            Collections.sort(players, new scoreComparer());
        }
        
        /**
         * function to get the player_boolean class that is stored at the index
         * @param index int that shows the place in the table the player is
         * @return the player_boolean stored at index
         */
        private player_boolean getPlayerBoolean(int index) {
            return players.get(index);
        }
    }
    
    /**
     * subclass to store players and booleans in a single class.  boolean represents if the player is taking part in the tournament
     * contains only simple setters and getters
     */
    class player_boolean{
        Player p; 
        boolean b;

        public player_boolean(Player p, boolean b) {
            this.p = p;
            this.b = b;            
        }

        public void setB(boolean b) {
            this.b = b;
        }        

        public Player getP() {
            return p;
        }

        public boolean isB() {
            return b;
        }
    }
    
    /**
     * compares the scores of player_boolean's so that the players can be ordered by score
     */
    class scoreComparer implements Comparator<player_boolean>{

        /**
         * the compare method to judge whether o1 or o2 is larger. Sorted by score, then by name alphabetically
         * @param o1 first object to compare
         * @param o2 second object to compare
         * @return 1 if o1 is greater, 0 if same, -1 if o1 is smaller
         */
        @Override
        public int compare(player_boolean o1, player_boolean o2) {  
            
            if ( o1.p.getScore() > o2.p.getScore())
                return 1;
            else if (o1.p.getScore() == o2.p.getScore())                
                return o1.p.getName().compareTo(o2.p.getName());
            else
                return -1;
            
        }
    }
}
