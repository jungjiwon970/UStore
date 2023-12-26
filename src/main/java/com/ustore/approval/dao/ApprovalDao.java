package com.ustore.approval.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ustore.approval.dto.ApprovalDto;

@Mapper
public interface ApprovalDao {

	ApprovalDto findByCommonIdx(Integer common_idx);

	void saveHtmlByCommonIdx(String html, Integer common_idx);

	
	
}