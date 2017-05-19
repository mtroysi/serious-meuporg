package com.example.dto;

public class BoardWithDetailDTO {

	private Long id;
    private String name;
    private String color;
    private String role;

    public BoardWithDetailDTO(Long id, String name, String color, String code) {
        this.id = id;
        this.name = name;
        this.role = code;
        this.color = color;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
