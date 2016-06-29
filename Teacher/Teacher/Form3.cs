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

namespace Teacher
{
    public partial class Form3 : Form
    {
        public Form3()
        {
            InitializeComponent();
        }

        MySqlConnection conn;
        public static string ti;
        
        private void Form3_Load(object sender, EventArgs e)
        {
            dateTimePicker1.Format = DateTimePickerFormat.Time;
            
            conn = new MySqlConnection("server=localhost;User Id=root;password=abcd123;Database=sign");
            conn.Open();
            MySqlCommand cmd = new MySqlCommand();
            cmd.Connection = conn;
            cmd.CommandText = "select signtime from school";
            cmd.CommandType = CommandType.Text;
            MySqlDataReader sdr = cmd.ExecuteReader();
            if (sdr.Read())
            {
                ti = sdr[0].ToString();
            }
            
            
        }
        private void button1_Click(object sender, EventArgs e)
        {
            if (ti != dateTimePicker1.Text)
            {
                conn = new MySqlConnection("server=localhost;User Id=root;password=abcd123;Database=sign");
                conn.Open();
                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                
                cmd.CommandText = "update school set signtime = '" + dateTimePicker1.Text + "' where schno = '" + Form2.no + "'";
                
                cmd.CommandType = CommandType.Text;
                cmd.ExecuteNonQuery();
                DialogResult dr = MessageBox.Show("修改时间成功！", "提示", MessageBoxButtons.OK);
                this.Close();
            }
            label2.Text = "您要修改的时间与现在的时间一样，无需修改！";
           
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

       
    }
}
