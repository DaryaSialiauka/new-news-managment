<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>
<%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>


<spring:message code="header.localbutton.singin" var="singin"/>
<spring:message code="header.localbutton.singout" var="singout"/> 
<spring:message code="header.localbutton.reg" var="reg"/>

<div class="col-4 d-flex">
  <h3 class="text-primary m-auto">
    <a
      class="text-decoration-none text-reset"
      href="news"
      ><spring:message code="header.title"
    /></a>
  </h3>
</div>
<div class="col-8 row d-flex align-items-center">
  <div class="col-12 text-end">
    <security:authorize access="isAuthenticated()">
      <form action="singout" method="post" class="d-inline">
        <input path="singout" type="submit" class="btn btn-secondary" value="${singout}" />
      </form>
    </security:authorize>

    <security:authorize access="isAnonymous()">
      <form action="singin" method="get" class="d-inline">
        <input path="singin" type="submit" class="btn btn-primary" value="${singin}" />
      </form>

      <form action="regist" method="get" class="d-inline">
        <input path="regist" type="submit" class="btn btn-secondary" value="${reg}" />
      </form>
    </security:authorize>
  </div>
  <div class="col-12 text-end"> 
  
<a href="${currentPage}?languageVar=en" class="p-2"><spring:message code="header.localbutton.en"/></a> 
<a href="${currentPage}?languageVar=be" class="p-2"><spring:message code="header.localbutton.be"/></a>

  </div>
</div>
