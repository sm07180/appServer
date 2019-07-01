package repair;

import java.util.List;

public interface RepairService {
	String pair_mileage(String user_id);
	List<RepairVO> repair_list();
}
