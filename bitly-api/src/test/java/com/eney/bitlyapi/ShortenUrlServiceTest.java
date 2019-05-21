package com.eney.bitlyapi;

import com.eney.bitlyapi.model.dto.UrlResponseDto;
import com.eney.bitlyapi.model.enums.Unit;
import com.eney.bitlyapi.service.ShortenUrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by YoungMan on 2019-05-20.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortenUrlServiceTest {

	@Autowired
	ShortenUrlService shortenUrlService;

	@Test
	public void convertToShortenUrl_존재사이트() throws Exception {

		//given
		String longUrl = "https://eney.co.kr/";

		//when
		UrlResponseDto responseDto = shortenUrlService.convertToShortenUrl(longUrl);

		//then
		assertThat(responseDto.getStatusCode(), is(200));
		assertThat(responseDto.getLongUrl(), is(longUrl));

		System.out.println(responseDto.toString());
	}

	@Test
	public void convertToShortenUrl_미존재사이트_결과는같다() throws Exception {

		//given
		String longUrl = "https://hahadw.com/";

		//when
		UrlResponseDto responseDto = shortenUrlService.convertToShortenUrl(longUrl);

		//then
		assertThat(responseDto.getStatusCode(), is(200));
		assertThat(responseDto.getLongUrl(), is(longUrl));

		System.out.println(responseDto.toString());
	}

	@Test
	public void convertToLongUrl() throws Exception {

		//given
		String shortenUrl = "http://bit.ly/2w4Gp8k";

		//when
		UrlResponseDto responseDto = shortenUrlService.convertToLongUrl(shortenUrl);

		//then
		assertThat(responseDto.getStatusCode(), is(200));
		assertThat(responseDto.getShortenUrl(), is(shortenUrl));

		System.out.println(responseDto.toString());
	}

	@Test
	public void fetchLinkClickInfos() throws Exception {

		//given
		String shortenUrl = "http://bit.ly/2w4Gp8k";
		Unit unit = Unit.day;

		//when
		shortenUrlService.fetchLinkClickInfos(shortenUrl, unit);

		//then
//		assertThat(responseDto.getStatusCode(), is(200));
//		assertThat(responseDto.getLongUrl(), is(longUrl));

//		System.out.println(responseDto.toString());
	}

	@Test
	public void fetchLinkClickInfosByUser() throws Exception {

		//when
		shortenUrlService.fetchLinkClickInfosByUser();

		//then
//		assertThat(responseDto.getStatusCode(), is(200));

//		System.out.println(responseDto.toString());
	}

}
