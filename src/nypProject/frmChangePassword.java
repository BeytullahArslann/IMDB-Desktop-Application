
package nypProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class frmChangePassword extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement preparedstatement = null;
    ResultSet resultset=null;
    
    public frmChangePassword() {
        initComponents();
    }
    private String userPassword;
    public String user;
    public int Authority;
    public frmChangePassword(String user,String userPassword,int Authority) {
        initComponents();
        this.userPassword = userPassword;
        this.user = user;
        this.Authority=Authority;
    }
    public void passwordControl(){
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        String Sql = null;
        Sql ="Select userPassword from tblusers where userNickName='"+user+"' and userPassword =?";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            preparedstatement.setString(1, txtCurrentPassword.getText());
            resultset = preparedstatement.executeQuery();
            if(resultset.next()){
                JOptionPane.showMessageDialog(null,"ŞİFRE BAŞARILI BİR ŞEKİLDE GÜNCELLENDİ");
                updatePassword();
                this.setVisible(false);
                new frmUserSettings(user,Authority).setVisible(true);
            }else{
                
                JOptionPane.showMessageDialog(null,"MEVCUT ŞİFRE YANLIŞ");
                
            }
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
    public void updatePassword(){
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        String Sql = null;
        try{
            
            Sql = "update tblusers set userPassword=? where userNickName = '"+user+"'";
            
                connection = dbHelper.getConnection();
                preparedstatement = connection.prepareStatement(Sql);
                preparedstatement.setString(1,txtNewPassword.getText());
                userPassword = txtNewPassword.getText();
                int result =preparedstatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(frmUserSettings.class.getName()).log(Level.SEVERE, null, ex);
            
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckPassword = new javax.swing.JCheckBox();
        txtCurrentPassword = new javax.swing.JPasswordField();
        txtNewPassword = new javax.swing.JPasswordField();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("MEVCUT ŞİFRE");

        jLabel2.setText("YENİ ŞİFRE");

        jCheckPassword.setBackground(new java.awt.Color(255, 250, 250));
        jCheckPassword.setText("ŞİFREYİ GÖSTER");
        jCheckPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckPasswordActionPerformed(evt);
            }
        });

        btnUpdate.setText("GÜNCELLE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckPassword)
                            .addComponent(txtNewPassword)
                            .addComponent(txtCurrentPassword)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckPasswordActionPerformed
        if(jCheckPassword.isSelected()){
            txtCurrentPassword.setEchoChar((char)0);
            txtNewPassword.setEchoChar((char)0);
        }else{
            txtCurrentPassword.setEchoChar('*');
            txtNewPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckPasswordActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        passwordControl();
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(frmChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox jCheckPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtCurrentPassword;
    private javax.swing.JPasswordField txtNewPassword;
    // End of variables declaration//GEN-END:variables
}
