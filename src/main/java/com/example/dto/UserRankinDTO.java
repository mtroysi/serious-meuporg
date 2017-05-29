package com.example.dto;

/**
 * Created by Sara ZALARHE on 24/05/17.
 */
public class UserRankinDTO {

	private Long rankMoney;
	private Long rankLevel;
	private Long rankExperience;
	private Long nbrUsers;

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

}
