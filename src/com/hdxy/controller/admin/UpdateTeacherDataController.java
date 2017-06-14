package com.hdxy.controller.admin;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdxy.pojo.ScoreInput;
import com.hdxy.pojo.TeacherData;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class UpdateTeacherDataController {
	
	@RequestMapping(value = "/teacher_data_input", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String scoreInput(@RequestBody  String data) {
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ScoreInput.class);  
        List<TeacherData> list = null; 
        try {
			list = objectMapper.readValue(data, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnMessageUtil.MESSAGE_WRONG;
		}
        if(list == null) return ReturnMessageUtil.SYSTEM_BUSY;
        //以下开始存储数据
        StringBuilder sb = new StringBuilder();
        TeacherData t = new TeacherData();
        for (int i = 0; i < list.size(); i++) {
			t = list.get(i);
			if(t.getJobNumber() == null || t.getName() == null) {
				sb.append("第" + (i + 2) + "行数据为空");
				continue;
			}
		}
        String error = sb.toString(); //获取出错信息，并返回
        if(!error.equals("")) {
        	return error;
        }
		return ReturnMessageUtil.TRUE;
	}
}
