package repair;

public class RepairVO {
	private String repair_name;
	private int repair_id, repair_term, repair_mile;
	
	public String getRepair_name() {
		return repair_name;
	}
	public void setRepair_name(String repair_name) {
		this.repair_name = repair_name;
	}
	public int getRepair_id() {
		return repair_id;
	}
	public void setRepair_id(int repair_id) {
		this.repair_id = repair_id;
	}
	public int getRepair_term() {
		return repair_term;
	}
	public void setRepair_term(int repair_term) {
		this.repair_term = repair_term;
	}
	public int getRepair_mile() {
		return repair_mile;
	}
	public void setRepair_mile(int repair_mile) {
		this.repair_mile = repair_mile;
	}
}
