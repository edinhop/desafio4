package com.desafio.agenda.api.controller;

import com.desafio.agenda.domain.model.User;
import com.desafio.agenda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AuthFilter implements Filter {

    @Autowired
    UserRepository userRepository;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String requestURL = req.getRequestURL().toString();

        if (requestURL.contains("admin")) {
            String apiKey = ((HttpServletRequest) request).getHeader("X-API-Key");

            /*
            Em produção, isso seria uma chave gerada e acessada via variáveis de ambiente, mas coloquei hardcoded apenas
            para ilustrar o comportamento de uma chave de admin
             */

            if (!apiKey.equals("admin")) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not an administrator");
            }
        } else if (requestURL.contains("/user") && req.getMethod().equals("POST")) {
            //Usado apenas para permitir a criação de usuários sem necessidade de autenticação
        } else {
            String token = ((HttpServletRequest) request).getHeader("Authorization");

            if (token != null && token.toLowerCase().startsWith("basic")) {
                final String[] values = decodeToken(token);
                User user = userRepository.findContactByUserId(values[0], values[1]);

                if (user == null) {
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
                }

                request.setAttribute("user", user);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login");
            }
        }

        chain.doFilter(request, response);
    }

    private String[] decodeToken(String token) {
        String base64Credentials = token.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        return values;
    }
}
