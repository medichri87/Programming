using System;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace SQLClient
{
    partial class SQLClient
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lbl_server = new System.Windows.Forms.Label();
            this.lbl_database = new System.Windows.Forms.Label();
            this.lbl_password = new System.Windows.Forms.Label();
            this.lbl_uid = new System.Windows.Forms.Label();
            this.lbl_status = new System.Windows.Forms.Label();
            this.txt_server = new System.Windows.Forms.TextBox();
            this.txt_database = new System.Windows.Forms.TextBox();
            this.txt_uid = new System.Windows.Forms.TextBox();
            this.txt_password = new System.Windows.Forms.TextBox();
            this.txt_status = new System.Windows.Forms.TextBox();
            this.grp_title = new System.Windows.Forms.GroupBox();
            this.btn_disconnect = new System.Windows.Forms.Button();
            this.btn_connect = new System.Windows.Forms.Button();
            this.grp_command = new System.Windows.Forms.GroupBox();
            this.btn_clear_command = new System.Windows.Forms.Button();
            this.btn_execute = new System.Windows.Forms.Button();
            this.txt_command = new System.Windows.Forms.TextBox();
            this.grp_result = new System.Windows.Forms.GroupBox();
            this.txt_result = new System.Windows.Forms.TextBox();
            this.btn_clear_result = new System.Windows.Forms.Button();
            this.btn_exit = new System.Windows.Forms.Button();
            this.grp_title.SuspendLayout();
            this.grp_command.SuspendLayout();
            this.grp_result.SuspendLayout();
            this.SuspendLayout();
            // 
            // lbl_server
            // 
            this.lbl_server.AutoSize = true;
            this.lbl_server.Location = new System.Drawing.Point(22, 32);
            this.lbl_server.Name = "lbl_server";
            this.lbl_server.Size = new System.Drawing.Size(51, 16);
            this.lbl_server.TabIndex = 0;
            this.lbl_server.Text = "Server:";
            // 
            // lbl_database
            // 
            this.lbl_database.AutoSize = true;
            this.lbl_database.Location = new System.Drawing.Point(22, 63);
            this.lbl_database.Name = "lbl_database";
            this.lbl_database.Size = new System.Drawing.Size(71, 16);
            this.lbl_database.TabIndex = 1;
            this.lbl_database.Text = "Database:";
            // 
            // lbl_password
            // 
            this.lbl_password.AutoSize = true;
            this.lbl_password.Location = new System.Drawing.Point(22, 127);
            this.lbl_password.Name = "lbl_password";
            this.lbl_password.Size = new System.Drawing.Size(71, 16);
            this.lbl_password.TabIndex = 3;
            this.lbl_password.Text = "Password:";
            // 
            // lbl_uid
            // 
            this.lbl_uid.AutoSize = true;
            this.lbl_uid.Location = new System.Drawing.Point(22, 96);
            this.lbl_uid.Name = "lbl_uid";
            this.lbl_uid.Size = new System.Drawing.Size(80, 16);
            this.lbl_uid.TabIndex = 2;
            this.lbl_uid.Text = "User Name:";
            // 
            // lbl_status
            // 
            this.lbl_status.AutoSize = true;
            this.lbl_status.Location = new System.Drawing.Point(22, 158);
            this.lbl_status.Name = "lbl_status";
            this.lbl_status.Size = new System.Drawing.Size(48, 16);
            this.lbl_status.TabIndex = 4;
            this.lbl_status.Text = "Status:";
            // 
            // txt_server
            // 
            this.txt_server.Location = new System.Drawing.Point(102, 29);
            this.txt_server.Name = "txt_server";
            this.txt_server.Size = new System.Drawing.Size(129, 21);
            this.txt_server.TabIndex = 5;
            // 
            // txt_database
            // 
            this.txt_database.Location = new System.Drawing.Point(102, 60);
            this.txt_database.Name = "txt_database";
            this.txt_database.Size = new System.Drawing.Size(129, 21);
            this.txt_database.TabIndex = 6;
            // 
            // txt_uid
            // 
            this.txt_uid.Location = new System.Drawing.Point(102, 93);
            this.txt_uid.Name = "txt_uid";
            this.txt_uid.Size = new System.Drawing.Size(129, 21);
            this.txt_uid.TabIndex = 7;
            // 
            // txt_password
            // 
            this.txt_password.Location = new System.Drawing.Point(102, 124);
            this.txt_password.Name = "txt_password";
            this.txt_password.PasswordChar = '*';
            this.txt_password.Size = new System.Drawing.Size(129, 21);
            this.txt_password.TabIndex = 8;
            // 
            // txt_status
            // 
            this.txt_status.Location = new System.Drawing.Point(102, 155);
            this.txt_status.Name = "txt_status";
            this.txt_status.ReadOnly = true;
            this.txt_status.Size = new System.Drawing.Size(129, 21);
            this.txt_status.TabIndex = 9;
            this.txt_status.Text = "Not Connected";
            // 
            // grp_title
            // 
            this.grp_title.Controls.Add(this.btn_disconnect);
            this.grp_title.Controls.Add(this.btn_connect);
            this.grp_title.Controls.Add(this.lbl_server);
            this.grp_title.Controls.Add(this.txt_status);
            this.grp_title.Controls.Add(this.lbl_database);
            this.grp_title.Controls.Add(this.txt_password);
            this.grp_title.Controls.Add(this.lbl_uid);
            this.grp_title.Controls.Add(this.txt_uid);
            this.grp_title.Controls.Add(this.lbl_password);
            this.grp_title.Controls.Add(this.txt_database);
            this.grp_title.Controls.Add(this.lbl_status);
            this.grp_title.Controls.Add(this.txt_server);
            this.grp_title.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.grp_title.Location = new System.Drawing.Point(11, 11);
            this.grp_title.Name = "grp_title";
            this.grp_title.Size = new System.Drawing.Size(261, 219);
            this.grp_title.TabIndex = 10;
            this.grp_title.TabStop = false;
            this.grp_title.Text = "SQL Connection";
            // 
            // btn_disconnect
            // 
            this.btn_disconnect.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_disconnect.Location = new System.Drawing.Point(25, 188);
            this.btn_disconnect.Name = "btn_disconnect";
            this.btn_disconnect.Size = new System.Drawing.Size(99, 23);
            this.btn_disconnect.TabIndex = 11;
            this.btn_disconnect.Text = "Disconnect";
            this.btn_disconnect.UseVisualStyleBackColor = true;
            this.btn_disconnect.Click += new System.EventHandler(this.btn_disconnect_Click);
            // 
            // btn_connect
            // 
            this.btn_connect.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_connect.Location = new System.Drawing.Point(132, 188);
            this.btn_connect.Name = "btn_connect";
            this.btn_connect.Size = new System.Drawing.Size(99, 23);
            this.btn_connect.TabIndex = 10;
            this.btn_connect.Text = "Connect";
            this.btn_connect.UseVisualStyleBackColor = true;
            this.btn_connect.Click += new System.EventHandler(this.btn_connect_Click);
            // 
            // grp_command
            // 
            this.grp_command.Controls.Add(this.btn_clear_command);
            this.grp_command.Controls.Add(this.btn_execute);
            this.grp_command.Controls.Add(this.txt_command);
            this.grp_command.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.grp_command.Location = new System.Drawing.Point(279, 12);
            this.grp_command.Name = "grp_command";
            this.grp_command.Size = new System.Drawing.Size(395, 218);
            this.grp_command.TabIndex = 11;
            this.grp_command.TabStop = false;
            this.grp_command.Text = "SQL Command";
            // 
            // btn_clear_command
            // 
            this.btn_clear_command.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_clear_command.Location = new System.Drawing.Point(178, 187);
            this.btn_clear_command.Name = "btn_clear_command";
            this.btn_clear_command.Size = new System.Drawing.Size(103, 23);
            this.btn_clear_command.TabIndex = 2;
            this.btn_clear_command.Text = "Clear";
            this.btn_clear_command.UseVisualStyleBackColor = true;
            this.btn_clear_command.Click += new System.EventHandler(this.btn_clear_command_Click);
            // 
            // btn_execute
            // 
            this.btn_execute.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_execute.Location = new System.Drawing.Point(286, 187);
            this.btn_execute.Name = "btn_execute";
            this.btn_execute.Size = new System.Drawing.Size(103, 23);
            this.btn_execute.TabIndex = 1;
            this.btn_execute.Text = "Execute";
            this.btn_execute.UseVisualStyleBackColor = true;
            this.btn_execute.Click += new System.EventHandler(this.btn_execute_Click);
            // 
            // txt_command
            // 
            this.txt_command.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txt_command.Location = new System.Drawing.Point(6, 28);
            this.txt_command.Multiline = true;
            this.txt_command.Name = "txt_command";
            this.txt_command.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txt_command.Size = new System.Drawing.Size(383, 146);
            this.txt_command.TabIndex = 0;
            // 
            // grp_result
            // 
            this.grp_result.Controls.Add(this.txt_result);
            this.grp_result.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.grp_result.Location = new System.Drawing.Point(11, 236);
            this.grp_result.Name = "grp_result";
            this.grp_result.Size = new System.Drawing.Size(663, 246);
            this.grp_result.TabIndex = 12;
            this.grp_result.TabStop = false;
            this.grp_result.Text = "SQL Result";
            // 
            // txt_result
            // 
            this.txt_result.BackColor = System.Drawing.SystemColors.ButtonHighlight;
            this.txt_result.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txt_result.Location = new System.Drawing.Point(7, 22);
            this.txt_result.Multiline = true;
            this.txt_result.Name = "txt_result";
            this.txt_result.ReadOnly = true;
            this.txt_result.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.txt_result.Size = new System.Drawing.Size(650, 218);
            this.txt_result.TabIndex = 0;
            // 
            // btn_clear_result
            // 
            this.btn_clear_result.Location = new System.Drawing.Point(457, 488);
            this.btn_clear_result.Name = "btn_clear_result";
            this.btn_clear_result.Size = new System.Drawing.Size(103, 23);
            this.btn_clear_result.TabIndex = 13;
            this.btn_clear_result.Text = "Clear";
            this.btn_clear_result.UseVisualStyleBackColor = true;
            this.btn_clear_result.Click += new System.EventHandler(this.btn_clear_result_Click);
            // 
            // btn_exit
            // 
            this.btn_exit.Location = new System.Drawing.Point(565, 488);
            this.btn_exit.Name = "btn_exit";
            this.btn_exit.Size = new System.Drawing.Size(103, 23);
            this.btn_exit.TabIndex = 14;
            this.btn_exit.Text = "Exit";
            this.btn_exit.UseVisualStyleBackColor = true;
            this.btn_exit.Click += new System.EventHandler(this.btn_exit_Click);
            // 
            // SQLClient
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Control;
            this.ClientSize = new System.Drawing.Size(686, 516);
            this.Controls.Add(this.btn_exit);
            this.Controls.Add(this.btn_clear_result);
            this.Controls.Add(this.grp_result);
            this.Controls.Add(this.grp_command);
            this.Controls.Add(this.grp_title);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "SQLClient";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "SQLClient";
            this.grp_title.ResumeLayout(false);
            this.grp_title.PerformLayout();
            this.grp_command.ResumeLayout(false);
            this.grp_command.PerformLayout();
            this.grp_result.ResumeLayout(false);
            this.grp_result.PerformLayout();
            this.ResumeLayout(false);

        }

        public static void Main(string[] args)
        {
            Application.Run(new SQLClient());
        }

        #endregion

        private Label lbl_server;
        private Label lbl_database;
        private Label lbl_password;
        private Label lbl_uid;
        private Label lbl_status;
        private TextBox txt_server;
        private TextBox txt_database;
        private TextBox txt_uid;
        private TextBox txt_password;
        private TextBox txt_status;
        private GroupBox grp_title;
        private GroupBox grp_command;
        private TextBox txt_command;
        private GroupBox grp_result;
        private TextBox txt_result;
        private Button btn_disconnect;
        private Button btn_connect;
        private Button btn_clear_command;
        private Button btn_execute;
        private Button btn_clear_result;
        private Button btn_exit;

        private String server;
        private String database;
        private String uId;
        private String passWord;
        private MySqlConnection connection;
        private MySqlCommand command;
        private MySqlDataReader dataReader;
        private bool connected;
    }
}