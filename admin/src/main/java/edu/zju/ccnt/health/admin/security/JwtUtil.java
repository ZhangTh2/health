package edu.zju.ccnt.health.admin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    final static String base64EncodedSecretKey = "1G78Y160lo3W2E7H97";//私钥
    final static long TOKEN_EXP = 1000 * 6000;//过期时间,测试使用6000秒

    //Map<String,Object> claims = new HashMap<String,Object>();//在AWT里面的payload

    public static String getToken(String id) {
        return Jwts.builder()
                .setSubject(id) //代表JWT的拥有者，以uid作为唯一标识
                .setIssuedAt(new Date())//jwt签发时间
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey) //前面使用的算法和密钥
                .compact();
    }

    /**
     * @Desc:检查token,只要不正确就会抛出异常
     **/
    public static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }

    /**
     * 解密Token
     * @param token
     * @return String uid
     * @throws Exception
     */
    public static String getIdByToekn(String token) {
        final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
