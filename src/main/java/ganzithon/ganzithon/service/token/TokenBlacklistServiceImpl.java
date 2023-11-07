package ganzithon.ganzithon.service.token;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    @Override
    public void blacklistToken(String token) {
        System.out.println(token);
        blacklistedTokens.add(token);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}