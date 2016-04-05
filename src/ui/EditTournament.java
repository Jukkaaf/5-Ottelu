package ui;

import objects.Tournament;
import objects.Match;

/**
 *
 * JFrame used for creating new tournaments and editing old ones.
 * 
 * @author Jukka
 */
public class EditTournament extends javax.swing.JFrame {  
    private int spinnerTeamLast = 1;
    private int spinnerRankLast = 1;
    
    private Tournament tournament;
    private Tournament tempTournament;
    
    private Match currentMatch;

    public EditTournament(Tournament tournament) {
        this.setResizable(false);
        initComponents();
        this.setLocationRelativeTo(null);
        this.tournament = tournament;
        
        tempTournament = new Tournament("temp");
        tempTournament.copyTournament(this.tournament);

        // load the current match
        guiLoadMatch(1);
        
        // update GUI elements
        textTournamentName.setText(tempTournament.getName());
        textMatchNO.setValue(tempTournament.getMatches());
        sliderMatchNO.setValue(tempTournament.getMatches());
        textGameName.setText(currentMatch.getName());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spinnerMatch = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        sliderMatchNO = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        textTournamentName = new javax.swing.JTextField();
        textMatchNO = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textGameName = new javax.swing.JTextField();
        sliderTeamAmmount = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spinnerTeam = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        spinnerRank = new javax.swing.JSpinner();
        textTeamAmmount = new javax.swing.JFormattedTextField();
        textTeamPlayer = new javax.swing.JFormattedTextField();
        textRankPoints = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Tournament");

        buttonSave.setText("Save");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        jLabel3.setText("Match NO.");

        spinnerMatch.setModel(new javax.swing.SpinnerNumberModel(1, 1, 25, 1));
        spinnerMatch.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerMatchStateChanged(evt);
            }
        });

        jLabel2.setText("Ammount of matches:");

        sliderMatchNO.setMajorTickSpacing(5);
        sliderMatchNO.setMaximum(25);
        sliderMatchNO.setMinorTickSpacing(1);
        sliderMatchNO.setPaintLabels(true);
        sliderMatchNO.setPaintTicks(true);
        sliderMatchNO.setSnapToTicks(true);
        sliderMatchNO.setToolTipText("");
        sliderMatchNO.setValue(5);
        sliderMatchNO.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderMatchNOStateChanged(evt);
            }
        });

        jLabel1.setText("Name:");

        textTournamentName.setText("Tournament1");
        textTournamentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTournamentNameActionPerformed(evt);
            }
        });

        textMatchNO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        textMatchNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textMatchNOActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("Ammount of teams:");

        jLabel4.setText("Name:");

        textGameName.setText("Game1");
        textGameName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textGameNameActionPerformed(evt);
            }
        });

        sliderTeamAmmount.setMajorTickSpacing(2);
        sliderTeamAmmount.setMaximum(12);
        sliderTeamAmmount.setMinorTickSpacing(1);
        sliderTeamAmmount.setPaintLabels(true);
        sliderTeamAmmount.setPaintTicks(true);
        sliderTeamAmmount.setValue(2);
        sliderTeamAmmount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sliderTeamAmmountMouseReleased(evt);
            }
        });

        jLabel6.setText("Team NO.");

        jLabel7.setText("Player ammount");

        spinnerTeam.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        spinnerTeam.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerTeamStateChanged(evt);
            }
        });

        jLabel10.setText("Points");

        jLabel11.setText("Rank");

        spinnerRank.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        spinnerRank.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerRankStateChanged(evt);
            }
        });

        textTeamAmmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        textTeamAmmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTeamAmmountActionPerformed(evt);
            }
        });

        textTeamPlayer.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        textRankPoints.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderTeamAmmount, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTeamAmmount))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textGameName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11)
                            .addComponent(spinnerTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(spinnerRank))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTeamPlayer)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textRankPoints))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textGameName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textTeamAmmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderTeamAmmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTeamPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textRankPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sliderMatchNO, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textMatchNO))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textTournamentName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textTournamentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textMatchNO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(sliderMatchNO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerMatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSave)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    *  Once the user is done with editing the temporary tournament object,
    *  if he or she chooses to save it the changes will be copied over to the actual tournament.
    */
    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
              // copy values from gui
        tempTournament.setName(textTournamentName.getText());
        tempTournament.setMatches(new Integer(textMatchNO.getText()));
        guiSaveMatch();
        // replace the main tournament objects data
        tournament.copyTournament(tempTournament);
        dispose();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void textTournamentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTournamentNameActionPerformed
        tempTournament.setName(textTournamentName.getText());
    }//GEN-LAST:event_textTournamentNameActionPerformed

    private void textMatchNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMatchNOActionPerformed
        if(new Integer(textMatchNO.getText()) != tempTournament.getMatches()){
            guiUpdateTournament(new Integer(textMatchNO.getText()));
        }
    }//GEN-LAST:event_textMatchNOActionPerformed

    private void sliderMatchNOStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderMatchNOStateChanged
        guiUpdateTournament(sliderMatchNO.getValue());      
    }//GEN-LAST:event_sliderMatchNOStateChanged

    private void textGameNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textGameNameActionPerformed
        currentMatch.setName(textGameName.getText());
    }//GEN-LAST:event_textGameNameActionPerformed
    /**
    *  Used for changing the match that is currently being viewed.
    */
    private void spinnerMatchStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerMatchStateChanged
        Integer value = (Integer)spinnerMatch.getValue();
        // check that spinner is in limits
        if(value > tempTournament.getMatches()){
            value = tempTournament.getMatches();
            spinnerMatch.setValue(value);
        }
        // then save the match info and load the new one in
        guiSaveMatch();
        guiLoadMatch(value);
    }//GEN-LAST:event_spinnerMatchStateChanged
    
    private void textTeamAmmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTeamAmmountActionPerformed
        if(new Integer(textTeamAmmount.getText()) != currentMatch.getTeamsAmmount()){
            guiUpdateMatch(new Integer(textTeamAmmount.getText()));
        }      
    }//GEN-LAST:event_textTeamAmmountActionPerformed
    /**
    *  Used for changing the team that is currently being viewed.
    */
    private void spinnerTeamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerTeamStateChanged
        Integer value = (Integer)spinnerTeam.getValue();
        if(value > currentMatch.getTeamsAmmount()){
            value = currentMatch.getTeamsAmmount();
            spinnerTeam.setValue(currentMatch.getTeamsAmmount());
        }
        // save the last teams player ammount       
        guiSaveTeamLast();
        
        // load next playerammount
        textTeamPlayer.setValue(currentMatch.getTeamSizeIndex(value - 1));
        spinnerTeamLast = value;
    }//GEN-LAST:event_spinnerTeamStateChanged
    /**
    *  Used for selecting what ranks points are viewed at the moment.
    */
    private void spinnerRankStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerRankStateChanged
        Integer value = (Integer)spinnerRank.getValue();
        if(value > currentMatch.getTeamsAmmount()){
            value = currentMatch.getTeamsAmmount();
            spinnerRank.setValue(currentMatch.getTeamsAmmount());
        }
        // save the last ranks points
        guiSaveRankLast();
        
        // load next rank
        textRankPoints.setValue(currentMatch.getPointsIndex(value - 1));
        spinnerRankLast = value;
    }//GEN-LAST:event_spinnerRankStateChanged
    /**
    *  Updates the match when ever the user clicks the team amount slider.
    */
    private void sliderTeamAmmountMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderTeamAmmountMouseReleased
        guiUpdateMatch(sliderTeamAmmount.getValue()); 
    }//GEN-LAST:event_sliderTeamAmmountMouseReleased
    /**
    *  Saves all the current matches info.
    */
    private void guiSaveMatch(){
        currentMatch.setName(textGameName.getText());
        guiSaveTeamLast();
        guiSaveRankLast();
    }
    /**
    *  When the user changes what teams player amount is being shown, 
    *   this function saves the last ones player amount.
    */
    private void guiSaveTeamLast(){
        String temp = textTeamPlayer.getText();
        if(!temp.equals("")){
            Integer playerAmmount = new Integer(temp);
            currentMatch.setTeamSizeIndex(spinnerTeamLast - 1, playerAmmount);
        }
    }
    /**
    *  When the user changes what ranks points are being shown,
    *   this function saves the last ones points.
    */
    private void guiSaveRankLast(){
        String temp = textRankPoints.getText();
        if(!temp.equals("")){
            Integer point = new Integer(temp);
            currentMatch.setPointsIndex(spinnerRankLast - 1, point);
        }
    }
    /**
    *  When actions in the ui are performed
    *  this function makes sure everything is in limits and synced.
    */
    private void guiUpdateMatch(int value){
        // check that the given value is in the limits
        if(value > tournament.getMAXTEAMS()){
            value = tournament.getMAXTEAMS();
        }
        else if(value < 1){
            value = 1;
        }
        // sync the textbox and slider        
        // check values to avoid loop, statechange
        if(value != sliderTeamAmmount.getValue()){
            sliderTeamAmmount.setValue(value);
        }           
        textTeamAmmount.setValue(value);
        
        // save the player ammount and points for the currently visible ones 
        guiSaveTeamLast();
        guiSaveRankLast();       
        
        // update the match with the new value
        currentMatch.updateMatchLists(value);
        
        // update the window
        guiUpdateTeamAndRank();
    }
    /**
    *  Syncs ui elements and makes sure things are in limits.
    */
    private void guiUpdateTournament(int value){
        if(value > tournament.getMAXMATCHES()){
            value = tournament.getMAXMATCHES();
        }
        else if(value < 1){
            value = 1;
        }        
        textMatchNO.setValue(value);
        // check values to avoid loop, statechange
        if(value != sliderMatchNO.getValue()){
            sliderMatchNO.setValue(value);
        }  
        tempTournament.updateMatchList(value);
    }
    /**
    *  Load the selected matches information.
    */
    private void guiLoadMatch(int value){
        currentMatch = tempTournament.getMatchList().get(value - 1);
        textGameName.setText(currentMatch.getName());
        textTeamAmmount.setValue(currentMatch.getTeamsAmmount());

        // check values to avoid loop, statechange
        if(currentMatch.getTeamsAmmount() != sliderTeamAmmount.getValue()){
            sliderTeamAmmount.setValue(currentMatch.getTeamsAmmount());
        }  
                
        guiUpdateTeamAndRank();
    }
    /**
    *  As a new match is loaded the team and rank spinners are reset by this method.
    */
    private void guiUpdateTeamAndRank(){  
        if(1 != (Integer)spinnerTeam.getValue()){
            spinnerTeam.setValue(1);
        }
        if(1 != (Integer)spinnerRank.getValue()){
            spinnerRank.setValue(1);
        }
        
        spinnerTeamLast = 1;
        spinnerRankLast = 1;
        textTeamPlayer.setValue(currentMatch.getTeamSizeIndex(0));
        textRankPoints.setValue(currentMatch.getPointsIndex(0));
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider sliderMatchNO;
    private javax.swing.JSlider sliderTeamAmmount;
    private javax.swing.JSpinner spinnerMatch;
    private javax.swing.JSpinner spinnerRank;
    private javax.swing.JSpinner spinnerTeam;
    private javax.swing.JTextField textGameName;
    private javax.swing.JFormattedTextField textMatchNO;
    private javax.swing.JFormattedTextField textRankPoints;
    private javax.swing.JFormattedTextField textTeamAmmount;
    private javax.swing.JFormattedTextField textTeamPlayer;
    private javax.swing.JTextField textTournamentName;
    // End of variables declaration//GEN-END:variables
}
