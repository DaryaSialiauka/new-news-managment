<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@page
isELIgnored="false" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="spring"
uri="http://www.springframework.org/tags"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form"%>

<spring:message code="singin.login" var="loclogin" />
<spring:message code="singin.password" var="locpassword" />
<spring:message code="singin.button.singin" var="locsingin" />
<spring:message code="singin.error" var="locerror" />

<div class="row align-items-start">
  <form action="authentication" method="post">
    <div class="col-12 text-center text-danger">
      &nbsp;<c:if test="${login_error eq true}"><c:out value="${locerror}"/></c:if>&nbsp;
    </div>
    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control"
        id="login"
        name="login"
        required
      /><label for="login" class="form-label"><c:out value="${loclogin}"/></label>
    </div>
    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="password"
        class="form-control"
        id="password"
        name="password"
        required
      />
      <label for="password" class="form-label"><c:out value="${locpassword}"/></label>
    </div>
    <div class="col-12 text-center">
      <input type="submit" class="btn btn-primary" value="${locsingin}" />
    </div>
  </form>
</div>
