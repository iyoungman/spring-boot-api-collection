package com.eney.bitlyapi.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.swisstech.bitly.model.Response;
import net.swisstech.bitly.model.v3.ExpandResponse;
import net.swisstech.bitly.model.v3.ShortenResponse;

/**
 * Created by YoungMan on 2019-05-20.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UrlResponseDto {

	private int statusCode;
	private String statusText;
	private String shortenUrl;
	private String longUrl;

	@Builder
	public UrlResponseDto(int statusCode, String statusText, String shortenUrl, String longUrl) {
		this.statusCode = statusCode;
		this.statusText = statusText;
		this.shortenUrl = shortenUrl;
		this.longUrl = longUrl;
	}

	public static UrlResponseDto toShorten(Response<ShortenResponse> response) {
		return UrlResponseDto.builder()
				.statusCode(response.status_code)
				.statusText(response.status_txt)
				.shortenUrl(response.data.url)
				.longUrl(response.data.long_url)
				.build()
		;
	}

	public static UrlResponseDto toLong(Response<ExpandResponse> response) {
		return UrlResponseDto.builder()
				.statusCode(response.status_code)
				.statusText(response.status_txt)
				.shortenUrl(response.data.expand.get(0).short_url)
				.longUrl(response.data.expand.get(0).long_url)
				.build()
		;
	}

	@Override
	public String toString() {
		return "UrlResponseDto{" +
				"statusCode=" + statusCode +
				", statusText='" + statusText + '\'' +
				", shortenUrl='" + shortenUrl + '\'' +
				", longUrl='" + longUrl + '\'' +
				'}';
	}
}
