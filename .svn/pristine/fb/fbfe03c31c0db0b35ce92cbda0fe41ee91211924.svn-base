package notify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService{
	@Autowired private NotifyDAO dao;

	@Override
	public List<NotifyVO> notify_list() {
		return dao.notify_list();
	}
}
