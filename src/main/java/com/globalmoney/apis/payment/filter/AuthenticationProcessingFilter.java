package com.globalmoney.apis.payment.filter;

import com.globalmoney.apis.payment.config.SecurityConfigReader;
import com.globalmoney.apis.payment.security.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProcessingFilter.class);

    protected final SecurityConfigReader configReader;
    protected final String serviceIdentifier;
    protected final boolean isUserService;

    @Autowired
    public AuthenticationProcessingFilter(RequestMatcher requestMatcher, SecurityConfigReader configReader) {
        this(requestMatcher, configReader, null);
    }

    @Autowired
    public AuthenticationProcessingFilter(RequestMatcher requestMatcher, SecurityConfigReader configReader, String serviceIdentifier) {
        super(requestMatcher);
        this.configReader = configReader;
        this.serviceIdentifier = serviceIdentifier;
        this.isUserService = ObjectUtils.isEmpty(this.serviceIdentifier);
    }

    /**
     * Performs actual authentication.
     * <p>
     * The implementation should do one of the following:
     * <ol>
     * <li>Return a populated authentication token for the authenticated user, indicating
     * successful authentication</li>
     * <li>Return null, indicating that the authentication process is still in progress.
     * Before returning, the implementation should perform any additional work required to
     * complete the process.</li>
     * <li>Throw an <tt>AuthenticationException</tt> if the authentication process
     * fails</li>
     * </ol>
     *
     * @param request  from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     *                 redirect as part of a multi-stage authentication process (such as OpenID).
     * @return the authenticated user token, or null if authentication is incomplete.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException
    {
        String accountNumber = request.getHeader("account-number");
        String applicationSecret = request.getHeader("application-secret-key");
        if ((!this.isUserService || this.validAccountNumber(accountNumber)) && this.configReader.getAppSecretKey().equals(applicationSecret)) {
            return new AuthenticationToken(accountNumber);
        } else {
            LOGGER.error("Invalid or Missing - accountNumber : {} or application-secret-key", accountNumber);
            throw new SecurityException("Request doesn't have required security headers");
        }
    }

    private boolean validAccountNumber(String rim) {
        return !StringUtils.hasLength(rim);
    }
}
