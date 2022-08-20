package br.com.fiap.soccer;

import br.com.fiap.soccer.security.JwtRequestFilter;
import br.com.fiap.soccer.security.JwtUserDetailsService;
import br.com.fiap.soccer.security.JwtUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

class SoccerApplicationTests {

	@Test
	void contextLoads() {
        JwtUtil jwtUtil = Mockito.any(JwtUtil.class);
        JwtUserDetailsService jwtUserDetailsService = Mockito.any(JwtUserDetailsService.class);
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(jwtUtil, jwtUserDetailsService);
//        Assert.assertEquals(jwtRequestFilter.doFilter(), 2);
    }

}
