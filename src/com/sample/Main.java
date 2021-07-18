package com.sample;

import com.dbUtil.dbConn;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;

/**
 * @author Ananda Rizaldy Aristyo
 * @author Muhammad Lutfi Syukran
 * @author Prasomya Mahardhika
 * @author Virgiawan Sagarmata Patabuga
 * @since 1.0 in 2021
 */
public class Main extends Application {

    /**
     * Declare Table Field
     */
    TableView<TabelBuku> tabelNya;

    /**
     * Fetch columns from an sqlite database
     *
     * @return Observable Array List Field
     */
    public ObservableList<TabelBuku> getItemNya() {
        String sql = "SELECT id, judul, penulis, penerbit, jumlahEksemplar, edisi, kategori, bentuk FROM tabelBuku";
        ObservableList<TabelBuku> tabelBukus = FXCollections.observableArrayList();
        try (Connection conn = dbConn.connectSQL(); Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                tabelBukus.add(new TabelBuku(resultSet.getInt("id"), resultSet.getString("judul"), resultSet.getString("penulis"), resultSet.getString("penerbit"), resultSet.getString("jumlahEksemplar"), resultSet.getString("edisi"), resultSet.getString("kategori"), resultSet.getString("bentuk")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return tabelBukus;
    }

    public TableView<TabelBuku> getTabelNya() {
        TableColumn<TabelBuku, Integer> idCol = new TableColumn<>("ID Entry");
        TableColumn<TabelBuku, Integer> judulCol = new TableColumn<>("Judul");
        TableColumn<TabelBuku, Integer> penulisCol = new TableColumn<>("Penulis");
        TableColumn<TabelBuku, Integer> penerbitCol = new TableColumn<>("Penerbit");
        TableColumn<TabelBuku, Integer> jumlahEksemplarCol = new TableColumn<>("Jml. Eks.");
        TableColumn<TabelBuku, Integer> edisiCol = new TableColumn<>("Edisi");
        TableColumn<TabelBuku, Integer> kategoriCol = new TableColumn<>("Kategori");
        TableColumn<TabelBuku, Integer> bentukCol = new TableColumn<>("Bentuk");
        idCol.setMinWidth(20);
        judulCol.setMinWidth(20);
        penulisCol.setMinWidth(20);
        penerbitCol.setMinWidth(20);
        edisiCol.setMinWidth(20);
        jumlahEksemplarCol.setMinWidth(20);
        kategoriCol.setMinWidth(20);
        bentukCol.setMinWidth(20);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        judulCol.setCellValueFactory(new PropertyValueFactory<>("judul"));
        penulisCol.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        penerbitCol.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        jumlahEksemplarCol.setCellValueFactory(new PropertyValueFactory<>("jumlahEksemplar"));
        edisiCol.setCellValueFactory(new PropertyValueFactory<>("edisi"));
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        bentukCol.setCellValueFactory(new PropertyValueFactory<>("bentuk"));
        tabelNya = new TableView<>();
        tabelNya.setItems(getItemNya());
        tabelNya.getColumns().add(idCol);
        tabelNya.getColumns().add(judulCol);
        tabelNya.getColumns().add(penulisCol);
        tabelNya.getColumns().add(penerbitCol);
        tabelNya.getColumns().add(jumlahEksemplarCol);
        tabelNya.getColumns().add(edisiCol);
        tabelNya.getColumns().add(kategoriCol);
        tabelNya.getColumns().add(bentukCol);
        return tabelNya;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dbConn.connectSQL();
        //grid & scene awal
        primaryStage.setTitle("Perpus-Perpus-an");
        GridPane gridMenu = new GridPane();
        gridMenu.setAlignment(Pos.CENTER);
        gridMenu.setHgap(10);
        gridMenu.setVgap(10);
        gridMenu.setPadding(new Insets(25, 25, 25, 25));
        Scene sceneMenu = new Scene(gridMenu, 500, 400);
        primaryStage.setScene(sceneMenu);

        //Title
        Text titlePerpus = new Text("Librarian Book Data Manager");
        titlePerpus.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        gridMenu.add(titlePerpus, 0, 0, 1, 1);

        //id, judul, penulis, penerbit, jumlahEksemplar, edisi, kategori, bentuk
        Label labelJudul = new Label("Judul");
        Label labelPenulis = new Label("Penulis");
        Label labelPenerbit = new Label("Penerbit");
        Label labelJumlahEksemplar = new Label("Jumlah Eksemplar");
        Label labelEdisi = new Label("Edisi");
        Label labelKategori = new Label("Kategori");
        Label labelBentuk = new Label("Bentuk");
        TextField judulTF = new TextField();
        TextField penulisTF = new TextField();
        TextField penerbitTF = new TextField();
        TextField jumlahEksemplarTF = new TextField();
        TextField edisiTF = new TextField();
        ComboBox<String> kategoriCB = new ComboBox<>();
        kategoriCB.getItems().addAll("Sains", "Novel", "Majalah", "Koran", "Tabloid", "Komik", "Lain-Lain");
        ComboBox<String> bentukCB = new ComboBox<>();
        bentukCB.getItems().addAll("Fisik", "Maya", "Fisik dan Maya", "Lain-Lain");
        //Main Buttons
        Button tambahBuku = new Button("Tambah Buku");
        Button listBuku = new Button("List Buku");
        gridMenu.add(tambahBuku, 0, 8);
        gridMenu.add(listBuku, 0, 9);

        //Text Instance to be used in next setOnActions
        Text saveStatus = new Text();

        tambahBuku.setOnAction(a -> {
            GridPane gridTambah = new GridPane();
            gridTambah.setAlignment(Pos.CENTER);
            gridTambah.setHgap(10);
            gridTambah.setVgap(10);
            gridTambah.setPadding(new Insets(25, 25, 25, 25));
            Scene sceneTambah = new Scene(gridTambah, 500, 400);
            primaryStage.setScene(sceneTambah);

            gridTambah.add(bentukCB, 1, 7);
            gridTambah.add(kategoriCB, 1, 6);
            gridTambah.add(edisiTF, 1, 5);
            gridTambah.add(jumlahEksemplarTF, 1, 4);
            gridTambah.add(penerbitTF, 1, 3);
            gridTambah.add(penulisTF, 1, 2);
            gridTambah.add(judulTF, 1, 1);
            gridTambah.add(labelJudul, 0, 1);
            gridTambah.add(labelPenulis, 0, 2);
            gridTambah.add(labelPenerbit, 0, 3);
            gridTambah.add(labelJumlahEksemplar, 0, 4);
            gridTambah.add(labelEdisi, 0, 5);
            gridTambah.add(labelKategori, 0, 6);
            gridTambah.add(labelBentuk, 0, 7);

            Button addBook = new Button("Add Book");
            gridTambah.add(addBook, 0, 8);
            addBook.setOnAction(add -> {
                String sql = "INSERT INTO tabelBuku(judul,penulis,penerbit,jumlahEksemplar,edisi,kategori,bentuk) VALUES(?,?,?,?,?,?,?)";
                try (Connection conn = dbConn.connectSQL(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, (judulTF.getText()));
                    preparedStatement.setString(2, (penulisTF.getText()));
                    preparedStatement.setString(3, (penerbitTF.getText()));
                    preparedStatement.setString(4, (jumlahEksemplarTF.getText()));
                    preparedStatement.setString(5, (edisiTF.getText()));
                    preparedStatement.setString(6, (kategoriCB.getValue()));
                    preparedStatement.setString(7, (bentukCB.getValue()));
                    preparedStatement.executeUpdate();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
                primaryStage.setScene(sceneMenu);
                judulTF.clear();
                penulisTF.clear();
                penerbitTF.clear();
                jumlahEksemplarTF.clear();
                edisiTF.clear();
                gridMenu.add(saveStatus, 0, 10);
                saveStatus.setText("Successfully Added New Book!");
            });
        });
        listBuku.setOnAction(b -> {
            //grid & scene list
            GridPane gridLihat = new GridPane();
            gridLihat.setAlignment(Pos.CENTER);
            gridLihat.setVgap(10);
            gridLihat.setHgap(10);
            gridLihat.setPadding(new Insets(25, 25, 25, 25));
            Scene sceneLihat = new Scene(gridLihat, 500, 720);
            primaryStage.setScene(sceneLihat);

            TableView<TabelBuku> tabelBerisi = getTabelNya();
            VBox vBox = new VBox(0);
            vBox.getChildren().add(tabelBerisi);
            gridLihat.add(vBox, 0, 0, 4, 1);

            Label idEntry = new Label("ID Entry:");
            TextField idTF = new TextField();
            gridLihat.add(idEntry, 1, 1);
            gridLihat.add(idTF, 2, 1);

            Button editButton = new Button("Edit");
            Button delButton = new Button("Delete");
            gridLihat.add(editButton, 1, 2);
            gridLihat.add(delButton, 2, 2);

            //save for next acts to be easily accessed
            Button goBack = new Button("Go Back");
            gridLihat.add(goBack, 3, 2);
            goBack.setOnAction(gb -> primaryStage.setScene(sceneMenu));

            delButton.setOnAction(d -> {
                if ((idTF.getText()).equals("")) {
                    primaryStage.setScene(sceneLihat);
                }
                String sql = "DELETE FROM tabelBuku WHERE id = ?";
                try (Connection conn = dbConn.connectSQL(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, Integer.parseInt(idTF.getText()));
                    preparedStatement.executeUpdate();
                    saveStatus.setText("DataBase Updated!");
                    primaryStage.setScene(sceneMenu);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            });

            editButton.setOnAction(c -> {
                if ((idTF.getText()).equals("")) {
                    primaryStage.setScene(sceneLihat);
                } else {
                    //grid & scene edit
                    GridPane gridEdit = new GridPane();
                    gridEdit.setAlignment(Pos.CENTER);
                    gridEdit.setVgap(10);
                    gridEdit.setHgap(10);
                    Scene sceneEdit = new Scene(gridEdit, 500, 720);
                    gridEdit.setPadding(new Insets(25, 25, 25, 25));
                    primaryStage.setScene(sceneEdit);

                    TextField idTF2 = new TextField(idTF.getText());
                    idTF2.setEditable(false);
                    Button saveButton = new Button("Update");
                    gridEdit.add(labelJudul, 1, 2);
                    gridEdit.add(labelPenulis, 1, 3);
                    gridEdit.add(labelPenerbit, 1, 4);
                    gridEdit.add(labelJumlahEksemplar, 1, 5);
                    gridEdit.add(labelEdisi, 1, 6);
                    gridEdit.add(labelKategori, 1, 7);
                    gridEdit.add(labelBentuk, 1, 8);
                    gridEdit.add(judulTF, 2, 2);
                    gridEdit.add(penulisTF, 2, 3);
                    gridEdit.add(penerbitTF, 2, 4);
                    gridEdit.add(jumlahEksemplarTF, 2, 5);
                    gridEdit.add(edisiTF, 2, 6);
                    gridEdit.add(kategoriCB, 2, 7);
                    gridEdit.add(bentukCB, 2, 8);
                    gridEdit.add(vBox, 0, 0);
                    gridEdit.add(idEntry, 1, 1);
                    gridEdit.add(idTF2, 2, 1);
                    gridEdit.add(saveButton, 1, 9);
                    gridEdit.add(goBack, 2, 9);

                    saveButton.setOnAction(e -> {
                        String sql = "UPDATE tabelBuku SET judul = ?, penulis = ?, penerbit = ?, jumlahEksemplar = ?, edisi = ?, kategori = ?, bentuk = ? WHERE id = ?";
                        try (Connection conn = dbConn.connectSQL(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                            preparedStatement.setString(1, (judulTF.getText()));
                            preparedStatement.setString(2, (penulisTF.getText()));
                            preparedStatement.setString(3, (penerbitTF.getText()));
                            preparedStatement.setString(4, (jumlahEksemplarTF.getText()));
                            preparedStatement.setString(5, (edisiTF.getText()));
                            preparedStatement.setString(6, (kategoriCB.getValue()));
                            preparedStatement.setString(7, (bentukCB.getValue()));
                            preparedStatement.setInt(8, Integer.parseInt((idTF2.getText())));
                            preparedStatement.executeUpdate();
                            saveStatus.setText("DataBase Updated!");
                            primaryStage.setScene(sceneMenu);
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }
                    });
                }
            });
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
