package vo;

public class ReplyVO {
	int id;
	String content;
	int board_id;
	String writer_id;
	String writer_date;
	String writer_name;
	public ReplyVO() {
		
	}
	
	public ReplyVO(int id, String content, int board_id, String writer_id, String writer_date, String name) {
		this.id= id;
		this.content=content;
		this.board_id=board_id;
		this.writer_id=writer_id;
		this.writer_date=writer_date;
		this.writer_name = name;
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
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
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
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}
	
	
}
