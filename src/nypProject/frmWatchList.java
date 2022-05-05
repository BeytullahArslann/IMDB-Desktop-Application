
package nypProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class frmWatchList extends javax.swing.JFrame {
       DefaultTableModel model;
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset=null;
    
    public frmWatchList() {
        initComponents();
        tblMovies();
    }
    public String user;
    public int Authority;
    public int status;
    public frmWatchList(String user,int Authority,int status) {
        this.user=user;
        this.Authority=Authority;
        this.status=status;
        initComponents();
        tblMovies();
        if(status==0){
            pnlWatchHistory.hide();
        }else if(status==1){
            pnlWatchList.hide();
        }
    }
    public void watchlistcontrol(int status){
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        String Sql ="Select userID, movieID from watchlist where userID=(select userID from tblusers where userNickName='"+user+"') and movieID="+lblMovieID.getText()+"";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            resultset = preparedstatement.executeQuery();
            if(resultset.next()){
                watchlist(status);
            }else{
                
                JOptionPane.showMessageDialog(null,"FİLM ZATEN EKLİ");
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
        //connection =userIdentifyConnection.connectUser(); 
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
                //clear();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void clear(){
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        String Sql ="delete from tblwatch where movieID="+lblMovieID.getText()+" and tblwatch.userID=(select userID from tblusers where userNickName='"+user+"');";
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
                tblMovies();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void tblMovies(){
            
            model = (DefaultTableModel)tblWatchList.getModel();
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
    public ArrayList<Movie> getMovies() throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Movie> movies=null;
        
       try{
           connection = dbHelper.getConnection();
           statement = connection.createStatement();
           resultSet = statement.executeQuery("SELECT tblmovies.movieID,movieName,movieDirector,movieWriters,movieIMDBScore,movieType,movieReleaseDate\n" +
            "FROM tblwatch\n" +
            "RIGHT JOIN tblmovies\n" +
            "ON tblwatch.movieID = tblmovies.movieID\n" +
            "where tblwatch.status="+status+" and tblwatch.userID=(select userID from tblusers where userNickName='"+user+"')");
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblWatchList = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        pnlWatchList = new javax.swing.JPanel();
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
        btnAddWatchHistory = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtSearchMovies = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlWatchHistory = new javax.swing.JPanel();
        lblID2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblMovieID1 = new javax.swing.JLabel();
        lblMovieName1 = new javax.swing.JLabel();
        lblMovieDirector1 = new javax.swing.JLabel();
        lblMovieWriter1 = new javax.swing.JLabel();
        lblMovieIMDB1 = new javax.swing.JLabel();
        lblMovieDate1 = new javax.swing.JLabel();
        lblMovieType1 = new javax.swing.JLabel();
        btnAddWatchHistory1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        btnSendComment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblWatchList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "FİLM ID", "FİLM AD", "YÖNETMEN", "YAZAR", "IMDB", "YAYINLANMA TARİHİ", "TÜR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWatchList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblWatchListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblWatchList);

        btnBack.setText("<- GERİ");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblID1.setText("FİLM ID");

        jLabel10.setText("FİLM AD");

        jLabel11.setText("YÖNETMEN");

        jLabel12.setText("YAZAR");

        jLabel13.setText("IMDB PUAN");

        jLabel14.setText("TARİH");

        jLabel15.setText("TÜR");

        btnAddWatchHistory.setText("İZLEDİM");
        btnAddWatchHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWatchHistoryActionPerformed(evt);
            }
        });

        btnDelete.setText("SİL");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlWatchListLayout = new javax.swing.GroupLayout(pnlWatchList);
        pnlWatchList.setLayout(pnlWatchListLayout);
        pnlWatchListLayout.setHorizontalGroup(
            pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWatchListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWatchListLayout.createSequentialGroup()
                        .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblID1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMovieName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieWriter, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieDate, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieType, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieID, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieIMDB, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addGroup(pnlWatchListLayout.createSequentialGroup()
                        .addComponent(btnAddWatchHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlWatchListLayout.setVerticalGroup(
            pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWatchListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblID1)
                    .addComponent(lblMovieID, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlWatchListLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addGroup(pnlWatchListLayout.createSequentialGroup()
                        .addComponent(lblMovieName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMovieDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWatchListLayout.createSequentialGroup()
                        .addComponent(lblMovieWriter, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlWatchListLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWatchListLayout.createSequentialGroup()
                        .addComponent(lblMovieIMDB, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMovieDate, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlWatchListLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblMovieType, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlWatchListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddWatchHistory)
                    .addComponent(btnDelete))
                .addGap(40, 40, 40))
        );

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

        lblID2.setText("FİLM ID");

        jLabel16.setText("FİLM AD");

        jLabel17.setText("YÖNETMEN");

        jLabel18.setText("YAZAR");

        jLabel19.setText("IMDB PUAN");

        jLabel20.setText("TARİH");

        jLabel21.setText("TÜR");

        btnAddWatchHistory1.setText("İZLEMEDİM");
        btnAddWatchHistory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWatchHistory1ActionPerformed(evt);
            }
        });

        btnDelete1.setText("SİL");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jScrollPane2.setViewportView(txtComment);

        btnSendComment.setText("YORUM YAP");
        btnSendComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendCommentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlWatchHistoryLayout = new javax.swing.GroupLayout(pnlWatchHistory);
        pnlWatchHistory.setLayout(pnlWatchHistoryLayout);
        pnlWatchHistoryLayout.setHorizontalGroup(
            pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                        .addComponent(btnAddWatchHistory1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                        .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblID2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMovieName1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieID1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieDirector1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieWriter1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieType1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMovieIMDB1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(btnSendComment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlWatchHistoryLayout.setVerticalGroup(
            pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                        .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWatchHistoryLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblMovieName1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMovieDirector1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblID2)
                                    .addComponent(lblMovieID1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWatchHistoryLayout.createSequentialGroup()
                                .addComponent(lblMovieWriter1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                                .addComponent(lblMovieIMDB1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMovieDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(lblMovieType1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlWatchHistoryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addGap(18, 18, 18)
                .addGroup(pnlWatchHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddWatchHistory1)
                    .addComponent(btnDelete1)
                    .addComponent(btnSendComment))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlWatchList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlWatchHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchMovies)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchMovies, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addComponent(pnlWatchList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlWatchHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnAddWatchHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWatchHistoryActionPerformed
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        String Sql ="update tblwatch set status="+1+"";
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
                tblMovies();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddWatchHistoryActionPerformed

    private void txtSearchMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMoviesActionPerformed

    }//GEN-LAST:event_txtSearchMoviesActionPerformed

    private void txtSearchMoviesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMoviesKeyReleased
        
        String searchKey = txtSearchMovies.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter =
        new TableRowSorter<DefaultTableModel>(model);
        tblWatchList.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }//GEN-LAST:event_txtSearchMoviesKeyReleased

    private void tblWatchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblWatchListMouseClicked
        int number = tblWatchList.getSelectedRow();
        
        lblMovieID.setText(tblWatchList.getValueAt(number , 0).toString());
        lblMovieName.setText(tblWatchList.getValueAt(number , 1).toString());
        lblMovieDirector.setText(tblWatchList.getValueAt(number , 2).toString());
        lblMovieWriter.setText(tblWatchList.getValueAt(number , 3).toString());
        lblMovieIMDB.setText(tblWatchList.getValueAt(number , 4).toString());
        lblMovieDate.setText(tblWatchList.getValueAt(number , 5).toString());
        lblMovieType.setText(tblWatchList.getValueAt(number , 6).toString());
        lblMovieID1.setText(tblWatchList.getValueAt(number , 0).toString());
        lblMovieName1.setText(tblWatchList.getValueAt(number , 1).toString());
        lblMovieDirector1.setText(tblWatchList.getValueAt(number , 2).toString());
        lblMovieWriter1.setText(tblWatchList.getValueAt(number , 3).toString());
        lblMovieIMDB1.setText(tblWatchList.getValueAt(number , 4).toString());
        lblMovieDate1.setText(tblWatchList.getValueAt(number , 5).toString());
        lblMovieType1.setText(tblWatchList.getValueAt(number , 6).toString());
    }//GEN-LAST:event_tblWatchListMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        clear();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddWatchHistory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWatchHistory1ActionPerformed
    Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        String Sql ="update tblwatch set status="+0+"";
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
                tblMovies();
            } catch (SQLException ex) {
                Logger.getLogger(frmLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAddWatchHistory1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        clear();
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnSendCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendCommentActionPerformed
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement preparedstatement = null;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        
        String Sql ="insert into tblcomments (userID,movieID,commentDate,comment) values((select userID from tblusers where userNickName = '"+user+"'),?,?,?)";
        try{
            connection = dbHelper.getConnection();
            preparedstatement = connection.prepareStatement(Sql);
            preparedstatement.setString(1,lblMovieID1.getText() );
            preparedstatement.setString(2,date.toString());
            preparedstatement.setString(3,txtComment.getText());
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
    }//GEN-LAST:event_btnSendCommentActionPerformed

    
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
            java.util.logging.Logger.getLogger(frmWatchList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmWatchList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmWatchList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmWatchList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmWatchList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddWatchHistory;
    private javax.swing.JButton btnAddWatchHistory1;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnSendComment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblID1;
    private javax.swing.JLabel lblID2;
    private javax.swing.JLabel lblMovieDate;
    private javax.swing.JLabel lblMovieDate1;
    private javax.swing.JLabel lblMovieDirector;
    private javax.swing.JLabel lblMovieDirector1;
    private javax.swing.JLabel lblMovieID;
    private javax.swing.JLabel lblMovieID1;
    private javax.swing.JLabel lblMovieIMDB;
    private javax.swing.JLabel lblMovieIMDB1;
    private javax.swing.JLabel lblMovieName;
    private javax.swing.JLabel lblMovieName1;
    private javax.swing.JLabel lblMovieType;
    private javax.swing.JLabel lblMovieType1;
    private javax.swing.JLabel lblMovieWriter;
    private javax.swing.JLabel lblMovieWriter1;
    private javax.swing.JPanel pnlWatchHistory;
    private javax.swing.JPanel pnlWatchList;
    private javax.swing.JTable tblWatchList;
    private javax.swing.JTextArea txtComment;
    private javax.swing.JTextField txtSearchMovies;
    // End of variables declaration//GEN-END:variables
}
