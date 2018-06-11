package br.com.loov.mycash.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFiltro implements Filter {

    public LoginFiltro() {
    }
    

	public void init(FilterConfig fConfig) throws ServletException {
	}

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		Object usuario = httprequest.getSession().getAttribute("usuario");
		System.out.println(httprequest.getRequestURI());
		boolean regra1 = httprequest.getRequestURI().endsWith("login.jsp");
		boolean regra2 = httprequest.getRequestURI().endsWith("Cadastro.jsp");
		boolean regra3 = httprequest.getRequestURI().endsWith(".css");
		boolean regra4 = httprequest.getRequestURI().endsWith(".js");
		boolean regra5 = httprequest.getRequestURI().endsWith("LoginController");
		boolean regra6 = httprequest.getRequestURI().endsWith("/");
		boolean regra7 = httprequest.getRequestURI().endsWith("UsuarioController");

		if(usuario == null && !( regra1 || regra2 || regra3 || regra4 || regra5 || regra6 || regra7)) {
			httpresponse.sendRedirect("login.jsp");
		} else {
			System.out.println("Filtrando");
			chain.doFilter(request, response);
		}
		
		
	}

}
