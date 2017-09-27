package Entity;

import java.io.Serializable;

public class Student implements Serializable {
	private Subject sub;
	private BanJi bj;
	private int score;
	private String Name;
	private String Sex;
	private int Age;
	private int ID;
	private String photo;

	
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public Subject getSub() {
		return sub;
	}
	
	public void setSub(Subject sub) {
		this.sub = sub;
	}

	public BanJi getBj() {
		return bj;
	}

	public void setBj(BanJi bj) {
		this.bj = bj;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}



	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

}
