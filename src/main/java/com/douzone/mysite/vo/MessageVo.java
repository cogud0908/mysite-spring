package com.douzone.mysite.vo;

public class MessageVo {

	private int no;
	private String content;
	private String name;
	private int user_no;
	private int board_no;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	@Override
	public String toString() {
		return "MessageVo [no=" + no + ", content=" + content + ", name=" + name + ", user_no=" + user_no
				+ ", board_no=" + board_no + "]";
	}
}
