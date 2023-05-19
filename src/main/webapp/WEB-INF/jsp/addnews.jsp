<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@page
isELIgnored="false" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="spring"
uri="http://www.springframework.org/tags"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form"%>

<spring:message code="addview.erroradd" var="locerroradd" />
<spring:message code="addview.newstitle" var="locnewstitle" />
<spring:message code="addview.newsdate" var="locnewsdate" />
<spring:message code="addview.newsbrief" var="locnewsbrief" />
<spring:message code="addview.newscontent" var="locnewscontent" />
<spring:message code="addview.save" var="locsave" />
<spring:message code="addview.cancel" var="loccancel" />
<spring:message code="addview.lang" var="loclang" />

<jsp:useBean id="now" class="java.util.Date" />

<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />

<form:form action="savenews" method="post" modelAttribute="news">
  <form:input type="hidden" path="id" />

  <div class="row">
    <div class="col-3 py-3 ps-5 text-secondary">
      <c:out value="${locnewstitle}" />
    </div>

    <div class="col-8 py-3">
      <form:input
        type="text"
        class="form-control"
        path="title"
        required="true"
      />
    </div>

    <div id="titleFeedback" class="invalid-feedback d-block text-center">
      <form:errors path="title" />
    </div>

    <div class="col-3 py-3 ps-5 text-secondary">
      <c:out value="${locnewsdate}" />
    </div>

    <div class="col-8 py-3">
      <form:input
        type="date"
        class="form-control"
        path="dateCreate"
        max="${date}"
        required="true"
      />
    </div>

    <div id="dateFeedback" class="invalid-feedback d-block text-center">
      <form:errors path="dateCreate" />
    </div>

    <div class="col-3 py-3 ps-5 text-secondary">
      <c:out value="${locnewsbrief}" />
    </div>

    <div class="col-8 py-3">
      <form:textarea
        class="form-control"
        path="brief"
        style="height: 50px"
        required="true"
      />
    </div>

    <div id="briefFeedback" class="invalid-feedback d-block text-center">
      <form:errors path="brief" />
    </div>

    <div class="col-3 py-3 ps-5 text-secondary">
      <c:out value="${locnewscontent}" />
    </div>

    <div class="col-8 py-3">
      <form:textarea
        class="form-control"
        path="content"
        style="height: 100px"
        required="true"
      />
    </div>

    <div id="contentFeedback" class="invalid-feedback d-block text-center">
      <form:errors path="content" />
    </div>
  </div>

  <div class="col-12 text-center">
    <input type="submit" class="btn btn-primary" value="${locsave}" />
    <input
      type="button"
      onClick="window.location='Controller?command=GO_TO_BASE_PAGE&pagenum=1'"
      class="btn btn-secondary"
      value="${loccancel}"
    />
  </div>
</form:form>
