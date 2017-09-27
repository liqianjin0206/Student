package Entity;

import java.util.List;

public class BanJi {
   private String name;
   private int  id;
   private int stuNums;
   private List<Subject> subs=null;
   
public String getName() {
	return name;
}
public List<Subject> getSubs() {
	return subs;
}
public void setSubs(List<Subject> subs) {
	this.subs = subs;
}
public void setName(String name) {
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getStuNums() {
	return stuNums;
}
public void setStuNums(int stuNums) {
	this.stuNums = stuNums;
}

   
   
}
