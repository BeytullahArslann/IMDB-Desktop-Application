/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author beytu
 */
public class frmMovieManager extends javax.swing.JFrame {
        
        DefaultTableModel model;
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset=null;
        
    
         
    public frmMovieManager() {
        initComponents();
        tblMovies();
        
    }
    public String user;
    public int Authority;
    public frmMovieManager(String user,int Authority) {
        this.user=user;
        this.Authority=Authority;
        initComponents();
        tblMovies();
        if(Authority==1){
            pnlUser.hide();
        }else if(Authority==0){
            pnlAdmin.hide();
        }
    }
    public void watchlistcontrol(int status){
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        ResultSet resultSet = null;
        //String Sql ="Select userID, movieID, status from tblwatch where userID=(select userID from tblusers where userNickName='"+user+"') and movieID="+lblMovieID.getText()+" and status="+status+"";
        String Sql ="Select userID, movieID from tblwatch where userID=(select userID from tblusers where userNickName='"+user+"') and movieID="+lblMovieID.getText()+"";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            resultset = preparedstatement.executeQuery();
            if(resultset.next()){
                JOptionPane.showMessageDialog(null,"FİLM ZATEN EKLİ");
            }else{
                
                
                watchlist(status);
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
    public void watchlist(int status){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        String Sql ="insert into tblwatch (userID,movieID,date,status) values((select userID from tblusers where userNickName='"+user+"'),'"+lblMovieID.getText()+"',?,"+status+")";
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
                tblMovies();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
        public void tblMovies(){
            
            model = (DefaultTableModel)tblMovies.getModel();
            model.setRowCount(0);
        try {
            ArrayList<Movie> movies = getMovies();
            for(Movie movie : movies){
                Object[] row = { movie.getId(),movie.getName(),movie.getDirector(),movie.getWriters(),movie.getImdbscore(),movie.getRelasedate(),movie.getType()};
                model.addRow(row);
                //System.out.println("film:"+ movie.getName());
            }
        } catch (SQLException ex) {
           // Logger.getLogger(frmMovieManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Movie> getMovies() throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Movie> movies=null;
        
       try{
           connection = dbHelper.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery("Select * from tblmovies");
           movies = new ArrayList<Movie>();
           while(resultSet.next()){
               movies.add(new Movie(
                       resultSet.getInt("movieID"),
                       resultSet.getString("movieName"),
                       resultSet.getString("movieDirector"),
                       resultSet.getString("movieWriters"),
                       resultSet.getInt("movieIMDBScore"),
                       resultSet.getString("movieReleaseDate"),
                       resultSet.getString("movieType")
                    ));
           }
       }catch(SQLException exception){
             dbHelper.showErrorMessage(exception);
        }finally{
            statement.close();
            connection.close();
        }
        return movies;
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
        tblMovies = new javax.swing.JTable();
        txtSearchMovies = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        pnlAdmin = new javax.swing.JPanel();
        txtType = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDirector = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnUpdateMovie = new javax.swing.JButton();
        txtWriter = new javax.swing.JTextField();
        btnDeleteMovie = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnAddMovie = new javax.swing.JButton();
        txtIMDB = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        pnlUser = new javax.swing.JPanel();
        lblID1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMovieID = new javax.swing.JLabel();
        lblMovieName = new javax.swing.JLabel();
        lblMovieDirector = new javax.swing.JLabel();
        lblMovieWriter = new javax.swing.JLabel();
        lblMovieIMDB = new javax.swing.JLabel();
        lblMovieDate = new javax.swing.JLabel();
        lblMovieType = new javax.swing.JLabel();
        btnAddWatchList = new javax.swing.JButton();
        btnAddWatchHistory = new javax.swing.JButton();
        btnComments = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblMovies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FİLM AD", "YÖNETMEN", "YAZAR", "IMDB PUAN", "YAYINLANMA TARİHİ", "TÜR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMoviesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMovies);

        txtSearchMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchMoviesActionPerformed(evt);
            }
        });
        txtSearchMovies.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMoviesKeyReleased(evt);
            }
        });

        jLabel1.setText("ARA");

        btnBack.setText("<- GERİ");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel5.setText("YÖNETMEN");

        jLabel6.setText("YAZAR");

        btnUpdateMovie.setText("GÜNCELLE");
        btnUpdateMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMovieActionPerformed(evt);
            }
        });

        btnDeleteMovie.setText("SİL");
        btnDeleteMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMovieActionPerformed(evt);
            }
        });

        jLabel7.setText("IMDB PUAN");

        btnAddMovie.setText("EKLE");
        btnAddMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMovieActionPerformed(evt);
            }
        });

        jLabel8.setText("TARİH");

        jLabel.setText("FİLM ID");

        jLabel9.setText("TÜR");

        jLabel4.setText("FİLM AD");

        jLabel2.setText("YÖNETİCİ PANELİ");

        javax.swing.GroupLayout pnlAdminLayout = new javax.swing.GroupLayout(pnlAdmin);
        pnlAdmin.setLayout(pnlAdminLayout);
        pnlAdminLayout.setHorizontalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminLayout.createSequentialGroup()
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlAdminLayout.createSequentialGroup()
                        .addComponent(btnAddMovie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateMovie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteMovie))
                    .addComponent(txtType, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtDate)
                    .addComponent(txtIMDB)
                    .addComponent(txtWriter)
                    .addComponent(txtDirector)
                    .addComponent(txtName)
                    .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 41, Short.MAX_VALUE))
            .addGroup(pnlAdminLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAdminLayout.setVerticalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAdminLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtWriter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIMDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMovie)
                    .addComponent(btnUpdateMovie)
                    .addComponent(btnDeleteMovie)))
        );

        lblID1.setText("FİLM ID");

        jLabel10.setText("FİLM AD");

        jLabel11.setText("YÖNETMEN");

        jLabel12.setText("YAZAR");

        jLabel13.setText("IMDB PUAN");

        jLabel14.setText("TARİH");

        jLabel15.setText("TÜR");

        btnAddWatchList.setText("DAHA SONRA İZLE");
        btnAddWatchList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWatchListActionPerformed(evt);
            }
        });

        btnAddWatchHistory.setText("İZLEDİM");
        btnAddWatchHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWatchHistoryActionPerformed(evt);
            }
        });

        btnComments.setText("YORUMLAR");
        btnComments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommentsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblID1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMovieName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieWriter, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieDate, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieType, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieID, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieIMDB, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(btnAddWatchList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddWatchHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblID1)
                    .addComponent(lblMovieID, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(lblMovieName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMovieDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserLayout.createSequentialGroup()
                        .addComponent(lblMovieWriter, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(lblMovieIMDB, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMovieDate, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblMovieType, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddWatchList)
                    .addComponent(btnAddWatchHistory)
                    .addComponent(btnComments))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchMovies)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(pnlUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchMovies, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMoviesActionPerformed
        
    }//GEN-LAST:event_txtSearchMoviesActionPerformed

    private void txtSearchMoviesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMoviesKeyReleased
        
        String searchKey = txtSearchMovies.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter =
                new TableRowSorter<DefaultTableModel>(model);
        tblMovies.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtSearchMoviesKeyReleased

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
       if(Authority==0){
           this.setVisible(false);
           new frmUser(user,Authority).setVisible(true);
       }else if(Authority==1){
           this.setVisible(false);
           new frmAdmin(user,Authority).setVisible(true);
       }
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMovieActionPerformed
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        //connection =userIdentifyConnection.connectUser(); 
        
        String Sql ="insert into tblmovies (movieName,movieDirector,movieWriters,movieIMDBScore,movieReleaseDate,movieType) values(?,?,?,?,?,?)";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            preparedstatement.setString(1,txtName.getText() );
            preparedstatement.setString(2,txtDirector.getText());
            preparedstatement.setString(3,txtWriter.getText());
            preparedstatement.setInt(4,Integer.valueOf(txtIMDB.getText()));
            preparedstatement.setString(5,txtDate.getText());
            preparedstatement.setString(6,txtType.getText());
            int result =preparedstatement.executeUpdate();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try {
                preparedstatement.close();
                connection.close();
                tblMovies();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnAddMovieActionPerformed

    private void btnUpdateMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMovieActionPerformed
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        //connection =userIdentifyConnection.connectUser(); 
        //insert into tblmovies (movieName,movieDirector,movieWriters,movieIMDBScore,movieReleaseDate,movieType) values(?,?,?,?,?,?)
        String Sql ="update tblmovies set movieName=?,movieDirector=?,movieWriters=?,movieIMDBScore=?,movieReleaseDate=?,movieType=? where movieID=?";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            preparedstatement.setString(1,txtName.getText() );
            preparedstatement.setString(2,txtDirector.getText());
            preparedstatement.setString(3,txtWriter.getText());
            preparedstatement.setInt(4,Integer.valueOf(txtIMDB.getText()));
            preparedstatement.setString(5,txtDate.getText());
            preparedstatement.setString(6,txtType.getText());
            preparedstatement.setInt(7,Integer.valueOf(lblID.getText()));
            int result =preparedstatement.executeUpdate();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try {
                preparedstatement.close();
                connection.close();
                tblMovies();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnUpdateMovieActionPerformed

    private void btnDeleteMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMovieActionPerformed
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        //connection =userIdentifyConnection.connectUser(); 
        //insert into tblmovies (movieName,movieDirector,movieWriters,movieIMDBScore,movieReleaseDate,movieType) values(?,?,?,?,?,?)
        String Sql ="delete from tblmovies where movieID=?";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            
            preparedstatement.setInt(1,Integer.valueOf(lblID.getText()));
            int result =preparedstatement.executeUpdate();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try {
                preparedstatement.close();
                connection.close();
                tblMovies();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteMovieActionPerformed

    private void tblMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMoviesMouseClicked
       int number = tblMovies.getSelectedRow();
        lblID.setText(tblMovies.getValueAt(number , 0).toString());
        txtName.setText(tblMovies.getValueAt(number , 1).toString());
        txtDirector.setText(tblMovies.getValueAt(number , 2).toString());
        txtWriter.setText(tblMovies.getValueAt(number , 3).toString());
        txtIMDB.setText(tblMovies.getValueAt(number , 4).toString());
        txtDate.setText(tblMovies.getValueAt(number , 5).toString());
        txtType.setText(tblMovies.getValueAt(number , 6).toString());
        lblMovieID.setText(tblMovies.getValueAt(number , 0).toString());
        lblMovieName.setText(tblMovies.getValueAt(number , 1).toString());
        lblMovieDirector.setText(tblMovies.getValueAt(number , 2).toString());
        lblMovieWriter.setText(tblMovies.getValueAt(number , 3).toString());
        lblMovieIMDB.setText(tblMovies.getValueAt(number , 4).toString());
        lblMovieDate.setText(tblMovies.getValueAt(number , 5).toString());
        lblMovieType.setText(tblMovies.getValueAt(number , 6).toString());
    }//GEN-LAST:event_tblMoviesMouseClicked

    private void btnAddWatchListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWatchListActionPerformed
        watchlistcontrol(0);
    }//GEN-LAST:event_btnAddWatchListActionPerformed

    private void btnAddWatchHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWatchHistoryActionPerformed
        watchlistcontrol(1);
    }//GEN-LAST:event_btnAddWatchHistoryActionPerformed

    private void btnCommentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommentsActionPerformed
     
      this.setVisible(false);
      new frmMovieComments(user,Authority,lblMovieName.getText()).setVisible(true);
    }//GEN-LAST:event_btnCommentsActionPerformed

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
            java.util.logging.Logger.getLogger(frmMovieManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMovieManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMovieManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMovieManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMovieManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMovie;
    private javax.swing.JButton btnAddWatchHistory;
    private javax.swing.JButton btnAddWatchList;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnComments;
    private javax.swing.JButton btnDeleteMovie;
    private javax.swing.JButton btnUpdateMovie;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblID1;
    private javax.swing.JLabel lblMovieDate;
    private javax.swing.JLabel lblMovieDirector;
    private javax.swing.JLabel lblMovieID;
    private javax.swing.JLabel lblMovieIMDB;
    private javax.swing.JLabel lblMovieName;
    private javax.swing.JLabel lblMovieType;
    private javax.swing.JLabel lblMovieWriter;
    private javax.swing.JPanel pnlAdmin;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JTable tblMovies;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDirector;
    private javax.swing.JTextField txtIMDB;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearchMovies;
    private javax.swing.JTextField txtType;
    private javax.swing.JTextField txtWriter;
    // End of variables declaration//GEN-END:variables
}
