package company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired private CompanyDAO dao;

	@Override
	public List<String> comp_id() {
		return dao.comp_id();
	}

	@Override
	public List<String> car_name(String comp_id) {
		return dao.car_name(comp_id);
	}

	
}
