package shoesTailor;

import java.util.Calendar;

public class MESSAGE_VO {
	private String account;
	private String contents;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate() {
		Calendar c = Calendar.getInstance();
		this.date = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-"
				+ c.get(Calendar.DATE) + " " + c.get(Calendar.HOUR_OF_DAY)
				+ ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
