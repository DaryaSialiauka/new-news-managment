package by.it_academy.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.it_academy.bean.News;
import by.it_academy.dao.NewsDAO;
import by.it_academy.dao.exception.NewsDAOException;
import by.it_academy.service.NewsService;
import by.it_academy.service.exception.NewsServiceException;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO providerNews;

	@Transactional
	public News getNews(int id) throws NewsServiceException {

		try {
			return providerNews.getNews(id);
		} catch (NewsDAOException e) {
			throw new NewsServiceException("Error get news", e);
		}
	}

	@Transactional
	public List<News> getListNews(int quantityNewsPage, int page) throws NewsServiceException {

		try {
			return providerNews.getListNews(quantityNewsPage, page);
		} catch (NewsDAOException e) {
			throw new NewsServiceException("Error get list news", e);
		}
	}

	@Transactional
	public void saveNews(News news) throws NewsServiceException {
		try {
			providerNews.saveNews(news);
		} catch (NewsDAOException e) {
			throw new NewsServiceException("Error save news", e);
		}
	}

	@Transactional
	public void deleteNews(int[] id) throws NewsServiceException {
		try {
			providerNews.deleteNews(id);
		} catch (NewsDAOException e) {
			throw new NewsServiceException("Error delete news", e);
		}
	}

	@Transactional
	public void updateNews(News news) throws NewsServiceException {
		try {
			providerNews.updateNews(news);
		} catch (NewsDAOException e) {
			throw new NewsServiceException("Error update news", e);
		}
	}

	@Transactional
	public int quantityNews(int quantityNewsPage) throws NewsServiceException {
		int quantityNews;
		try {
			quantityNews = providerNews.quantityNews();
			return (int) Math.ceil(quantityNews / (double) quantityNewsPage);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}

	}

}
