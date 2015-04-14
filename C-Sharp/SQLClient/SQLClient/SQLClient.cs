using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace SQLClient
{
    //Event methods go here
    public partial class SQLClient : Form
    {
        public SQLClient()
        {
            InitializeComponent();
            connected = false;
        }

        private void btn_connect_Click(object sender, EventArgs e)
        {
            server = txt_server.Text;
            database = txt_database.Text;
            uId = txt_uid.Text;
            passWord = txt_password.Text;

            String connectionString = "SERVER=" + server + ";" + "DATABASE=" +
            database + ";" + "UID=" + uId + ";" + "PASSWORD=" + passWord + ";";

            if (connected) return;
            connection = new MySqlConnection(connectionString);
            try
            {
                connection.Open();
                connected = true;
                command = connection.CreateCommand();
                DisplayResult("SELECT * FROM " + database);
                txt_status.Text = "Connected";
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(@"There was an issue connecting. Please try again.", "Error Connecting",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btn_disconnect_Click(object sender, EventArgs e)
        {
            if (!connected) return;
            try
            {
                connection.Close();
                connected = false;
                txt_status.Text = "Not connected";
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(@"There was an issue disconnecting. Please try again.", "Error Disconnecting",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btn_clear_command_Click(object sender, EventArgs e)
        {
            txt_command.Clear();
        }

        private void btn_clear_result_Click(object sender, EventArgs e)
        {
            txt_result.Clear();
        }

        private void btn_exit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void btn_execute_Click(object sender, EventArgs e)
        {
            if (connected)
            {
                command = connection.CreateCommand();
                if (txt_command.Text.StartsWith("SELECT") || txt_command.Text.StartsWith("select"))
                {
                    DisplayResult(txt_command.Text);
                }
                else
                {
                    command = new MySqlCommand(txt_command.Text, connection);
                    command.ExecuteNonQuery();
                    DisplayResult("SELECT * FROM " + database);
                }
            }
            else
            {
                MessageBox.Show("Not currently connected to any database. Please connect to one first.", "Not Connected", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void DisplayResult(String query)
        {
            txt_result.Clear();
            command = new MySqlCommand(query, connection);
            dataReader = command.ExecuteReader();

            int cols = dataReader.FieldCount;

            //TITLES
            for (int i = 0; i < cols; i++)
            {
                txt_result.AppendText(String.Format("{0}\t\t", dataReader.GetName(i)));
            }

            //DASHES
            txt_result.AppendText(Environment.NewLine);
            for (int i = 0; i < cols; i++)
            {
                double factor = dataReader.GetName(i).Length > 2 ? 1.5 : 1;
                double dashCount = dataReader.GetName(i).Length * factor;

                while (dashCount > 0)
                {
                    txt_result.AppendText("-");
                    dashCount--;
                }
                txt_result.AppendText("\t\t");
            }

            //VALUES FOR EACH ROW
            txt_result.AppendText(Environment.NewLine);
            for (; dataReader.Read(); )
            {
                //VALUES FOR EACH COLUMN
                for (int i = 0; i < cols; i++)
                {
                    int s_len = dataReader.GetName(i).Length;
                    txt_result.AppendText(String.Format("{0}\t\t", dataReader.GetString(i)));
                }
                txt_result.AppendText(Environment.NewLine);
            }

            dataReader.Close();
        }

    }
}
