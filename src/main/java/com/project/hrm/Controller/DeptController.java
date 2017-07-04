package com.project.hrm.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.hrm.Page.PageModel;
import com.project.hrm.Service.HrmService;
import com.project.hrm.domain.Dept;


@Controller
public class DeptController {
   @Autowired
   @Qualifier("hrmService")
   private HrmService hrmService;
   
   /*
	 * ģ���ѯ
	 * @param pageIndex  ��ǰҳ��
	 * @param Dept ����
	 * @param Model model
	 * @return ��תҳ
	 */
	@RequestMapping(value="/dept/selectDept")
	public String selectDept(Integer pageIndex,
			@ModelAttribute Dept dept,
			Model model){
		System.out.println("dept-->"+dept);
		PageModel pageModel=new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Dept> depts=hrmService.selectAllDept(dept, pageModel);
		model.addAttribute("dept",dept);
		model.addAttribute("depts",depts);
		model.addAttribute("pageModel",pageModel);
		return "dept/dept";
	}
	/*
	 * �����������
	 * @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ���޸Ĳ���
	 * @param Dept ����
	 * @param ModelAndView mv
	 * @return ��תҳ��
	 */
	@RequestMapping(value="/dept/updateDept")
	public ModelAndView updateDept(
			String flag,
			@ModelAttribute Dept dept,
			ModelAndView mv){
		if(flag.equals("1")){
                
			Dept target=hrmService.findDeptById(dept.getId());
			mv.addObject("dept",target);
			mv.setViewName("dept/showupdateDept");
		}else{
			hrmService.updateDept(dept);
			mv.setViewName("redirect:/dept/selectDept");
		}	
		return mv;
	}
	/*
	 * �����������
	  @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ�в������
	 * @param Dept ����
	 * @param ModelAndView mv
	 * @return ��תҳ��
	 */
	@RequestMapping(value="/dept/insertDept")
	public ModelAndView insertDept(
			String flag,
			@ModelAttribute Dept dept,
			ModelAndView mv,
			HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    System.out.println(dept.getName());
		if(flag.equals("1")){
			mv.setViewName("dept/showinsertDept");
		}else{
			hrmService.insertDept(dept);
			mv.setViewName("redirect:/dept/selectDept");
		}
		return mv;
	}
	
	@RequestMapping(value="/dept/deleteDept")
	public ModelAndView removeDept(String ids,ModelAndView mv){
		System.out.println("ids "+ids);
		String[] idArray=ids.split(",");
		for(String id:idArray){
			System.out.println(id);
			hrmService.deleteDeptById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/dept/selectDept");
		return mv;
	}
}
