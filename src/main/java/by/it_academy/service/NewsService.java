package by.it_academy.service;

import java.util.List;

import by.it_academy.bean.News;
import by.it_academy.service.exception.NewsServiceException;

public interface NewsService {
	
	News getNews(int id) throws NewsServiceException;
	
	List<News> getListNews(int quantityNewsPage, int page) throws NewsServiceException;
	
	void saveNews(News news) throws NewsServiceException;
	
	void deleteNews(int[] id) throws NewsServiceException;
	
	void updateNews(News news) throws NewsServiceException;
	
	int quantityNews(int quantityNewsPage) throws NewsServiceException;

}
