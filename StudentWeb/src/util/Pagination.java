package util;

public class Pagination {

	private int yeNum;//一页多少条数据
	private int yeMa;// 分多少页
	private int begin;
	private int end;

	private int ye;
	private int maxYe;


	public Pagination(int max, int ye, int yeNum, int yeMa) {
		this.ye = ye;
		this.yeNum = yeNum;
		this.yeMa = yeMa;



		// 最小页
		if (this.ye < 1) {
			this.ye = 1;
		}
		// 换算最多页
		this.maxYe = max % yeNum == 0 ? max / yeNum :max / yeNum +1;
if(this.maxYe==0){
	this.maxYe=1;
}
		// 最后页
		if (this.ye > maxYe) {
			this.ye = maxYe;
		}

		// 换算开始和结束

		begin = ye - yeMa / 2;
		end = ye + yeMa / 2;

		if (begin < 1) {
			end = yeMa;
			begin = 1;
		}
		if (end > maxYe) {
			end = maxYe;
			begin = maxYe - yeMa + 1;
		}
		if(maxYe<yeMa){
			begin=1;
			end=maxYe;
		}

	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getYe() {
		return ye;
	}

	public void setYe(int ye) {
		this.ye = ye;
	}

	public int getMaxYe() {
		return maxYe;
	}

	public void setMaxYe(int maxYe) {
		this.maxYe = maxYe;
	}
}
