package by.it_academy.dao;

import java.util.List;

import by.it_academy.bean.News;
import by.it_academy.dao.exception.NewsDAOException;

public interface NewsDAO {

	News getNews(int id) throws NewsDAOException;
	
	List<News> getListNews(int quantityNewsPage, int page) throws NewsDAOException;
	
	void saveNews(News news) throws NewsDAOException;
	
	void deleteNews(int[] id) throws NewsDAOException;
	
	void updateNews(News news) throws NewsDAOException;
	
	int quantityNews() throws NewsDAOException;
}
