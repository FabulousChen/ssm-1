package com.project.hrm.Service;

import java.util.List;


import com.project.hrm.Page.PageModel;
import com.project.hrm.domain.Dept;
import com.project.hrm.domain.Document;
import com.project.hrm.domain.Employee;
import com.project.hrm.domain.Job;
import com.project.hrm.domain.Notice;
import com.project.hrm.domain.User;

public interface HrmService {
   /*
    * �û���¼
    * @param loginname
    * @param password
    * @return User ����
    */
   User login(String loginname,String password);
   /*
    * ����id��ѯ�û�
    * @param id
    * @return User ����
    */
   User selectUserByid(Integer id);
   /*
    * �޸��û�
    * @param User ����
    */
   void updateUser(User user);
   /*
    * ģ����ѯ�û�
    * @param User ����,PageModel ����
    * return User���󼯺�List
    */
   List<User> selectAllUser(User user,PageModel pageModel);
   /*
    * �����û�
    * @param User ����
    */
   void insertUser(User user);
   /*
    * ����idɾ���û�
    * @param id
    */
   void deleteUser(Integer id);
   /*
    * ��ѯ�û�����
    * @param User ����
    */
   int countUser(User user);
   /*
    * ģ����ѯ���ţ���ҳ��ѯ
    * @param Dept ���Ŷ���
    * @param PageModel ����
    * @return ���ز��Ŷ��󼯺�List
    */
   List<Dept> selectAllDept(Dept dept,PageModel pageModel);
   /*
    * ��ѯ���в���
    * @return �������в���
    */
   	List<Dept> findAllDept();
   	/*
   	 * �����²���
   	 * @param Dept ����
   	 */
   	void insertDept(Dept dept);
   	/*
   	 * ����id��ѯ����
   	 * @param id
   	 * @return ����
   	 */
   	Dept findDeptById(Integer id);
   	/*
   	 * �޸Ĳ���
   	 * @param Dept ����
   	 */
   	void updateDept(Dept dept);
   	/*
   	 * ����idɾ������
   	 * @param id
   	 */
   	void deleteDeptById(Integer id);
   	/*
     * ģ����ѯ���ţ���ҳ��ѯ
     * @param Job ְλ����
     * @param PageModel ����
     * @return ����ְλ���󼯺�List
     */
    List<Job> SelectBypage(Job job,PageModel pageModel);
    /*
     * ��ѯ���в���
     * @return ��������ְλ
     */
    	List<Job> findAllJob();
    	/*
    	 * �����²���
    	 * @param Job ����
    	 */
    	void insertJob(Job job);
    	/*
    	 * ����id��ѯְλ
    	 * @param id
    	 * @return ְλ
    	 */
    	Job selectJobById(Integer id);
    	/*
    	 * �޸Ĳ���
    	 * @param Job ְλ
    	 */
    	void updateJob(Job job);
    	/*
    	 * ����idɾ��ְλ
    	 * @param id
    	 */
    	void deletejobById(Integer id);
    	
    	/*
         * ģ����ѯ���ţ���ҳ��ѯ
         * @param Employee ְλ����
         * @param PageModel ����
         * @return ����Ա�����󼯺�List
         */
    	List<Employee> SelectBypage(Employee employee,PageModel pageModel);

        	/*
        	 * ������Ա��
        	 * @param Employee ����
        	 */
        	void insertEmployee(Employee employee);
        	/*
        	 * ����id��ѯԱ��
        	 * @param id
        	 * @return Ա��
        	 */
        	Employee selectEmployeeById(Integer id);
        	/*
        	 * �޸�Ա��
        	 * @param Employee Ա��
        	 */
        	void updateEmployee(Employee employee);
        	/*
        	 * ����idɾ��ְԱ
        	 * @param id
        	 */
        	void deleteemployeeById(Integer id);
        	/*
             * ģ����ѯ���ţ���ҳ��ѯ
             * @param Document �������
             * @param PageModel ����
             * @return ���ع�����󼯺�List
             */
        	List<Document> SelectBypage(Document document,PageModel pageModel);

            	/*
            	 * �����¹���
            	 * @param Document ����
            	 */
            	void insertDocument(Document document);
            	/*
            	 * ����id��ѯ�ļ�
            	 * @param id
            	 * @return �ļ�
            	 */
            	Document selectDocumentById(Integer id);
            	/*
            	 * �޸Ĺ���
            	 * @param Document �ļ�
            	 */
            	void updateDocument(Document document);
            	/*
            	 * ����idɾ���ļ�
            	 * @param id
            	 */
            	void deletedocumentById(Integer id);
            	/*
                 * ģ����ѯ���ţ���ҳ��ѯ
                 * @param Notice �������
                 * @param PageModel ����
                 * @return �����ļ����󼯺�List
                 */
            	List<Notice> SelectBypage(Notice notie,PageModel pageModel);

                	/*
                	 * �����¹���
                	 * @param Notice ����
                	 */
                	void insertNotice(Notice notice);
                	/*
                	 * ����id��ѯ����
                	 * @param id
                	 * @return ����
                	 */
                	Notice selectNoticeById(Integer id);
                	/*
                	 * �޸Ĺ���
                	 * @param Notice ����
                	 */
                	void updateNotice(Notice notice);
                	/*
                	 * ����idɾ������
                	 * @param id
                	 */
                	void deletenoticeById(Integer id);
}
