package com.example.dto;

/**
 * Created by Sara ZALARHE on 23/05/17.
 */
public class UserStatsDTO {
	
	private Long nbrTaskDone;
	private Long nbrTaskCreated;
	private int nbrBoardCreated;
	private Long nbrTaskBid;
	private Long rankingByLevel;
	private Long rankingByMoney;
	private Long rankingByExperience;

	public Long getNbrTaskDone() {
		return nbrTaskDone;
	}

	public void setNbrTaskDone(Long nbrTaskDone) {
		this.nbrTaskDone = nbrTaskDone;
	}

	public Long getNbrTaskCreated() {
		return nbrTaskCreated;
	}

	public void setNbrTaskCreated(Long nbrTaskCreated) {
		this.nbrTaskCreated = nbrTaskCreated;
	}

	public int getNbrBoardCreated() {
		return nbrBoardCreated;
	}

	public void setNbrBoardCreated(int nbrBoardCreated) {
		this.nbrBoardCreated = nbrBoardCreated;
	}

	public Long getNbrTaskBid() {
		return nbrTaskBid;
	}

	public void setNbrTaskBid(Long nbrTaskBid) {
		this.nbrTaskBid = nbrTaskBid;
	}

	public Long getRankingByLevel() {
		return rankingByLevel;
	}

	public void setRankingByLevel(Long rankingByLevel) {
		this.rankingByLevel = rankingByLevel;
	}

	public Long getRankingByMoney() {
		return rankingByMoney;
	}

	public void setRankingByMoney(Long rankingByMoney) {
		this.rankingByMoney = rankingByMoney;
	}

	public Long getRankingByExperience() {
		return rankingByExperience;
	}

	public void setRankingByExperience(Long rankingByExperience) {
		this.rankingByExperience = rankingByExperience;
	}

}
