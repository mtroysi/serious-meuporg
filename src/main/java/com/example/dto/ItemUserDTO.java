package com.example.dto;

import java.util.Date;

import com.example.enumeration.ItemEnum;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

public class ItemUserDTO extends ItemDTO {

    private Date dateEnd;

    
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
}
