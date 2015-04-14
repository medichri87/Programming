namespace Textpad
{
    partial class FindForm
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
            this.lbl_find = new System.Windows.Forms.Label();
            this.txt_find = new System.Windows.Forms.TextBox();
            this.btn_find = new System.Windows.Forms.Button();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.btn_reset = new System.Windows.Forms.Button();
            this.btn_findAll = new System.Windows.Forms.Button();
            this.cb_exact = new System.Windows.Forms.CheckBox();
            this.SuspendLayout();
            // 
            // lbl_find
            // 
            this.lbl_find.AutoSize = true;
            this.lbl_find.Location = new System.Drawing.Point(11, 15);
            this.lbl_find.Name = "lbl_find";
            this.lbl_find.Size = new System.Drawing.Size(30, 13);
            this.lbl_find.TabIndex = 0;
            this.lbl_find.Text = "Find:";
            // 
            // txt_find
            // 
            this.txt_find.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txt_find.Location = new System.Drawing.Point(47, 12);
            this.txt_find.Multiline = true;
            this.txt_find.Name = "txt_find";
            this.txt_find.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txt_find.Size = new System.Drawing.Size(318, 69);
            this.txt_find.TabIndex = 1;
            this.txt_find.TextChanged += new System.EventHandler(this.OnTextChanged);
            // 
            // btn_find
            // 
            this.btn_find.Enabled = false;
            this.btn_find.Location = new System.Drawing.Point(209, 88);
            this.btn_find.Name = "btn_find";
            this.btn_find.Size = new System.Drawing.Size(75, 23);
            this.btn_find.TabIndex = 2;
            this.btn_find.Text = "Find Next";
            this.btn_find.UseVisualStyleBackColor = true;
            this.btn_find.Click += new System.EventHandler(this.btn_find_Click);
            // 
            // btn_cancel
            // 
            this.btn_cancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btn_cancel.Location = new System.Drawing.Point(128, 88);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(75, 23);
            this.btn_cancel.TabIndex = 3;
            this.btn_cancel.Text = "Cancel";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // btn_reset
            // 
            this.btn_reset.Enabled = false;
            this.btn_reset.Location = new System.Drawing.Point(47, 88);
            this.btn_reset.Name = "btn_reset";
            this.btn_reset.Size = new System.Drawing.Size(75, 23);
            this.btn_reset.TabIndex = 4;
            this.btn_reset.Text = "Reset";
            this.btn_reset.UseVisualStyleBackColor = true;
            this.btn_reset.Click += new System.EventHandler(this.btn_reset_Click);
            // 
            // btn_findAll
            // 
            this.btn_findAll.Location = new System.Drawing.Point(290, 87);
            this.btn_findAll.Name = "btn_findAll";
            this.btn_findAll.Size = new System.Drawing.Size(75, 23);
            this.btn_findAll.TabIndex = 5;
            this.btn_findAll.Text = "Find All";
            this.btn_findAll.UseVisualStyleBackColor = true;
            this.btn_findAll.Click += new System.EventHandler(this.btn_findAll_Click);
            // 
            // cb_exact
            // 
            this.cb_exact.AutoSize = true;
            this.cb_exact.Checked = true;
            this.cb_exact.CheckState = System.Windows.Forms.CheckState.Checked;
            this.cb_exact.Location = new System.Drawing.Point(279, 121);
            this.cb_exact.Name = "cb_exact";
            this.cb_exact.Size = new System.Drawing.Size(86, 17);
            this.cb_exact.TabIndex = 6;
            this.cb_exact.Text = "Exact Match";
            this.cb_exact.UseVisualStyleBackColor = true;
            // 
            // FindForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.btn_cancel;
            this.ClientSize = new System.Drawing.Size(385, 150);
            this.Controls.Add(this.cb_exact);
            this.Controls.Add(this.btn_findAll);
            this.Controls.Add(this.btn_reset);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.btn_find);
            this.Controls.Add(this.txt_find);
            this.Controls.Add(this.lbl_find);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Name = "FindForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Find";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.OnClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.OnClose);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbl_find;
        private System.Windows.Forms.TextBox txt_find;
        private System.Windows.Forms.Button btn_find;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_reset;
        private System.Windows.Forms.Button btn_findAll;
        private System.Windows.Forms.CheckBox cb_exact;
    }
}