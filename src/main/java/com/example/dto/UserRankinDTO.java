package com.example.dto;

import java.util.List;

import com.example.model.User;

/**
 * Created by Sara ZALARHE on 24/05/17.
 */
public class UserRankinDTO {

	private Long rankMoney;
	private Long rankLevel;
	private Long rankExperience;
	private Long nbrUsers;
	
	private List<UserDTO> top10Money;
	private List<UserDTO> top10Level;
	private List<UserDTO> top10Experience;

	public Long getRankMoney() {
		return rankMoney;
	}

	public void setRankMoney(Long rankMoney) {
		this.rankMoney = rankMoney;
	}

	public Long getRankLevel() {
		return rankLevel;
	}

	public void setRankLevel(Long rankLevel) {
		this.rankLevel = rankLevel;
	}

	public Long getRankExperience() {
		return rankExperience;
	}

	public void setRankExperience(Long rankExperience) {
		this.rankExperience = rankExperience;
	}

	public Long getNbrUsers() {
		return nbrUsers;
	}

	public void setNbrUsers(Long nbrUsers) {
		this.nbrUsers = nbrUsers;
	}

	public List<UserDTO> getTop10Money() {
		return top10Money;
	}

	public void setTop10Money(List<UserDTO> top10Money) {
		this.top10Money = top10Money;
	}

	public List<UserDTO> getTop10Level() {
		return top10Level;
	}

	public void setTop10Level(List<UserDTO> top10Level) {
		this.top10Level = top10Level;
	}

	public List<UserDTO> getTop10Experience() {
		return top10Experience;
	}

	public void setTop10Experience(List<UserDTO> top10Experience) {
		this.top10Experience = top10Experience;
	}

	
}
