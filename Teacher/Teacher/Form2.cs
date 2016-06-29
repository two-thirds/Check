using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data;


namespace Teacher
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        MySqlConnection conn;
        private void button1_Click(object sender, EventArgs e)
        {
            Form3 frm3 = new Form3();
            frm3.Show();
        }


        private void Form2_Load(object sender, EventArgs e)
        {
            dateTimePicker1.Format = DateTimePickerFormat.Custom;
            dateTimePicker1.CustomFormat = "yyyy-MM-dd";

            conn = new MySqlConnection("server=localhost;User Id=root;password=abcd123;Database=sign");
            conn.Open();
            MySqlDataAdapter sda = new MySqlDataAdapter("select * from nosignin",conn);
            DataSet ds = new DataSet();
            sda.Fill(ds, "nosignin");
            dataGridView1.DataSource = ds.Tables[0];


        }

        public static string no;
   
        private void label2_Click(object sender, EventArgs e)
        {
            conn = new MySqlConnection("server=localhost;User Id=root;password=abcd123;Database=sign");
            conn.Open();
            MySqlCommand cmd = new MySqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = "select schno from teacher where Tname = '" + Form1.name + "'";
            cmd.CommandType = CommandType.Text;
            MySqlDataReader sdr = cmd.ExecuteReader();
            while(sdr.Read())
            {
                no = sdr[0].ToString();
            }
            sdr.Close();
            MySqlDataAdapter sda = new MySqlDataAdapter("select * from student where schno = '" + no + "'",conn);
            DataSet ds = new DataSet();
            sda.Fill(ds, "student");
            dataGridView1.DataSource = ds.Tables[0];
        }

        private void label3_Click(object sender, EventArgs e)
        {
            conn = new MySqlConnection("server=localhost;User Id=root;password=abcd123;Database=sign");
            conn.Open();
           
            MySqlDataAdapter sda = new MySqlDataAdapter("select * from askforleave", conn);
            DataSet ds = new DataSet();
            sda.Fill(ds, "askforleave");
            dataGridView1.DataSource = ds.Tables[0];
        }

        public static string da;
        private void label1_Click(object sender, EventArgs e)
        {
            conn = new MySqlConnection("server=localhost;User Id=root;password=abcd123;Database=sign");
            conn.Open();

            da = dateTimePicker1.Text;
            MySqlDataAdapter sda = new MySqlDataAdapter("select * from nosignin where date = '"+ da +"'", conn);
            DataSet ds = new DataSet();
            sda.Fill(ds, "nosignin");
            dataGridView1.DataSource = ds.Tables[0];
        }
    }
}
