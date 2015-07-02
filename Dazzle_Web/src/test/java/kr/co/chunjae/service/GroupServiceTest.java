package kr.co.chunjae.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.chunjae.dao.GroupDAO;
import kr.co.chunjae.entities.group.GroupEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration("classpath:configs/mvc-dispatcher-servlet.xml")
public class GroupServiceTest {
	@Mock
	private GroupDAO groupDAO;
	
	//@InjectMocks
	//private GroupServiceImpl GroupService = new GroupServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testSelectGroupList() throws Exception {
		
		GroupEntity groupEntity = new GroupEntity();
		
		groupEntity.setGroupName("testGroupName");
		groupEntity.setGroupKey(1L);
		groupEntity.setLeaderName("testLeaderName");
		groupEntity.setGroupMemberCount(1);
		groupEntity.setQuestionCount(0);
		groupEntity.setAnswerCount(0);
		List<GroupEntity> groupList = new ArrayList<GroupEntity>();
		
		Mockito.when(groupDAO.selectGroupList(groupEntity)).thenReturn(groupList);
		
		Assert.assertNotNull(groupList);
		Assert.assertEquals(1, groupList.size());
	}

	/*@Test
	public void testSelectGroup() {
		long groupKey = 0;
		groupDAO.selectGroup(groupKey);
	}

	@Test
	public void testDeleteGroup() {
		long groupKey = 0;
		groupDAO.deleteGroup(groupKey);
	}*/

}
