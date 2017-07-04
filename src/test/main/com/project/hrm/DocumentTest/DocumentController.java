package com.project.hrm.DocumentTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.project.hrm.BaseTest.BaseTest;

@WebAppConfiguration(value="src/main/resources")
@TransactionConfiguration(defaultRollback = true)  
@Transactional 
public class DocumentController  extends BaseTest {
	@Autowired  
    private WebApplicationContext wac;  //ע��Web������applicationContext����
    private MockMvc mockMvc; 
    
    @Before  
    public void setup() {   
        this.mockMvc =MockMvcBuilders.webAppContextSetup(this.wac).build();  
    } 
  
      
    /*
       * ����ģ����ѯ
       */

    @Test
    public void testselectDocument() throws Exception{
    	mockMvc.perform((post("/document/selectDocument").param("pageIndex", "1"))).andExpect(status().isOk())
    	.andDo(print());
    }
    
    /*
     *���Ը������� 
     */

    @Test
    public void testupdateDocument() throws Exception{
    	mockMvc.perform((post("/employee/updateDocument").param("title","abc").param("remark", "abc").param("flag", "1"))).andExpect(status().isOk())
    	.andDo(print());
    }
    
    /*
     * �����������
     */

    @Test
    public void testinsertDocument() throws Exception{
    	mockMvc.perform((post("/employee/insertDocument").param("title", "abc").param("remark", "1").param("flag", "1"))).andExpect(status().isOk())
    	.andDo(print());
    }
   /*
    * ����ɾ������
    */
    @Test
    public void testdeleteDocument() throws Exception{
    	mockMvc.perform((post("/employee/deleteDocument").param("title", "1,2").param("remark", "1").param("flag", "1"))).andExpect(status().isOk())
    	.andDo(print());
    }
}
