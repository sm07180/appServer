package car_record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Car_recordServiceImpl implements Car_recordService{
	@Autowired private Car_recordDAO dao;

	@Override
	public List<Car_recordVO> chartData(String user_id) {
		return dao.chartData(user_id);
	}

	@Override
	public String inputInsert(Car_recordVO vo) {
		return dao.inputInsert(vo);
	}

	@Override
	public String oil_insert(Car_recordVO vo) {
		return dao.oil_insert(vo);
	}

	@Override
	public List<Car_recordVO> myRepairList(Car_recordVO vo) {
		return dao.myRepairList(vo);
	}

	@Override
	public List<Car_recordVO> myOilList(Car_recordVO vo) {
		return dao.myOilList(vo);
	}

	@Override
	public void record_delete(Car_recordVO vo) {
		dao.record_delete(vo);
	}
}
