package com.zhao.spring_boot_sign;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhao.spring_boot_sign.sign.SignDemo;
import com.zhao.spring_boot_sign.sign.SignRepository;

@Controller
public class SignController {

	@Autowired
	private SignRepository signRepository;
	
	/*
	 * 处理日期类型
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@PostMapping("/save")
	public @ResponseBody Map<String, Object> save(@RequestBody SignDemo entity) {
		Map<String, Object> result = new HashMap<>();
		entity = signRepository.save(entity);
		result.put("id", entity.id);
		return result;
	}
	
	@PostMapping("/findAll")
	public @ResponseBody Object findAll() {
		return signRepository.findAll();
	}
	
	@PostMapping("/findAllByNoLike")
	public @ResponseBody Object findAllByNoLike(@RequestParam String no) {
		return signRepository.findAllByUsernameLike("%" + no + "%");
	}
	
	@PostMapping("/findAllByDateBetween")
	public @ResponseBody Object findAllByDateBetween(@RequestParam Date startDate, @RequestParam Date endDate) {
		return signRepository.findAllByDateBetween(startDate, endDate);
	}
	
	
	
}
