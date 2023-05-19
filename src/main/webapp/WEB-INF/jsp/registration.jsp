<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@page
isELIgnored="false" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="spring"
uri="http://www.springframework.org/tags"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form"%>



<spring:message code="reg.firstname" var="locfirstname" />
<spring:message code="reg.lastname" var="loclastname" />
<spring:message code="reg.datebirth" var="locdatebirth" />
<spring:message code="reg.login" var="loclogin" />
<spring:message code="reg.email" var="locemail" />
<spring:message code="reg.phone" var="locphone" />
<spring:message code="reg.password" var="locpassword" />
<spring:message code="reg.button.reg" var="locreg" />
<spring:message code="reg.error" var="locerror" />

<jsp:useBean id="now" class="java.util.Date" />

<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />

<div class="row align-items-start">
  <form:form action="doregist" method="post" modelAttribute="user">
    <div class="col-12 text-center text-danger">
      &nbsp;<c:if test="${reg_error eq true}"
        ><c:out value="${locerror}" /></c:if
      >&nbsp;
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <form:input
        type="text"
        class="form-control"
        id="firstname"
        path="firstname"
        placeholder="First name"
        required="true"
      />
      <label for="firstname" class="form-label"
        ><c:out value="${locfirstname}"
      /></label>
      <div id="firstnameFeedback" class="invalid-feedback d-block">
        <form:errors path="firstname" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <form:input
        type="text"
        class="form-control"
        id="lastname"
        path="lastname"
        placeholder="Last name"
        required="true"
      />
      <label for="lastname" class="form-label"
        ><c:out value="${loclastname}"
      /></label>
      <div id="lastnameFeedback" class="invalid-feedback d-block">
        <form:errors path="lastname" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <form:input
        type="date"
        class="form-control"
        id="datebirth"
        path="datebirth"
        placeholder="Date birth"
        max="${date}"
        required="true"
      />
      <label for="datebirth" class="form-label"
        ><c:out value="${locdatebirth}"
      /></label>
      <div id="datebirthFeedback" class="invalid-feedback d-block">
        <form:errors path="datebirth" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <form:input
        type="text"
        class="form-control"
        id="login"
        path="login"
        placeholder="Login"
        required="true"
      />
      <label for="login" class="form-label"
        ><c:out value="${loclogin}"
      /></label>
      <div id="loginFeedback" class="invalid-feedback d-block">
        <form:errors path="login" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <form:input
        type="email"
        class="form-control form-control-sm"
        id="email"
        path="email"
        placeholder="Email"
        required="true"
      />
      <label for="email" class="form-label"
        ><c:out value="${locemail}"
      /></label>
      <div id="emailFeedback" class="invalid-feedback d-block">
        <form:errors path="email" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <form:input
        type="tel"
        class="form-control form-control-sm"
        id="phone"
        path="phone"
        pattern="^\\+375(\\s+)?\\(?(17|25|29|33|44)\\)?(\\s+)?[0-9]{3}-?[0-9]{2}-?[0-9]{2}$"
        placeholder="+375(__)___-__-__"
        required="true"
      />
      <label for="phone" class="form-label"
        ><c:out value="${locphone}"
      /></label>
      <div id="phoneFeedback" class="invalid-feedback d-block">
        <form:errors path="phone" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <form:input
        type="password"
        class="form-control form-control-sm"
        id="password"
        path="password"
        placeholder="Password"
        required="true"
      />
      <label for="password" class="form-label"
        ><c:out value="${locpassword}"
      /></label>
      <div id="passwordFeedback" class="invalid-feedback d-block">
        <form:errors path="password" />
      </div>
    </div>

    <div class="col-12 text-center">
      <input type="submit" class="btn btn-primary" value="${locreg}" />
    </div>
  </form:form>
</div>
