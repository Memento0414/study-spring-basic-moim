package org.edupoll.controller.API;

import org.edupoll.model.dto.response.TestResponseData;
import org.edupoll.model.entity.User;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api")
public class TestController {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/test-a")
	@ResponseBody
	public String testAHandle() throws JsonProcessingException {

		String[] ar = new String[] { "saito", "sadako", "goku" };
		String jsonStr = objectMapper.writeValueAsString(ar);
						// Json의 toJson()과 같은 것이다.
		System.out.println(jsonStr);
		return jsonStr;
	}

	
	@GetMapping("/test-b")
	@ResponseBody
	public String[] testBHandle() {

		String[] ar = new String[] { "saito", "sadako", "goku", "black sheep wall", "show me the money" };
	
		return ar;
		//객체를 리턴시키면 알아서 json라이브러리가 작동하면서 gson으로 전송된다.
		//물론 @ResponseBoby 어노테이션을 붙여야 한다.
	}
	
	@GetMapping("/test-c")
	@ResponseBody
	public TestResponseData testCHandle( ) {
		TestResponseData trd = 
				new TestResponseData(2, "정상처리되었습니다.", new String[] {"손오공", "손오반", "손오천"});
		//수동으로 데이터를 세팅하지 않고 객체를 리턴 시켜도 된다.
		//응답전용 dto를 만들어두는 것을 고려하고, setter,getter를 만들어야 가능하다.
		return trd;
	}
	
	
	@GetMapping("/test-d")
	@ResponseBody
	public User testDHandle() {
		//Entity 객체를 전송하게 되면 방대한 정보를 보내기 때문에 보내고자 하는 정보만 보내야 한다.
		//번거롭지만 응답전용 DTO를 만들어서 사용하는 것이 좋다.
		User found=userRepository.findById("sadako").get();
		
		return found;
	}
}
