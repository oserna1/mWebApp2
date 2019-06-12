package mWebApp2.backEnd;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mWebApp2.dao.MoodDao;
import com.mWebApp2.model.Mood;
import com.mWebApp2.model.MoodData;
import com.mWebApp2.model.User;
import com.mWebApp2.service.MoodServiceImpl;
import com.mWebApp2.service.UserService;

public class MoodServiceTest {
	
	@Mock
	private MoodDao moodDaoMock;
	
	@Mock
	private UserService userServiceMock;
	
	@InjectMocks
	private MoodServiceImpl moodService;
	
	static Mood expectedMood;
	static Mood actualMood;
	static MoodData mockedMood;
	static User mockedUser;
	static List<MoodData> moodEntityList = new ArrayList<MoodData>();
	static List<Mood> moodModelViewList = new ArrayList<Mood>();
	
	@BeforeTest
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());	
		
		mockedUser = new User((long)3, "", "", "");
		expectedMood = new Mood((long)1, 5, "happy", timestamp, (long)3);		
		mockedMood = new MoodData((long)1, 5, "happy", timestamp, mockedUser);
		moodEntityList.add(mockedMood);
		moodModelViewList.add(expectedMood);
		
	}
	
	@Test
	public void testAddMeeting_returnsMeeting() {
		Mockito.when(userServiceMock.findById(Mockito.any(Long.class))).thenReturn(mockedUser);
		Mockito.when(moodDaoMock.saveMood(Mockito.any(MoodData.class))).thenReturn(mockedMood);	
		actualMood = moodService.saveMood(expectedMood);
		Assert.assertTrue(new ReflectionEquals(expectedMood,"ts").matches(actualMood));
		
	}
	
	@Test
	public void testRetrieveMeetings_returnsMeetings() {
		Mockito.when(moodDaoMock.findByUid(Mockito.any(Long.class))).thenReturn(moodEntityList);	
		Assert.assertNotNull(moodService.findByUid(expectedMood.getUid()));
	}
	

}
