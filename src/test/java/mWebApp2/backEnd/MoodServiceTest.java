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
	
	private Mood expectedMood;
	private Mood actualMood;
	private MoodData mockedMood;
	private User mockedUser;
	private List<MoodData> moodEntityList = new ArrayList<MoodData>();
	private List<Mood> moodModelViewList = new ArrayList<Mood>();
	
	@BeforeTest
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());	
		
		mockedUser = new User(3L, "", "", "");
		expectedMood = new Mood(1L, 5, "happy", timestamp, 3L);		
		mockedMood = new MoodData(1L, 5, "happy", timestamp, mockedUser);
		moodEntityList.add(mockedMood);
		moodModelViewList.add(expectedMood);
		
	}
	
	@Test
	public void testAddMood_returnsMood() {
		Mockito.when(moodDaoMock.saveMood(Mockito.any(MoodData.class))).thenReturn(mockedMood);
		
		actualMood = moodService.saveMood(expectedMood);
		Assert.assertTrue(new ReflectionEquals(expectedMood,"ts").matches(actualMood));
		Assert.assertNotNull(actualMood.getTs());
	}
	
	@Test
	public void testRetrieveMoodsByUserId_returnsMoods() {
		Mockito.when(moodDaoMock.findByUid(Mockito.any(Long.class))).thenReturn(moodEntityList);	
		
		Assert.assertNotNull(moodService.findByUid(expectedMood.getUid()));
		Assert.assertFalse(moodService.findByUid(expectedMood.getUid()).isEmpty());
		compareMoodLists(moodService.findByUid(expectedMood.getUid()));
	}
	
	@Test
	public void testRetrieveAllMoods_returnMoods() {
		Mockito.when(moodDaoMock.findAllMoods()).thenReturn(moodEntityList);
		
		Assert.assertNotNull(moodService.findAllMoods());
		Assert.assertFalse(moodService.findAllMoods().isEmpty());
		compareMoodLists(moodService.findAllMoods());
	}
	
	@Test
	public void testRetrieveMood_returnMood() {
		Mockito.when(moodDaoMock.findById(Mockito.any(Long.class))).thenReturn(mockedMood);
		actualMood = moodService.findById(expectedMood.getId());
		Assert.assertTrue(new ReflectionEquals(expectedMood,"ts").matches(actualMood));
	}
	
	@Test
	public void testDeleteMood_returnsNumOfDeleted() {
		Mockito.when(moodDaoMock.deleteMoodbyId(Mockito.any(Long.class))).thenReturn(1);
		Assert.assertTrue(moodService.deleteMoodById(actualMood.getId()));
	}
	
	@Test
	public void testDeleteMood_returnsFalse() {
		Mockito.when(moodDaoMock.deleteMoodbyId(Mockito.any(Long.class))).thenReturn(0);
		Assert.assertFalse(moodService.deleteMoodById(actualMood.getId()));
	}
	
	@Test
	public void testUpdateMood_returnsMood() {
		Mockito.when(moodDaoMock.updateMood(Mockito.any(MoodData.class))).thenReturn(mockedMood);
		actualMood = moodService.updateMood(expectedMood);
		Assert.assertTrue(new ReflectionEquals(expectedMood,"ts").matches(actualMood));
		Assert.assertNotNull(actualMood.getTs());
	}

	
	@Test
	public void testConvertEntity_returnViewModel() {
		actualMood = moodService.convertEntity(mockedMood);
		Assert.assertTrue(new ReflectionEquals(expectedMood,"ts").matches(actualMood));
	}
	
	@Test
	public void testConvertViewModel_returnEntity() {
		Mockito.when(userServiceMock.findById(Mockito.any(Long.class))).thenReturn(mockedUser);
		Assert.assertTrue(new ReflectionEquals(mockedMood,"ts").matches(moodService.convertViewModel(expectedMood)));
	}
	
	public void compareMoodLists(List<Mood> mockedMoodList) {
		for(Mood moodActual : mockedMoodList){
			for(Mood moodExpected: moodModelViewList ) {
				Assert.assertTrue(new ReflectionEquals(moodExpected,"ts").matches(moodActual));
			}	
		}
	}
	

}
