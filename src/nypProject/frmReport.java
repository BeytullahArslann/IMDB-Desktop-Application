
package nypProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class frmReport extends javax.swing.JFrame {

    
    public frmReport() {
        initComponents();
    }
    public String user;
    public int commentID;
    public frmReport(String user,int commentID) {
        initComponents();
        this.user = user;
        this.commentID=commentID;
    }
    
    
    
    public void report(int operation){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        String message = null;
        if(operation == 1){
            message = "ID Sİ "+commentID+ " OLAN YORUM KÜFÜR İÇERMEKTEDİR";
        }else if(operation == 2){
            message = "ID Sİ "+commentID+ " OLAN YORUM SPOİLER İÇERMEKTEDİR";
        }
        String Sql ="insert into nyp_scheme.tblmessages (senderID,receiverID,message,messageDate,status) values((select userID from nyp_scheme.tblusers where userNickName='"+user.toString()+"'),2,'"+message.toString()+"',?,1);";
        try{
            connection = dbHelper.getConnection();
           
            
            preparedstatement = connection.prepareStatement(Sql);
            preparedstatement.setString(1,date.toString());
            int result =preparedstatement.executeUpdate();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try {
                preparedstatement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSpoiler = new javax.swing.JButton();
        btnRudeWord = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSpoiler.setText("SPOİLER İÇEREN YORUM");
        btnSpoiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpoilerActionPerformed(evt);
            }
        });

        btnRudeWord.setText("KÜFÜR İÇEREN YORUM");
        btnRudeWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRudeWordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRudeWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSpoiler, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnSpoiler, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRudeWord, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSpoilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpoilerActionPerformed
        report(2);
        this.setVisible(false);
    }//GEN-LAST:event_btnSpoilerActionPerformed

    private void btnRudeWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRudeWordActionPerformed
        report(1);
        this.setVisible(false);
    }//GEN-LAST:event_btnRudeWordActionPerformed

    
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
            java.util.logging.Logger.getLogger(frmReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRudeWord;
    private javax.swing.JButton btnSpoiler;
    // End of variables declaration//GEN-END:variables
}
