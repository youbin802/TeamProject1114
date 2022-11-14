package vo;

public class NoticeVO {
	String id;
	String type;
	String title;
	String content;
	String writer_id;
	String writer_date;
	
	
	public NoticeVO(String id,	String type,String title,  String content, String writer_id, String writer_date) {
		this.id= id;
		this.type = type;
		this.title = title;
		this.content =content;
		this.writer_id=writer_id;
		this.writer_date= writer_date;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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
