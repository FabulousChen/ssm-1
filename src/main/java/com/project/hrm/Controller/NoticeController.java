package com.project.hrm.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.hrm.Page.PageModel;
import com.project.hrm.Service.HrmService;
import com.project.hrm.domain.Notice;
import com.project.hrm.domain.User;

@Controller
public class NoticeController {
	 @Autowired
	   @Qualifier("hrmService")
	   private HrmService hrmService;
	   /*
		 * ģ���ѯ
		 * @param pageIndex  ��ǰҳ��
		 * @param Notice ����
		 * @param Model model
		 * @return ��תҳ
		 */
		@RequestMapping(value="/notice/selectNotice")
		public String selectDept(Integer pageIndex,
				@ModelAttribute Notice notice,
				Model model){
			System.out.println("notice-->"+notice);
			PageModel pageModel=new PageModel();
			if(pageIndex!=null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Notice> notices=hrmService.SelectBypage(notice, pageModel);
			model.addAttribute("notice",notice);
			model.addAttribute("notices",notices);
			model.addAttribute("pageModel",pageModel);
			return "notice/notice";
		}
		/*
		 * �����������
		 * @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ���޸Ĳ���
		 * @param Notice ����
		 * @param ModelAndView mv
		 * @return ��תҳ��
		 */
		@RequestMapping(value="/notice/updateNotice")
		public ModelAndView updateNotice(
				String flag,
				@ModelAttribute Notice notice,
				ModelAndView mv){
			if(flag.equals("1")){
	                
				Notice target=hrmService.selectNoticeById(notice.getId());
				mv.addObject("notice",target);
				mv.setViewName("notice/showupdateNotice");
			}else{
				hrmService.updateNotice(notice);
				mv.setViewName("redirect:/notice/selectNotice");
			}	
			return mv;
		}
		/*
		 * �����������
		  @param String flag ����1��ʾ��ת������ҳ�棬2��ʾִ�в������
		 * @param Notice ����
		 * @param ModelAndView mv
		 * @return ��תҳ��
		 */
		@RequestMapping(value="/notice/insertNotice")
		public ModelAndView insertDept(
				String flag,
				@ModelAttribute Notice notice,
				ModelAndView mv,
				HttpServletRequest request,
				HttpSession session) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		    
			if(flag.equals("1")){
				mv.setViewName("notice/showinsertNotice");
			}else{
				User user=(User) session.getAttribute("user");
				System.out.println("user"+user);
				notice.setUser(user);
				hrmService.insertNotice(notice);
				mv.setViewName("redirect:/notice/selectNotice");
			}
			return mv;
		}
		
		@RequestMapping(value="/notice/deleteNotice")
		public ModelAndView removeDept(String ids,ModelAndView mv){
			String[] idArray=ids.split(",");
			for(String id:idArray){
				hrmService.deletenoticeById(Integer.parseInt(id));
			}
			mv.setViewName("redirect:/notice/selectNotice");
			return mv;
		}
		/*
		 * Ԥ������
		 * @param id
		 * @param model
		 * @return Ԥ��ҳ��
		 */
		@RequestMapping(value="/notice/previewNotice")
		public String previewNotice(
				Integer id,Model model){
			Notice notice=hrmService.selectNoticeById(id);
			model.addAttribute("notice",notice);
			return "notice/previewNotice";
		}
}
