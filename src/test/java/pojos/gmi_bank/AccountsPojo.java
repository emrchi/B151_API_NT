package pojos.gmi_bank;

import java.io.Serializable;

public class AccountsPojo implements Serializable {
	private int id;
	private String description;
	private int balance;
	private String accountType;
	private String accountStatusType;
	private String createDate;
	private String closedDate;
	private Object employee;
	private Object accountlogs;

	public AccountsPojo() {
	}

	public AccountsPojo(int id, String description, int balance, String accountType, String accountStatusType, String createDate, String closedDate, Object employee, Object accountlogs) {
		this.id = id;
		this.description = description;
		this.balance = balance;
		this.accountType = accountType;
		this.accountStatusType = accountStatusType;
		this.createDate = createDate;
		this.closedDate = closedDate;
		this.employee = employee;
		this.accountlogs = accountlogs;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}

	public int getBalance(){
		return balance;
	}

	public void setAccountType(String accountType){
		this.accountType = accountType;
	}

	public String getAccountType(){
		return accountType;
	}

	public void setAccountStatusType(String accountStatusType){
		this.accountStatusType = accountStatusType;
	}

	public String getAccountStatusType(){
		return accountStatusType;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setClosedDate(String closedDate){
		this.closedDate = closedDate;
	}

	public String getClosedDate(){
		return closedDate;
	}

	public void setEmployee(Object employee){
		this.employee = employee;
	}

	public Object getEmployee(){
		return employee;
	}

	public void setAccountlogs(Object accountlogs){
		this.accountlogs = accountlogs;
	}

	public Object getAccountlogs(){
		return accountlogs;
	}

	@Override
 	public String toString(){
		return 
			"AccountsPojo{" + 
			"id = '" + id + '\'' + 
			",description = '" + description + '\'' + 
			",balance = '" + balance + '\'' + 
			",accountType = '" + accountType + '\'' + 
			",accountStatusType = '" + accountStatusType + '\'' + 
			",createDate = '" + createDate + '\'' + 
			",closedDate = '" + closedDate + '\'' + 
			",employee = '" + employee + '\'' + 
			",accountlogs = '" + accountlogs + '\'' + 
			"}";
		}
}