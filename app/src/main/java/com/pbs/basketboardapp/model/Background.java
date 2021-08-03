package com.pbs.basketboardapp.model;

public class Background {
	
	private Integer image;
	private boolean selected;

	public Background() {
	}

	public Background(Integer image, boolean selected) {
		this.image = image;
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getImage() {
		return image;
	}

	public void setImage(Integer image) {
		this.image = image;
	}

}
