package by.it_academy.dao.impl;

import java.util.List;
import java.util.Objects;

import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.it_academy.bean.News;
import by.it_academy.dao.NewsDAO;
import by.it_academy.dao.exception.NewsDAOException;

@Repository
public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public News getNews(int id) throws NewsDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			News news = session.get(News.class, id);
			if (Objects.nonNull(news)) {
				return news;
			} else {
				throw new NewsDAOException("News didn't find");
			}
		} catch (HibernateException e) {
			throw new NewsDAOException("Error get news", e);
		}
	}

	private final static String SELECT_LIST_NEWS = "FROM News ORDER BY dateCreate DESC";

	@Override
	public List<News> getListNews(int quantityNewsPage, int page) throws NewsDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();

			Query<News> q = session.createQuery(SELECT_LIST_NEWS, News.class);
			q.setFirstResult((page - 1) * quantityNewsPage);
			q.setMaxResults(quantityNewsPage);

			List<News> listNews = q.getResultList();
			if (!listNews.isEmpty()) {
				return listNews;
			} else {
				throw new NewsDAOException("News didn't find");
			}
		} catch (HibernateException e) {
			throw new NewsDAOException("Error get list news", e);
		}
	}

	@Override
	public void saveNews(News news) throws NewsDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(news);
		} catch (HibernateException e) {
			throw new NewsDAOException("Error save news", e);
		}
	}

	@Override
	public void deleteNews(int[] id) throws NewsDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			for (int i : id) {
				session.delete(session.get(News.class, i));
			}
		} catch (HibernateException e) {
			throw new NewsDAOException("Error delete news", e);
		}

	}

	public void updateNews(News news) throws NewsDAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(news);
		} catch (HibernateException e) {
			throw new NewsDAOException("Error update news", e);
		}
	}

	private final static String SELECT_QUANTITY_NEWS = "select count(n) From News n";
	
	public int quantityNews() throws NewsDAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			int i = ((Long) (session.createQuery(SELECT_QUANTITY_NEWS).uniqueResult())).intValue();
			return i;
		} catch (HibernateException e) {
			throw new NewsDAOException("Error get quantity", e);
		}

	}

}
