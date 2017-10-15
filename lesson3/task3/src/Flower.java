public abstract class Flower {
	
	private String group;
	private String sort;
	private String color;
	private double price;
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Flower(String group, String sort, String color, double price) {
		this.group = group;
		this.sort = sort;
		this.color = color;
		this.price = price;
	}	
}
