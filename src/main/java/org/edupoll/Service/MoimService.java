package org.edupoll.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.edupoll.model.dto.response.PageItem;
import org.edupoll.model.dto.response.Pagination;
import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.edupoll.model.entity.User;
import org.edupoll.repository.AttendanceRepository;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.ReplyRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class MoimService {

	@Autowired
	MoimRepository moimRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Value("${config.moim.pageSize}")
	int pageSize;
	
	/**모임등록하고자 할때 사용하는 메서드 */
	public String createMoim(Moim moim, String logonId) {
		User found= userRepository.findById(logonId).get();// 로그온 상태라면 무조건 있는 데이터
			/*
			 * User user = new User();
			 * user.setId(logonId);
			 * user.setManager(user);
			 * */
			moim.setCurrentPerson(1);
			moim.setManager(found);
			Moim saved = moimRepository.save(moim);
			
			return saved.getId();
	}
	
	/**특정 Id의 모임정보 불러오기용 서비스 메서드*/
	public Moim findMoim(String logonId){

		return  moimRepository.findById(logonId).orElse(null);
	}
	
	//모임 전체글 페이징처리
	
	public List<Moim> findAllMoim(int page){
		PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by(Direction.ASC, "targetDate"));
		
		return  moimRepository.findAll(pageRequest).toList();
	}
	
	
	public Pagination MoimPaggingCount (int currentPage){
		//페이지 분량 설정하기 
		long totalData = moimRepository.count();
		long lastPage = totalData / pageSize + (totalData % pageSize > 0 ? 1: 0);
		
		List<PageItem> pages = new ArrayList<>();
		
		long endValue = (long)(Math.ceil(currentPage/5.0)*5);
		long startValue = endValue - 4;
		
		if(endValue > lastPage) {
			endValue = lastPage;
		}
		
		
		for(long i = startValue; i <=endValue ; i++) {
			pages.add(new PageItem(i, i == currentPage));
			
		}

		PageItem prev = (startValue != 1) ? new PageItem(startValue - 1, true) : new PageItem(startValue, false);
		PageItem next = (endValue < lastPage) ? new PageItem(endValue + 1, true) : new PageItem(endValue, false);
	
		Pagination pagenation = new Pagination(prev, next, pages);
		
		return pagenation;
	}
	
	/**본인이 쓴 모임을 삭제하고 할 때 사용하는 메서드*/
	public boolean deleteMoim(String moimId) {
		
		Optional<Moim> optionMoim = moimRepository.findById(moimId);
		
		if(optionMoim.isEmpty()) {
			
			return false;
		}
		
		Moim moim = optionMoim.get();
		
		List<Reply> replys = moim.getReplys();
		List<Attendance> attend = moim.getAttendance();
		
		for(Reply r : replys) {
			
			replyRepository.deleteById(r.getId());
		}
		
		for(Attendance a : attend) {
			
			attendanceRepository.deleteById(a.getId());
		}
	
		moimRepository.delete(moim);
		
		return true;
		
	}


}
