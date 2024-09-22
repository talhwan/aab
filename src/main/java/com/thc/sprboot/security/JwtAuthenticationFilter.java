package com.thc.sprboot.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thc.sprboot.dto.TbuserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Transactional
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final ObjectMapper objectMapper;
	private final AuthService authService;
	private final ExternalProperties externalProperties;

	/**
     *  로그인하려는 사용자의 자격을 확인해 토큰을 발급하는 함수.
	 *  "/api/login" 으로 들어오는 요청에 실행된다.
	 *  생성된 Authentication이 SecurityContextHolder에 등록되어 권한처리가 가능하게 한다.
	 *  
	 *  @throws AuthenticationException
	 */
	@Transactional
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		Authentication authentication = null;
		TbuserDto.LoginReqDto tbuserLoginDto = null;

		//1번. 로그인에 필요한 아이디(username)이랑 비번(password)가 있는지 먼저 확인!!!
		try {
			tbuserLoginDto = objectMapper.readValue(request.getInputStream(), TbuserDto.LoginReqDto.class);
		} catch (IOException e) {
			System.out.println("1. login attemptAuthentication : Not Enough Parameters?!");
			//e.printStackTrace();
		}

		//1번. 로그인에 필요한 아이디(username)이랑 비번(password)으로 실제 존재하는 고객인지 확인!!!
		//=> 그 정보로 Authentication 객체를 만들꺼에요!!!
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tbuserLoginDto.getUsername(), tbuserLoginDto.getPassword());
			authentication = authenticationManager.authenticate(authenticationToken);
		} catch (AuthenticationException e) {
			System.out.println("2. login attemptAuthentication : username, password Not Mathced?!");
			//e.printStackTrace();
		}
		
		return authentication;
	} 
	
	/**
     *  로그인 완료시 호출되는 함수.
     *  Refresh Token을 발급해 HttpServletRespons에 담는다.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		// TbuserId로 리프레시토큰 발급
		System.out.println("principalDetails.getTbuser().getId() : " + principalDetails.getTbuser().getId());
		String refreshToken = authService.createRefreshToken(principalDetails.getTbuser().getId());
		String accessToken = authService.issueAccessToken(refreshToken);

		// header에 담아서 전달!!
		response.addHeader(externalProperties.getRefreshKey(), externalProperties.getTokenPrefix() + refreshToken);
		response.addHeader(externalProperties.getAccessKey(), externalProperties.getTokenPrefix() + accessToken);
		// 바디에도 담아줍시다.
		//TbuserDto.TbuserLoginDto tbuserLoginDto = new TbuserDto.TbuserLoginDto(externalProperties.getTokenPrefix() + refreshToken, externalProperties.getTokenPrefix() + accessToken);
		//response.getWriter().write(tbuserLoginDto.toString());
		//response.getWriter().write(externalProperties.getTokenPrefix() + refreshToken);
		
		System.out.println("successfulAuthentication : login success?!");
	}
	
}