package login;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
/**
 *Nama : Edo Kristian Yusak
 *NIM  : E31192060
 */
public class supplier extends JFrame implements ActionListener {

    //mendeklarasikan panel baru di class siswa
    private JPanel jpsupplier = new JPanel();
    private JLabel lblKode = new JLabel("Kode:"), lblNama = new JLabel("Nama:"),
            lblAlamat = new JLabel("Alamat:"), lblTelp = new JLabel("Telp:"),
            lblEmail = new JLabel("Email:"), lblKedatanganBarang = new JLabel("KedatanganBarang:");
    private JTextField txtKode = new JTextField(), txtNama = new JTextField(), txtAlamat
            = new JTextField(), txtTelp = new JTextField(), txtEmail = new JTextField(), txtKedatanganBarang = new JTextField();
    private JButton btnTambah = new JButton("Tambah"), btnUbah = new JButton("Ubah"),
            btnHapus = new JButton("Hapus"), btnBersih = new JButton("Bersih"),
            btnBack = new JButton ("Back");
    
    String[] strJdl = {"Kode", "Nama", "Alamat", "Telp", "Email", "Kedatangan_Barang"};

//Deklarasi untuk Tabel
    JTable tabel = new JTable();
    JScrollPane skrTabel = new JScrollPane();

//contruktor
    supplier() {
        super("Entri Data Supplier");
        setSize(600, 500);
        jpsupplier.setLayout(null);

        //pengaturan Tabel
        skrTabel.getViewport().add(tabel);
        tabel.setEnabled(true);
        skrTabel.setBounds(15, 250, 470, 115);

        //menambahkan tabel pada panel
        jpsupplier.add(skrTabel);

        //menambahkan objek JPanel pada container frame
        getContentPane().add(jpsupplier);
        //menampilkan tabel siswa ke komponen tabel
        setLocationRelativeTo(this);
        TampilTabel();

        //mengatur letak objek pada container
        lblKode.setBounds(15, 20, 100, 25);
        lblNama.setBounds(15, 55, 100, 25);
        lblAlamat.setBounds(15, 90, 100, 25);
        lblTelp.setBounds(15, 125, 100, 25);
        lblEmail.setBounds(15, 160, 100, 25);
        lblKedatanganBarang.setBounds(15, 195, 100, 25);

        txtKode.setBounds(115, 20, 150, 25);
        txtNama.setBounds(115, 55, 150, 25);
        txtAlamat.setBounds(115, 90, 150, 25);
        txtTelp.setBounds(115, 125, 150, 25);
        txtEmail.setBounds(115, 160, 150, 25);
        txtKedatanganBarang.setBounds(115, 195, 50, 25);

        btnTambah.setBounds(340, 20, 85, 25);
        btnUbah.setBounds(340, 55, 85, 25);
        btnHapus.setBounds(340, 90, 85, 25);
        btnBersih.setBounds(340, 125, 85, 25);
        btnBack.setBounds(340, 160 , 85, 25);

        //mengatur/meletakkan objek pada objek panel
        jpsupplier.add(lblKode);
        jpsupplier.add(lblNama);
        jpsupplier.add(lblAlamat);
        jpsupplier.add(lblTelp);
        jpsupplier.add(lblEmail);
        jpsupplier.add(lblKedatanganBarang);
        jpsupplier.add(txtKode);
        jpsupplier.add(txtNama);
        jpsupplier.add(txtAlamat);
        jpsupplier.add(txtTelp);
        jpsupplier.add(txtEmail);
        jpsupplier.add(txtKedatanganBarang);

        jpsupplier.add(btnTambah);
        jpsupplier.add(btnUbah);
        jpsupplier.add(btnHapus);
        jpsupplier.add(btnBersih);
        jpsupplier.add(btnBack);

        //mengatur objek agar dapat berinteraksi dengan user
        btnTambah.addActionListener(this);
        btnUbah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnBersih.addActionListener(this);
        btnBack.addActionListener(this);

        //menambahkan objek JPanel pada container frame
        getContentPane().add(jpsupplier);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //method untuk menambahkan event button di panel yang sudah kita buat tadi
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        if (obj == btnTambah) {
            Tambah();
        }
        if (obj == btnUbah) {
            Ubah();
        }
        if (obj == btnHapus) {
            Hapus();
        }
        if (obj == btnBack) {
            Back () ;
        }
        Bersih();
    }

    //method untuk Button Bersih
    void Bersih() {
        txtKode.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelp.setText("");
        txtEmail.setText("");
        txtKedatanganBarang.setText("");

    }

    //method untuk Button Tambah
    void Tambah() {
        try {
            koneksi3 ObjKoneksi3= new koneksi3();
            Connection con = ObjKoneksi3.getkoneksi();
            Statement st = con.createStatement();
            String sql = "INSERT INTO tabel_supplier"
                    + "(`Kode`, `Nama`, `Alamat`, `Telp`, `Email`, `Kedatangan_Barang`) "
                    + "VALUES ('" + txtKode.getText()
                    + "','" + txtNama.getText()
                    + "','" + txtAlamat.getText() 
                    + "','"+ txtTelp.getText() + "',"
                    + "'" + txtEmail.getText() 
                    + "','"+ txtKedatanganBarang.getText() + "')";
            int row = st.executeUpdate(sql);

            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah ditambahkan ke"
                        + " database", "infomasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditambahkan ke"
                    + " database", "infomasi", JOptionPane.INFORMATION_MESSAGE);
        }
        TampilTabel();
    }

    //method untuk Button Ubah
    void Ubah() {
        try {
            koneksi3 ObjKoneksi3 = new koneksi3();
            Connection con = ObjKoneksi3.getkoneksi();
            Statement st = con.createStatement();

            String sql = "UPDATE tabel_supplier SET Nama ='" + txtNama.getText() + "',"
                    + "Alamat ='" + txtAlamat.getText() 
                    + "',Telp = '"+ txtTelp.getText()
                    + "',email='" + txtEmail.getText() 
                    + "',Kedatangan_Barang = '"+ txtKedatanganBarang.getText()
                    + "' where Kode = '" + txtKode.getText() + "' ";

            int row = st.executeUpdate(sql);
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah di update ",
                         "infomasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak diUbah", "infomasi"
                    , JOptionPane.INFORMATION_MESSAGE);
        }
TampilTabel();
    }

    //method untuk Button Hapus
    void Hapus() {
        try {
            koneksi3 ObjKoneksi3 = new koneksi3();
            Connection con = ObjKoneksi3.getkoneksi();
            Statement st = con.createStatement();
            String sql = "delete from tabel_supplier where Kode = '" + txtKode.getText() + "' ";
            int row = st.executeUpdate(sql);
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah dihapus dari "
                        + " database", "infomasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak dihapus",
                    "infomasi", JOptionPane.INFORMATION_MESSAGE);
        }
TampilTabel();
    }
    void Back () {
        formDashboard start = new formDashboard();
        start.setVisible(true);
        this.dispose();
    }
void TampilTabel(){
        try {
            koneksi3 ObjKoneksi3 = new koneksi3();
            Connection con = ObjKoneksi3.getkoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM tabel_supplier";
            ResultSet set = st.executeQuery(sql);
            //menampilkan data ke Tabel
            ResultSetTableModel model = new ResultSetTableModel(set);
         
            tabel.setModel(model);

            while(set.next()){
                txtKode.setText(Integer.toString(set.getInt("Kode")));
                txtNama.setText(set.getString("Nama"));
                txtAlamat.setText(set.getString("Alamat"));
                txtTelp.setText(set.getString("Telp"));
                txtEmail.setText(set.getString("Email"));
                txtKedatanganBarang.setText(set.getString("Kedatangan_Barang"));
            }
        }
        catch(SQLException e) {
            System.out.println("gagal query");
        }
}
}

