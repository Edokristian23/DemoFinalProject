package login;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
/**
 *Nama : Edo Kristian Yusak
 *NIM  : E31192060
 */
public class product extends JFrame implements ActionListener {

    //mendeklarasikan panel baru di class siswa
    private JPanel jpproduct = new JPanel();
    private JLabel lblKodeProduct = new JLabel("Kode Product:"), lblNamaProduct = new JLabel("Nama Product:"),
            lblHargaProduct = new JLabel("Harga Product:"), lblTanggalKadaluarsa = new JLabel("Tanggal Kadaluarsa:"),lblStockProduct = new JLabel("Stock Product:");
    private JTextField txtKodeProduct = new JTextField(), txtNamaProduct= new JTextField(), txtHargaProduct
            = new JTextField(), txtTanggalKadaluarsa = new JTextField();
    private JComboBox cboStockProduct = new JComboBox();
    private JButton btnTambah = new JButton("Tambah"), btnUbah = new JButton("Ubah"),
            btnHapus = new JButton("Hapus"), btnBersih = new JButton("Bersih"),
            btnBack = new JButton ("Back");
    
    String[] strJdl = {"kode_product", "nama_product", "harga_product", "Stock Product", "tanggal_kadaluarsa"};

//Deklarasi untuk Tabel
    JTable tabel = new JTable();
    JScrollPane skrTabel = new JScrollPane();

//contruktor
    product() {
        super("Entri Product");
        setSize(600, 500);
        jpproduct.setLayout(null);

        //pengaturan Tabel
        skrTabel.getViewport().add(tabel);
        tabel.setEnabled(true);
        skrTabel.setBounds(15, 250, 470, 115);

        //menambahkan tabel pada panel
        jpproduct.add(skrTabel);

        //menambahkan objek JPanel pada container frame
        getContentPane().add(jpproduct);
        //menampilkan tabel siswa ke komponen tabel
        setLocationRelativeTo(this);
        TampilTabel();

        //mengatur letak objek pada container
        lblKodeProduct.setBounds(15, 20, 100, 25);
        lblNamaProduct.setBounds(15, 55, 100, 25);
        lblHargaProduct.setBounds(15, 90, 100, 25);
        lblTanggalKadaluarsa.setBounds(15, 160, 100, 25);
        lblStockProduct.setBounds(15, 125, 100, 25);
        

        txtKodeProduct.setBounds(115, 20, 150, 25);
        txtNamaProduct.setBounds(115, 55, 150, 25);
        txtHargaProduct.setBounds(115, 90, 150, 25);
        txtTanggalKadaluarsa.setBounds(115, 160, 150, 25);
        cboStockProduct.setBounds(115, 125, 50, 25);

        btnTambah.setBounds(340, 20, 85, 25);
        btnUbah.setBounds(340, 55, 85, 25);
        btnHapus.setBounds(340, 90, 85, 25);
        btnBersih.setBounds(340, 125, 85, 25);
        btnBack.setBounds(340, 160, 85, 25);

        //mengatur/meletakkan objek pada objek panel
        jpproduct.add(lblKodeProduct);
        jpproduct.add(lblNamaProduct);
        jpproduct.add(lblHargaProduct);
        jpproduct.add(lblTanggalKadaluarsa);
        jpproduct.add(lblStockProduct);
        jpproduct.add(txtKodeProduct);
        jpproduct.add(txtNamaProduct);
        jpproduct.add(txtHargaProduct);
        jpproduct.add(txtTanggalKadaluarsa);
        jpproduct.add(cboStockProduct);

        jpproduct.add(btnTambah);
        jpproduct.add(btnUbah);
        jpproduct.add(btnHapus);
        jpproduct.add(btnBersih);
        jpproduct.add(btnBack);
        
        //mengisi combo stock product
        cboStockProduct.addItem("Ada");
        cboStockProduct.addItem("Tidak Ada");

        //mengatur objek agar dapat berinteraksi dengan user
        btnTambah.addActionListener(this);
        btnUbah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnBersih.addActionListener(this);
        btnBack.addActionListener(this);

        //menambahkan objek JPanel pada container frame
        getContentPane().add(jpproduct);

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
        if (obj == btnBack){
            Back ();
        }
        Bersih();
    }

    //method untuk Button Bersih
    void Bersih() {
        txtKodeProduct.setText("");
        txtNamaProduct.setText("");
        txtHargaProduct.setText("");
        txtTanggalKadaluarsa.setText("");
        cboStockProduct.setSelectedIndex(0);
        
    }

    //method untuk Button Tambah
    void Tambah() {
        try {
            koneksi2 ObjKoneksi2= new koneksi2();
            Connection con = ObjKoneksi2.getkoneksi();
            Statement st = con.createStatement();
            String sql = "INSERT INTO tabel_product"
                    + "(`kode_product`, `nama_product`, `harga_product`, `stock`, `tanggal_kadaluarsa`) "
                    + "VALUES ('" + txtKodeProduct.getText()
                    + "','" + txtNamaProduct.getText()
                    + "','" + txtHargaProduct.getText() 
                    + "','" + cboStockProduct.getSelectedItem() 
                    + "','" + txtTanggalKadaluarsa.getText()+ "')";
            int row = st.executeUpdate(sql);

            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah ditambahkan ke"
                        + " database", "infomasi", JOptionPane.INFORMATION_MESSAGE);
               
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditambahkan ke"
                    + " database", "informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        TampilTabel();
    }

    //method untuk Button Ubah
    void Ubah() {
        try {
            koneksi2 ObjKoneksi2 = new koneksi2();
            Connection con = ObjKoneksi2.getkoneksi();
            Statement st = con.createStatement();

            String sql =  "update tabel_product set nama_product ='"+txtNamaProduct.getText()+"'," 
                    +"harga_product ='"+txtHargaProduct.getText()
                    +"',tanggal_kadaluarsa = '"+txtTanggalKadaluarsa.getText()
                    +"',stock='"+cboStockProduct.getSelectedItem()
                    + "' where kode_product = '"+txtKodeProduct.getText()+"' ";

            int row = st.executeUpdate(sql);
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah di update ",
                         "informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan, karena " + e.getMessage());
        }
TampilTabel();
    }

    //method untuk Button Hapus
    void Hapus() {
        try {
            koneksi2 ObjKoneksi2 = new koneksi2();
            Connection con = ObjKoneksi2.getkoneksi();
            Statement st = con.createStatement();
            String sql = "delete from tabel_product where kode_product = '" + txtKodeProduct.getText() + "' ";
            int row = st.executeUpdate(sql);
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah dihapus dari "
                        + " database", "informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan, karena " + e.getMessage());
        }
TampilTabel();
    }
    
    void Back (){
        formDashboard start = new formDashboard();
        start.setVisible(true);
        this.dispose();
    }
void TampilTabel(){
        try {
            koneksi2 ObjKoneksi2 = new koneksi2();
            Connection con = ObjKoneksi2.getkoneksi();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM tabel_product";
            ResultSet set = st.executeQuery(sql);
            //menampilkan data ke Tabel
            ResultSetTableModel model = new ResultSetTableModel(set);
         
            tabel.setModel(model);

            while(set.next()){
                txtKodeProduct.setText(Integer.toString(set.getInt("kode_product")));
                txtNamaProduct.setText(set.getString("nama_product"));
                txtHargaProduct.setText(set.getString("harga_product"));
                txtTanggalKadaluarsa.setText(set.getString("tanggal_kadaluarsa"));
                cboStockProduct.addItem(set.getString("stock"));
            }
        }
        catch(SQLException e) {
             System.out.println("Terjadi kesalahan, karena " + e.getMessage());
             JOptionPane.showMessageDialog(null, "Terjadi kesalahan, karena " + e.getMessage());
        }
}
}
