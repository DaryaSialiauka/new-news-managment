<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags"%> <%@ taglib prefix=
"security" uri= "http://www.springframework.org/security/tags" %>

<spring:message code="nav.news" var="locnews" />
<spring:message code="nav.newslist" var="locnewslist" />
<spring:message code="nav.addnews" var="locaddnews" />

<div class="row p-2 m-1 bg-secondary bg-opacity-50">
  <div class="bg-secondary col-11 offset-1 mb-1 text-white text-center">
    <spring:message code="nav.news" />
  </div>

  <div class="col-9 offset-3 bg-white">
    <div class="col-12">
      <a class="text-center" href="news"
        ><img src="./image/plus.svg" alt="" width="15" />
        <spring:message code="nav.newslist" />
      </a>
    </div>

    <security:authorize access="hasAnyRole('ROLE_ADMIN')">
      <div class="col-12">
        <a class="text-center" href="addnews"
          ><img src="./image/plus.svg" alt="" width="15" /><spring:message
            code="nav.addnews"
          />
        </a>
      </div>
    </security:authorize>
  </div>
</div>
