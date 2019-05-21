package com.eney.bitlyapi.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-05-20.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LinkInfoResponseDto {

	private String date;
	private int clicks;

	@Builder
	public LinkInfoResponseDto(String date, int clicks) {
		this.date = date;
		this.clicks = clicks;
	}

	public static LinkInfoResponseDto of(String date, int clicks) {
		return LinkInfoResponseDto.builder()
				.date(date)
				.clicks(clicks)
				.build()
		;
	}
}
