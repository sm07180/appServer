package repair;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl implements RepairService{
	@Autowired private RepairDAO dao;

	@Override
	public String pair_mileage(String user_id) {
		return dao.pair_mileage(user_id);
	}

	@Override
	public List<RepairVO> repair_list() {
		return dao.repair_list();
	}
}
