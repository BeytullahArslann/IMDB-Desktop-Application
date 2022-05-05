
package nypProject;

import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.modelmbean.ModelMBean;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class frmMessages extends javax.swing.JFrame {

    DefaultTableModel model;
    PreparedStatement preparedstatement = null;
    
    public frmMessages() {
        initComponents();
        tblMessages();
    }
    public String user;
    public int Authority;
    public int Operation;
    public frmMessages(String user,int Authority,int Operation){
        this.user=user;
        this.Authority=Authority;
        this.Operation=Operation;
        initComponents();
        tblMessages();
        
    }

    public void tblMessages(){
            
            model = (DefaultTableModel)tblMessages.getModel();
            model.setRowCount(0);
        try {
            ArrayList<Message> messages = getMessages();
            for(Message message : messages){
                Object[] row = { message.getUserNickName(),message.getMessage(),message.getDate()};        
                model.addRow(row);
                //System.out.println("film:"+ movie.getName());
            }
        } catch (SQLException ex) {
           // Logger.getLogger(frmMovieManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    
    
        public ArrayList<Message> getMessages() throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Message> messages=null;
        //String sql ="Select * from nyp_scheme.tblmessages where receiverID=(select userID from nyp_scheme.tblusers where userNickName='"+ userr +"');";
        String sql ="Select * from nyp_scheme.tblmessages LEFT JOIN nyp_scheme.tblusers on tblmessages.senderID=tblusers.userID where receiverID=(select userID from nyp_scheme.tblusers where userNickName='"+ user +"') and status = "+Operation+";";
        System.out.print(user);
        System.out.print(sql);
        //String sql ="Select * from nyp_scheme.tblmessages where receiverID=(select userID from nyp_scheme.tblusers where userNickName='admin');";
       try{
           connection = dbHelper.getConnection();
           statement = connection.createStatement();
           //System.out.print(userr+"1");
           resultSet = statement.executeQuery(sql);
           //preparedstatement = connection.prepareStatement(sql);
           //preparedstatement.setInt(1, 2);
           messages = new ArrayList<Message>();
           while(resultSet.next()){
               messages.add(new Message(
                       resultSet.getInt("messageID"),
                       resultSet.getInt("senderID"),
                       resultSet.getInt("receiverID"),
                       resultSet.getString("message"),
                       resultSet.getString("messagedate"),
                       resultSet.getInt("userID"),
                       resultSet.getString("userName"),
                       resultSet.getString("userSurname"),
                       resultSet.getString("userNickName"),
                       resultSet.getString("userPassword"),
                       resultSet.getString("userEmail"),
                       resultSet.getString("userBirthDate"),
                       resultSet.getInt("userAuthority")
                       
                    ));
           }
       }catch(SQLException exception){
             dbHelper.showErrorMessage(exception);
        }finally{
            statement.close();
            connection.close();
        }
        return messages;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMessages = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        lblSender = new javax.swing.JLabel();
        txtSearchMessages = new javax.swing.JTextField();
        lblMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtReceiver = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        btnDeleteMessage = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblMessageDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblMessages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Gönderici", "Mesaj", "Tarih"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMessages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMessagesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMessages);

        btnBack.setText("<- GERİ");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtSearchMessages.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMessagesKeyReleased(evt);
            }
        });

        jLabel1.setText("GÖNDERİCİ");

        jLabel2.setText("MESAJ");

        jLabel3.setText("MESAJ");

        jLabel4.setText("ALICI");

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        jScrollPane2.setViewportView(txtMessage);

        btnSend.setText("GÖNDER");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnDeleteMessage.setText("MESAJI SİL");
        btnDeleteMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMessageActionPerformed(evt);
            }
        });

        jLabel5.setText("TARİH");

        jLabel6.setText("ARA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBack)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnDeleteMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(30, 30, 30)
                                            .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(lblSender, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMessageDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                            .addComponent(txtReceiver))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchMessages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMessageDate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addComponent(lblSender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteMessage)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        if(Authority==0){
            this.setVisible(false);
            new frmUser(user,Authority).setVisible(true);
        }else if(Authority==1){
            this.setVisible(false);
            new frmAdmin(user,Authority).setVisible(true);
        }   
            
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtSearchMessagesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMessagesKeyReleased
        String searchKey = txtSearchMessages.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(model);
        tblMessages.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtSearchMessagesKeyReleased

    private void tblMessagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMessagesMouseClicked
        int number = tblMessages.getSelectedRow();
        lblSender.setText(tblMessages.getValueAt(number , 0).toString());
        lblMessage.setText(tblMessages.getValueAt(number , 1).toString());
        txtReceiver.setText(tblMessages.getValueAt(number , 0).toString());
        lblMessageDate.setText(tblMessages.getValueAt(number , 2).toString());
    }//GEN-LAST:event_tblMessagesMouseClicked

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        //connection =userIdentifyConnection.connectUser();
        String receiver = txtReceiver.getText();
        String Sql ="insert into nyp_scheme.tblmessages (senderID,receiverID,message,messageDate) values((select userID from nyp_scheme.tblusers where userNickName='"+user.toString()+"'),(select userID from nyp_scheme.tblusers where userNickName='"+receiver.toString()+"'),?,?);";
        //String Sql ="insert into tblmessages (senderID,receiverID,message,messagesDate) values(?,?,?,?)";
        //String Sql1 ="Select userID from tblusers where userNickName = '"+ receiver +"';";
        //String Sql2 ="Select userID from tblusers where userNickName = '"+ userr +"';";
        try{
            connection = dbHelper.getConnection();
           
            
            preparedstatement = connection.prepareStatement(Sql);
            //preparedstatement.setINT(1,receiverID);
            preparedstatement.setString(2,date.toString());
            preparedstatement.setString(1,txtMessage.getText());
            
            int result =preparedstatement.executeUpdate();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try {
                preparedstatement.close();
                connection.close();
                tblMessages();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnDeleteMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMessageActionPerformed
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        String sender = lblSender.getText();
        String message = lblMessage.getText();
        String messageDate = lblMessageDate.getText();
        System.out.print(sender+message+messageDate);
        String Sql ="delete from nyp_scheme.tblmessages where senderID=(select userID from nyp_scheme.tblusers where userNickName='"+sender.toString()+"') and message='"+message.toString()+"' and messageDate ='"+messageDate.toString()+"';";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            int result =preparedstatement.executeUpdate();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try {
                preparedstatement.close();
                connection.close();
                tblMessages();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteMessageActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMessages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMessages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMessages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMessages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMessages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDeleteMessage;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblMessageDate;
    private javax.swing.JLabel lblSender;
    private javax.swing.JTable tblMessages;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextField txtReceiver;
    private javax.swing.JTextField txtSearchMessages;
    // End of variables declaration//GEN-END:variables
}
