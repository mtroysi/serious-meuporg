package com.example.service;

import com.example.dto.BoardWithDetailDTO;

import java.util.List;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */


public interface BoardUserService {

    /**
     * Get list of BoardWithDetail by userId
     * @param user_id
     * @return
     */
    List<BoardWithDetailDTO> getListBoardByUserId(Long user_id);
}
