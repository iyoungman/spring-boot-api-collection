package com.eney.bitlyapi.service;

import com.eney.bitlyapi.exception.UserDefineException;
import com.eney.bitlyapi.model.TokenInfo;
import com.eney.bitlyapi.model.dto.LinkInfoResponseDto;
import com.eney.bitlyapi.model.dto.UrlResponseDto;
import com.eney.bitlyapi.model.enums.Unit;
import net.swisstech.bitly.BitlyClient;
import net.swisstech.bitly.model.Response;
import net.swisstech.bitly.model.v3.ExpandResponse;
import net.swisstech.bitly.model.v3.LinkClicksExpanded;
import net.swisstech.bitly.model.v3.ShortenResponse;
import net.swisstech.bitly.model.v3.UserClicksExpanded;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YoungMan on 2019-05-20.
 */

@Service
public class ShortenUrlService {

	/**
	 * Original Long Url -> bit-ly Shorten Url
	 */
	public UrlResponseDto convertToShortenUrl(String longUrl) {

		try {
			BitlyClient client = new BitlyClient(TokenInfo.ACCESS_TOKEN);
			Response<ShortenResponse> response = client.shorten()
					.setLongUrl(longUrl)
					.call()
			;

			return UrlResponseDto.toShorten(response);
		} catch (Exception e) {
			throw UserDefineException.builder()
					.message("Long URL -> 단축 URL 변환 실패")
					.originalMessage(e.toString())
					.build()
			;
		}
	}

	/**
	 * bit-ly Shorten Url -> Original Long Url
	 */
	public UrlResponseDto convertToLongUrl(String shortenUrl) {
		try {
			BitlyClient client = new BitlyClient(TokenInfo.ACCESS_TOKEN);
			Response<ExpandResponse> response = client.expand()
					.addShortUrl(shortenUrl)
					.call()
			;

			return UrlResponseDto.toLong(response);
		} catch (Exception e) {
			throw UserDefineException.builder()
					.message("단축 URL -> Long URL 변환 실패")
					.originalMessage(e.toString())
					.build()
			;
		}
	}

	/**
	 * 링크 정보 조회
	 */
	public List<LinkInfoResponseDto> fetchLinkClickInfos(String shortenUrl, Unit unit) {
		try {
			BitlyClient client = new BitlyClient(TokenInfo.ACCESS_TOKEN);
			Response<LinkClicksExpanded> response = client.linkClicksExpanded()
					.setTimezone(9)
					.setLink(shortenUrl)
					.setUnit(unit.toString())
					.setLimit(1000)
					.call()
			;

			return response.data.link_clicks.stream()
					.map(s -> LinkInfoResponseDto.of(unit.convertLongToDateStr(s.dt),
							(int) s.clicks)
					)
					.collect(Collectors.toList())
			;
		} catch (Exception e) {
			throw UserDefineException.builder()
					.message("링크정보 조회 실패")
					.originalMessage(e.toString())
					.build()
			;
		}
	}

	/**
	 * 사용자의 총 링크 횟수 조회
	 */
	public int fetchLinkClickInfosByUser() {
		try {
			BitlyClient client = new BitlyClient(TokenInfo.ACCESS_TOKEN);
			Response<UserClicksExpanded> response = client.userClicksExpanded()
					.setTimezone(9)
					.call()
			;

			return (int) response.data.total_clicks;
		} catch (Exception e) {
			throw UserDefineException.builder()
					.message("사용자의 총 링크정보 조회 실패")
					.originalMessage(e.toString())
					.build()
			;
		}
	}

}
