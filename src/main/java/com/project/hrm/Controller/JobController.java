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

import com.project.hrm.domain.Job;

@Controller
public class JobController {
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
		@RequestMapping(value="/job/selectJob")
		public String selectJob(Integer pageIndex,
				@ModelAttribute Job job,
				Model model){
			System.out.println("job-->"+job);
			PageModel pageModel=new PageModel();
			if(pageIndex!=null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Job> jobs=hrmService.SelectBypage(job, pageModel);
			model.addAttribute("job",job);
			model.addAttribute("jobs",jobs);
			model.addAttribute("pageModel",pageModel);
			return "job/job";
		}
		/*
		 * �����������
		 * @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ���޸Ĳ���
		 * @param Job ����
		 * @param ModelAndView mv
		 * @return ��תҳ��
		 */
		@RequestMapping(value="/job/updateJob")
		public ModelAndView updateDept(
				String flag,
				@ModelAttribute Job job,
				ModelAndView mv){
			if(flag.equals("1")){
	                
				Job target=hrmService.selectJobById(job.getId());
				mv.addObject("job",target);
				mv.setViewName("job/showupdateJob");
			}else{
				hrmService.updateJob(job);
				mv.setViewName("redirect:/job/selectJob");
			}	
			return mv;
		}
		/*
		 * �����������
		  @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ�в������
		 * @param Job ����
		 * @param ModelAndView mv
		 * @return ��תҳ��
		 */
		@RequestMapping(value="/job/insertJob")
		public ModelAndView insertDept(
				String flag,
				@ModelAttribute Job job,
				ModelAndView mv,
				HttpServletRequest request) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(flag.equals("1")){
				mv.setViewName("job/showinsertJob");
			}else{
				hrmService.insertJob(job);
				mv.setViewName("redirect:/job/selectJob");
			}
			return mv;
		}
		
		@RequestMapping(value="/dept/deleteJob")
		public ModelAndView removeDept(String ids,ModelAndView mv){
			String[] idArray=ids.split(",");
			for(String id:idArray){
				hrmService.deletejobById(Integer.parseInt(id));
			}
			mv.setViewName("redirect:/job/selectJob");
			return mv;
		}
}
