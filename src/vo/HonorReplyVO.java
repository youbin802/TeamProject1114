package vo;

public class HonorReplyVO {
	int honorId;
	String name;
	String writerId;
	int replyId;
	String content;
	String date;
	
	public HonorReplyVO(int honorId, String name, String writerId, int replyId, String content, String date) {
		this.honorId= honorId;
		this.name = name;
		this.writerId= writerId;
		this.replyId = replyId;
		this.content = content;
		this.date = date;
	}

	public int getHonorId() {
		return honorId;
	}

	public void setHonorId(int honorId) {
		this.honorId = honorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

	
	
}
