<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@page
isELIgnored="false" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="spring"
uri="http://www.springframework.org/tags"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form"%> <%@ taglib prefix= "security"
uri= "http://www.springframework.org/security/tags" %>

<spring:message code="news.datepattern" var="locdate" />
<spring:message code="news.deletenews" var="locdeletenews" />

<div class="row" id="list">
  <c:if test="${not(news eq null)}">
    <form action="deletenews" method="post">
      <c:forEach var="news" items="${news}">
        <div class="col-12">
          <div class="card border-white">
            <div class="card-body row">
              <h6 class="card-title col-10"><c:out value="${news.title}" /></h6>

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

              <h6 class="card-title col-2 text-end">
                <c:out value="${date}" />
              </h6>
              <p class="card-text">
                <c:out value="${news.brief}" />
              </p>

              <div class="text-end">
                <security:authorize access="isAuthenticated()">
                  <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <a href="editnews?id_news=${news.id}" class="card-link"
                      ><spring:message code="news.editnews" /></a
                  ></security:authorize>

                  <a href="getnews?id_news=${news.id}" class="card-link"
                    ><spring:message code="news.viewnews"
                  /></a>
                  <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      name="id_news"
                      value="${news.id}"
                  /></security:authorize>
                </security:authorize>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>

      <div class="d-md-flex justify-content-md-end">
        <security:authorize access="isAuthenticated()">
          <security:authorize access="hasAnyRole('ROLE_ADMIN')">
            <input
              type="submit"
              class="btn btn-primary me-md-2"
              value="${locdeletenews}"
            /> </security:authorize
        ></security:authorize>
      </div>
    </form>

    <div class="col-12">
      <security:authorize access="isAuthenticated()">
        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${quantityPage}">
              <c:if test="${param.pagenum eq page}">
                <li class="page-item active">
                  <a class="page-link" href="news?pagenum=${page}"
                    ><c:out value="${page}"
                  /></a>
                </li>
              </c:if>
              <c:if test="${not(param.pagenum eq page)}">
                <li class="page-item">
                  <a class="page-link" href="news?pagenum=${page}"
                    ><c:out value="${page}"
                  /></a>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </nav>
        <div
          class="btn-group-vertical"
          role="group"
          aria-label="Vertical button group"
        >
          <c:if test="${(param.quantity eq null) || (param.quantity eq 5)}">
            <button
              type="button"
              class="btn btn-primary"
              onClick="window.location='news?pagenum=1&quantity=5'"
            >
              5
            </button>
            <button
              type="button"
              class="btn btn-secondary"
              onClick="window.location='news?pagenum=1&quantity=10'"
            >
              10
            </button>
          </c:if>
          <c:if test="${(param.quantity eq 10)}">
            <button
              type="button"
              class="btn btn-secondary"
              onClick="window.location='news?pagenum=1&quantity=5'"
            >
              5
            </button>
            <button
              type="button"
              class="btn btn-primary"
              onClick="window.location='news?pagenum=1&quantity=10'"
            >
              10
            </button>
          </c:if>
        </div>
      </security:authorize>
    </div>
  </c:if>
  <c:if test="${requestScope.news eq null}">
    <div class="col-6 offset-3">
      <div class="text-center alert alert-primary">
        <c:out value="${param.news_error}" />
      </div>
    </div>
  </c:if>
</div>
