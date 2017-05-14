package com.example.dto;

public class BoardWithDetailDTO {

	private Long id;
    private String name;
    private String role;

    public BoardWithDetailDTO(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.role = code;
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

}
