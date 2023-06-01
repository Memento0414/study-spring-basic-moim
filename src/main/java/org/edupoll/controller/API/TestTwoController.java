package org.edupoll.controller.API;

import org.edupoll.model.dto.response.TestResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @controller와 @responseBody 결합되어 있는데 어노테이션
@RequestMapping("/api")
public class TestTwoController {

	@GetMapping("/test-1")
	public TestResponseData test1Handle() {
		
		TestResponseData trd = new TestResponseData(3, "소노 마마 시네", new String [] {"이오리", "쿠사나기 쿄"});
		
		return trd;
	}
	
	@GetMapping("/test-2")
	public String test2Handle() {
		
		return "moim/write";
	}
	//RestController를 뷰를 전송하기 위해서 사용하는 것이 아니라 데이터 처리하기 위한 컨트롤러
	
	@GetMapping("/test-3")
	public String test3Handle() {
		
		return "redirect:/moim/list";
	}
	
}
