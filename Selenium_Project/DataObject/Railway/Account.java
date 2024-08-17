package Railway;

public class Account {
	private String email;
	private String password;
	private String pID;
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public Account(String email, String password, String pID) {
		this.email = email;
		this.password = password;
		this.pID = pID;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPID(String pID) {
		this.pID = pID;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getPID() {
		return this.pID;
	}
}
