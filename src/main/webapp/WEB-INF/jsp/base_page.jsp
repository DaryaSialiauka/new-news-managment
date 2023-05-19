<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="ISO-8859-1" %>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>News management</title>

    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" 
      rel="stylesheet"
    />
  </head> 
  <body>

    <c:set value="${page}" var="pagelist"/>

    <div class="container mt-1">
      <div class="card">
        <div class="card-header hat row mx-0">
          <c:import url="/WEB-INF/jsp/header.jsp" />
        </div>
        <div class="row" style="min-height: 80vh;">
          <div class="col-2">
            <c:import url="/WEB-INF/jsp/nav.jsp" />
          </div>
          <div class="col-10 border-start border-secondary">

            <c:if test="${pagelist eq 'singin'}">
              <c:import url="/WEB-INF/jsp/singin.jsp" />
            </c:if>

            <c:if test="${pagelist eq 'registration'}">
              <c:import url="/WEB-INF/jsp/registration.jsp" />
            </c:if>

            <c:if test="${pagelist eq 'news'}">
              <c:import url="/WEB-INF/jsp/news.jsp" />
            </c:if>

            <c:if test="${pagelist eq 'viewnews'}">
              <c:import url="/WEB-INF/jsp/viewnews.jsp" />
            </c:if>

            <c:if test="${pagelist eq 'addnews'}">
              <c:import url="/WEB-INF/jsp/addnews.jsp" />
            </c:if>

          </div>
        </div>
        <div class="card-footer">
          <c:import url="/WEB-INF/jsp/footer.jsp" />
        </div>
      </div>
    </div>
  </body>
</html>
