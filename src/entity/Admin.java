package entity;

public class Admin {
		private int adminId;
		private String password;
		private String fname;
		private String lname;
		
		public Admin() {
			  this.adminId=0;
			  this.password="";
			  this.fname="";
			  this.lname="";
		}
		
		public Admin(int adminId, String password, String fname, String lname) {
			super();
			this.adminId = adminId;
			this.password = password;
			this.fname = fname;
			this.lname = lname;
		}

		public int getAdminId() {
			return adminId;
		}

		public void setAdminId(int adminId) {
			this.adminId = adminId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		
		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		@Override
		public String toString() {
			return "Admin [adminId=" + adminId + ", password=" + password + ", fname=" + fname + ", lname=" + lname
					+ "]";
		}

		
		
}
