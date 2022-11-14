package vo;

public class UserVO {
	String id;
	String name;
	int identity;
	String deparment;
	String grade_number;
	String class_number;
	String profile_img;
	
	public UserVO() {
		
	}
	public UserVO(String id,String name,String deparment,String grade_number,String class_number,String profile_img, int identity) {
		this.id = id;
		this.name =name;
		this.deparment = deparment;
		this.grade_number= grade_number;
		this.class_number=class_number;
		this.profile_img=profile_img;
		this.identity = identity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getDeparment() {
		return deparment;
	}
	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}
	public String getGrade_number() {
		return grade_number;
	}
	public void setGrade_number(String grade_number) {
		this.grade_number = grade_number;
	}
	public String getClass_number() {
		return class_number;
	}
	public void setClass_number(String class_number) {
		this.class_number = class_number;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	

	
}
