package com.example.dto;

import java.util.Date;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

public class ItemUserDTO extends ItemDTO {

    private Date dateEnd;
    private Boolean active;

    
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
