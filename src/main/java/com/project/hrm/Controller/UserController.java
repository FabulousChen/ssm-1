package com.project.hrm.Controller;



import java.util.List;

import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.hrm.Page.PageModel;
import com.project.hrm.Service.HrmService;
import com.project.hrm.domain.User;

@Controller

public class UserController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	/*
	 * �����¼����
	 * @param String loginname ��¼��
	 * @param String password ����
	 * @return ��ת����ͼ
	 */
	@RequestMapping(value="/login")	
	public ModelAndView login(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			HttpSession session,
			ModelAndView mv
			){
		
		User user=hrmService.login(loginname, password);
		session.setAttribute("user",user);
		if(user!=null){
		mv.setViewName("main");
		}else{
			mv.setViewName("forward:/index.jsp");
		}
		return mv;
	}
	/*
	 * ģ���ѯ
	 * @param pageIndex  ��ǰҳ��
	 * @param User ����
	 * @param Model model
	 * @return ��תҳ
	 */
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex,
			@ModelAttribute User user,
			Model model){
		System.out.println("user-->"+user);
		PageModel pageModel=new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(pageIndex);
		}
		List<User> users=hrmService.selectAllUser(user, pageModel);
		model.addAttribute("user",user);
		model.addAttribute("users",users);
		model.addAttribute("pageModel",pageModel);
		return "user/user";
	}
	/*
	 * �����������
	 * @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ���޸Ĳ���
	 * @param User ����
	 * @param ModelAndView mv
	 * @return ��תҳ��
	 */
	@RequestMapping(value="/user/updateUser")
	public ModelAndView updateUser(
			String flag,
			@ModelAttribute User user,
			ModelAndView mv){
		if(flag.equals("1")){

			User target=hrmService.selectUserByid(user.getId());
			mv.addObject("user",target);
			mv.setViewName("user/showupdateUser");
		}else{
			hrmService.updateUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}	
		return mv;
	}
	/*
	 * �����������
	  @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ�в������
	 * @param User ����
	 * @param ModelAndView mv
	 * @return ��תҳ��
	 */
	@RequestMapping(value="/user/insertUser")
	public ModelAndView insertUser(
			String flag,
			@ModelAttribute User user,
			ModelAndView mv){
		if(flag.equals("1")){
			mv.setViewName("user/showinsertUser");
		}else{
			hrmService.insertUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
	
	@RequestMapping(value="/user/deleteUser")
	public ModelAndView removeUser(String ids,ModelAndView mv){
		String[] idArray=ids.split(",");
		for(String id:idArray){
			hrmService.deleteUser(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/user/selectUser");
		return mv;
	}

}