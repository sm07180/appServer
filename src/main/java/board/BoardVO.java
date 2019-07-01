package board;

import java.sql.Date;

public class BoardVO {
	private String board_title, board_content, user_id, comp_id, kind, mycar,car_name;
	private String Mfilepath,filepath, filename,board_user_nick;
	private int board_id, readcnt,board_reply_cnt,sympathy;
	private Date board_date;

	
	
	
	
	
	
	
	public String getBoard_user_nick() {
		return board_user_nick;
	}

	public void setBoard_user_nick(String board_user_nick) {
		this.board_user_nick = board_user_nick;
	}

	public int getSympathy() {
		return sympathy;
	}

	public void setSympathy(int sympathy) {
		this.sympathy = sympathy;
	}

	public int getBoard_reply_cnt() {
		return board_reply_cnt;
	}

	public void setBoard_reply_cnt(int board_reply_cnt) {
		this.board_reply_cnt = board_reply_cnt;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getMfilepath() {
		return Mfilepath;
	}

	public void setMfilepath(String mfilepath) {
		Mfilepath = mfilepath;
	}

	public String getMycar() {
		return mycar;
	}

	public void setMycar(String mycar) {
		this.mycar = mycar;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
}