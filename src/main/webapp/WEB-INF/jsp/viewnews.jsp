<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@page 
isELIgnored="false" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="spring" 
uri="http://www.springframework.org/tags"%>

<spring:message code="news.datepattern" var="locdate" />

<div class="row" id="view">
  <c:if test="${not(news eq null)}">
    <div class="col-3 py-3 ps-5 text-secondary">
      <spring:message code="addview.newstitle" />
    </div>
    <div class="col-8 py-3"><c:out value="${news.title}" /></div>

    <div class="col-3 py-3 ps-5 text-secondary">
      <spring:message code="addview.newsdate" />
    </div>

    <fmt:parseDate
      value="${news.dateCreate}"
      pattern="yyyy-MM-dd"
      parseLocale="${sessionScope.local}"
      var="parsedDate"
      type="date"
    />

    <fmt:formatDate
      value="${parsedDate}"
      var="date"
      type="date"
      pattern="${locdate}"
    />

    <div class="col-8 py-3"><c:out value="${date}" /></div>

    <div class="col-3 py-3 ps-5 text-secondary">
      <spring:message code="addview.newsbrief" />
    </div>
    <div class="col-8 py-3"><c:out value="${news.brief}" /></div>

    <div class="col-3 py-3 ps-5 text-secondary">
      <spring:message code="addview.newscontent" />
    </div>
    <div class="col-8 py-3"><c:out value="${news.content}" /></div>
  </c:if>
  
  <c:if test="${news eq null}">
    <div class="col-6 offset-3">
      <div class="text-center alert alert-primary">
        &nbsp;<spring:message code="addview.errorview" />&nbsp;
      </div>
    </div>
  </c:if>
</div>
