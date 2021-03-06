package bookmall.vo;

public class CategoryVo {

	private int no;
	private String name;
		
	public CategoryVo() {
		super();
	}

	public CategoryVo(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Category [no=" + no + ", name=" + name + "]";
	}

}
