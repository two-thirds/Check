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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        MySqlConnection conn;
        public static string name;
        public static string pwd;
        
        private void button2_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "" & textBox2.Text != "")
            {

                try
                {
                    conn = new MySqlConnection("server=localhost;User Id=root;password=abcd123;Database=sign");
                    conn.Open();
                    if (conn.State == ConnectionState.Open)
                    {
                        MySqlCommand cmd = new MySqlCommand("select Tname from Teacher where Tname = '" + textBox1.Text.Trim() + "' and Password = '" + textBox2.Text.Trim() + "'", conn);
                        MySqlDataReader sdr = cmd.ExecuteReader();
                        sdr.Read();
                        if (sdr.HasRows)
                        {
                            name = textBox1.Text.Trim();
                            pwd = textBox2.Text.Trim();
                            conn.Close();
                            DialogResult cg = MessageBox.Show("登录成功！", "提示", MessageBoxButtons.OK);
                            Form2 frm2 = new Form2();
                            if (cg == DialogResult.OK)
                            {
                                frm2.Show();
                                this.Hide();

                            }
                            else
                                this.Close();
                        }

                    }

                }
                catch
                {
                    MessageBox.Show("用户名或密码错误，请重新输入！");
                    conn.Close();
                    textBox2.Clear();
                }
            }
            else
                MessageBox.Show("请填写完整登录信息！", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
    }
}
