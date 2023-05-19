package by.it_academy.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.it_academy.bean.News;
import by.it_academy.bean.User;
import by.it_academy.service.NewsService;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.NewsServiceException;
import by.it_academy.service.exception.UserServiceException;

@Controller
@RequestMapping("/")
public class ControllerNews {

	@Autowired
	private NewsService providerNewsService;

	@Autowired
	private UserService providerUserService;

	@Autowired
	private AuthenticationManager authenticationManager;

	private final static String PAGE = "page";
	private final static String USER = "user";
	private final static String NEWS = "news";
	private final static String ID_NEWS = "id_news";
	private final static String VIEWNEWS = "viewnews";
	private final static String ADDNEWS = "addnews";
	private final static String SINGIN = "singin";
	private final static String REGISTRATION = "registration";
	private final static String HAS_ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
	private final static String HAS_ROLE_USER_AND_ADMIN = "hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')";
	private final static String REDIRECT_ERROR_PAGE = "redirect:/error-page";
	private final static String REDIRECT_NEWS_PAGE = "redirect:/news";
	private final static String REDIRECT_GETNEWS = "redirect:/getnews?id_news=";
	private final static String BASE_PAGE = "base_page";
	private final static String QUANTITY = "quantity";
	private final static String PAGENUM = "pagenum";
	private final static String QUANTITY_PAGE = "quantityPage";
	private final static String PARAM_TRUE = "true";
	private final static String PARAM_FALSE = "false";

	private final static String LOGIN_ERROR = "login_error";
	private final static String REG_ERROR = "reg_error";

	@RequestMapping("/news")
	public String main(Model model, @RequestParam(value = PAGENUM, defaultValue = "1") int pagenum,
			@RequestParam(value = QUANTITY, defaultValue = "5") int quantity) {
		List<News> news;
		int quantityPage;

		try {
			quantityPage = providerNewsService.quantityNews(quantity);
			news = providerNewsService.getListNews(quantity, pagenum);
			model.addAttribute(NEWS, news);
			model.addAttribute(PAGE, NEWS);
			model.addAttribute(QUANTITY_PAGE, quantityPage);
		} catch (NewsServiceException e) {
			return REDIRECT_ERROR_PAGE;
		}
		return BASE_PAGE;
	}

	@PreAuthorize(HAS_ROLE_USER_AND_ADMIN)
	@RequestMapping("/getnews")
	public String getNews(Model model, @RequestParam(ID_NEWS) int idnews) {
		News news = null;
		try {
			model.addAttribute(PAGE, VIEWNEWS);
			news = providerNewsService.getNews(idnews);
			model.addAttribute(NEWS, news);
		} catch (NewsServiceException e) {
			return REDIRECT_ERROR_PAGE;
		}

		return BASE_PAGE;
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@RequestMapping("/editnews")
	public String editNews(Model model, @RequestParam(ID_NEWS) int idnews) {
		News news = null;
		try {
			model.addAttribute(PAGE, ADDNEWS);
			news = providerNewsService.getNews(idnews);
			model.addAttribute(NEWS, news);
		} catch (NewsServiceException e) {
			return REDIRECT_ERROR_PAGE;
		}

		return BASE_PAGE;
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@RequestMapping("/addnews")
	public String addNews(Model model) {
		News news = new News();
		model.addAttribute(NEWS, news);
		model.addAttribute(PAGE, ADDNEWS);
		return BASE_PAGE;
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@RequestMapping("/savenews")
	public String saveNews(@Valid @ModelAttribute(NEWS) News news, BindingResult bindingResult, Model model,
			Principal principal) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(PAGE, ADDNEWS);
			return BASE_PAGE;
		}

		try {
			model.addAttribute(PAGE, VIEWNEWS);
			model.addAttribute(NEWS, news);
			news.setUser(providerUserService.getUser(principal.getName()));

			if (news.getId() > 0) {
				providerNewsService.updateNews(news);
			} else {
				providerNewsService.saveNews(news);
			}
		} catch (NewsServiceException | UserServiceException e) {
			return REDIRECT_ERROR_PAGE;
		}

		return REDIRECT_GETNEWS + news.getId();
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@RequestMapping("/deletenews")
	public String deleteNews(Model model, @RequestParam(ID_NEWS) int[] idnews) {

		try {
			if (idnews.length > 0) {
				providerNewsService.deleteNews(idnews);
				model.addAttribute(PAGE, NEWS);
			}
		} catch (NewsServiceException e) {
			return REDIRECT_ERROR_PAGE;
		}

		return REDIRECT_NEWS_PAGE;
	}

	@RequestMapping("/singin")
	public String singin(Model model,
			@RequestParam(value = LOGIN_ERROR, defaultValue = PARAM_FALSE) boolean loginError) {
		model.addAttribute(LOGIN_ERROR, loginError);
		model.addAttribute(PAGE, SINGIN);
		return BASE_PAGE;
	}

	@RequestMapping("/regist")
	public String regist(Model model) {
		User user = new User();
		model.addAttribute(USER, user);
		model.addAttribute(PAGE, REGISTRATION);
		return BASE_PAGE;
	}

	@RequestMapping("/doregist")
	public String registration(Model model, @Valid @ModelAttribute(USER) User user, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(PAGE, REGISTRATION);
			model.addAttribute(REG_ERROR, PARAM_TRUE);
			return BASE_PAGE;
		}

		String username = user.getLogin();
		String password = user.getPassword();

		try {
			providerUserService.saveUser(user);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

			request.getSession();

			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

		} catch (UserServiceException e) {
			return REDIRECT_ERROR_PAGE;
		}
		return REDIRECT_NEWS_PAGE;

	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model) {
		return REDIRECT_ERROR_PAGE;
	}

}
