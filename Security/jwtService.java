package app.Api.Security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtService {
	private static final String KEY = "eyJhbGciOiJIUzI1NiJ95ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoIiwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn0pPyfVxDhOQRg8JXGIl1I87tClv8F3gFiE4tVoGW5ADsI";
	public String extractName(String jwt) {
		
		return extractClaim(jwt, Claims::getSubject);
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
		
	}
	public String generateToken(Map<String, Object> extractClaims,UserDetails usrdetails) {
		long expirationTimeInMillis = TimeUnit.HOURS.toMillis(24); // Set to 24 hours
	    Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMillis);

		return Jwts.builder()
				.claims()
				.add(extractClaims)
				.subject(usrdetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(expirationDate)
				.and()
				.signWith(getSignInKey(), Jwts.SIG.HS256)
				.compact()
				;
				
		
	}
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractName(token);
		return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token, userDetails);
	}
	private boolean isTokenExpired(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
	               .verifyWith(getSignInKey())
	               .build()
	               .parseSignedClaims(token)
	               .getPayload();
	               
	              
	}

		
	private SecretKey getSignInKey() {
		// TODO Auto-generated method stub
		byte [] keybytes = Decoders.BASE64.decode(KEY);
		
		return Keys.hmacShaKeyFor(keybytes);
	}
}
