package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	 @JsonProperty("id")
	    int id;
	    
	    @JsonProperty("username")
	    String username;

	    @JsonProperty("firstName")
	    String firstname;

	    @JsonProperty("lastName")
	    String lastname;

	    @JsonProperty("email")
	    String email;

	    @JsonProperty("password")
	    String password;

	    @JsonProperty("phone")
	    String phone;

	    @JsonProperty("userStatus")
	    int Userstatus=0;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public int getUserstatus() {
			return Userstatus;
		}

		public void setUserstatus(int userstatus) {
			Userstatus = userstatus;
		}
	
}
