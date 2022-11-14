package vo;

import java.util.ArrayList;

public class BoardVO {
	int id;
	String name;
	String title;
	String content;
	String writer_id;
	String writer_date;
	
	public BoardVO() {
		
	}
	public BoardVO(String name, int id,String title,String content,String writer_id, String writer_date) {
		this.name = name;
		this.id=id;
		this.title= title;
		this.content=content;
		this.writer_id=writer_id;
		this.writer_date = writer_date;
	}
	public String getName() {
		return name;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getWriter_date() {
		return writer_date;
	}
	public void setWriter_date(String writer_date) {
		this.writer_date = writer_date;
	}
	
	
}
