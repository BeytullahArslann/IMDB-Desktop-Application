/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypProject;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author beytu
 */
public class frmLogin extends javax.swing.JFrame {
    Connection connection = null;
    PreparedStatement preparedstatement = null;
    ResultSet resultset=null;
    //Authority authority = new Authority();
    /**
     * Creates new form frmLogin
     */
    public frmLogin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnRegister = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNickName = new javax.swing.JTextField();
        jCheckPassword = new javax.swing.JCheckBox();
        txtPassword = new javax.swing.JPasswordField();
        btnJoin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(getBackground());

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setForeground(new java.awt.Color(255, 0, 102));
        jPanel3.setLayout(null);

        btnRegister.setBackground(new java.awt.Color(51, 51, 255));
        btnRegister.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btnRegister.setText("KAYIT OL");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegister);
        btnRegister.setBounds(233, 171, 80, 23);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("KULLANICI ADI");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(13, 52, 130, 22);

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("ŞİFRE");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(73, 91, 70, 30);
        jPanel3.add(txtNickName);
        txtNickName.setBounds(161, 51, 135, 29);

        jCheckPassword.setForeground(new java.awt.Color(240, 240, 240));
        jCheckPassword.setText("ŞİFREYİ GÖSTER");
        jCheckPassword.setOpaque(false);
        jCheckPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckPasswordActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckPassword);
        jCheckPassword.setBounds(161, 130, 130, 23);
        jPanel3.add(txtPassword);
        txtPassword.setBounds(161, 93, 135, 30);

        btnJoin.setBackground(new java.awt.Color(255, 51, 51));
        btnJoin.setForeground(new java.awt.Color(240, 240, 240));
        btnJoin.setText("GİRİŞ");
        btnJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinActionPerformed(evt);
            }
        });
        jPanel3.add(btnJoin);
        btnJoin.setBounds(115, 171, 88, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        this.setVisible(false);
        new frmRegister().setVisible(true);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void jCheckPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckPasswordActionPerformed
        if(jCheckPassword.isSelected()){
            txtPassword.setEchoChar((char)0);
        }else{
            txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckPasswordActionPerformed

    private void btnJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinActionPerformed
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        
        //connection =userIdentifyConnection.connectUser(); 
        
        String Sql ="Select userNickName, userPassword, userAuthority from tblusers where userNickName=? and userPassword=? and userAuthority=?";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            preparedstatement.setString(1, txtNickName.getText());
            preparedstatement.setString(2,txtPassword.getText());
            preparedstatement.setString(3,"1");
            resultset = preparedstatement.executeQuery();
            
            if(resultset.next()){
                JOptionPane.showMessageDialog(null,"GİRİŞ BAŞARILI");
                String user = txtNickName.getText();
                this.setVisible(false);
                new frmAdmin(user,1).setVisible(true);
                
                return;
            }
            //preparedstatement.setString(1, txtNickName.getText());
            //preparedstatement.setString(2,txtPassword.getText());
            preparedstatement.setString(3,"0");
            resultset = preparedstatement.executeQuery();
            if(resultset.next()){
                JOptionPane.showMessageDialog(null,"GİRİŞ BAŞARILI");
                String user = txtNickName.getText();
                this.setVisible(false);
                new frmUser(user,0).setVisible(true);
                
                return;
            }
            
            preparedstatement.setString(3,"2");
            resultset = preparedstatement.executeQuery();
            if(resultset.next()){
                JOptionPane.showMessageDialog(null,"HESABINIZ BANLANMIŞTIR");
                
            }else{
                JOptionPane.showMessageDialog(null,"GİRİŞ BAŞARISIZ");
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

    }//GEN-LAST:event_btnJoinActionPerformed

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJoin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JCheckBox jCheckPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtNickName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}