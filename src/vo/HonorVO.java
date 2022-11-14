package vo;

public class HonorVO {
	int id;
	String content;
	String writer_id;
	String writer_date;
	
	public HonorVO(int id,String  content, String writer_id, String  writer_date ) {
		this.id = id;
		this.content = content;
		this.writer_id= writer_id;
		this.writer_date = writer_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
