package com.eney.bitlyapi.controller;

import com.eney.bitlyapi.model.dto.LinkInfoResponseDto;
import com.eney.bitlyapi.model.enums.Unit;
import com.eney.bitlyapi.service.ShortenUrlService;
import com.eney.bitlyapi.model.dto.UrlResponseDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by YoungMan on 2019-05-20.
 */

@RestController
@RequestMapping("/api")
public class ShortenUrlController {

	private ShortenUrlService shortenUrlService;

	public ShortenUrlController(ShortenUrlService shortenUrlService) {
		this.shortenUrlService = shortenUrlService;
	}


	@ApiOperation(value = "기존 URL을 단축 URL로 변환", notes = "기존 URL을 단축 URL로 변환")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "url", value = "기존 URL", required = true)
	})
	@GetMapping("/shorten")
	public UrlResponseDto convertToShortenUrl(@RequestParam("url") String longUrl) {
		return shortenUrlService.convertToShortenUrl(longUrl);
	}


	@ApiOperation(value = "단축 URL로 기존 URL조회", notes = "단축 URL로 기존 URL조회")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "url", value = "단축 URL", required = true)
	})
	@GetMapping("/long")
	public UrlResponseDto convertToLongUrl(@RequestParam("url") String shortenUrl) {
		return shortenUrlService.convertToLongUrl(shortenUrl);
	}


	@ApiOperation(value = "단축 URL로 링크의 클릭정보 조회", notes = "제공 API에서 최대 1000개 조회가능")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "url", value = "단축 URL", required = true),
			@ApiImplicitParam(name = "unit", value = "시간 단위", required = true),
	})
	@GetMapping("/linkinfo")
	public List<LinkInfoResponseDto> fetchLinkClickInfos(@RequestParam("url") String shortenUrl,
														 @RequestParam("unit") Unit unit) {
		return shortenUrlService.fetchLinkClickInfos(shortenUrl, unit);
	}
}
