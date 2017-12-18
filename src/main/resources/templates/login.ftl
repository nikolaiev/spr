<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>
<body>
<#--<#if (request.getParameter("error")?has_content && request.getParameter("error")?lower_case?matches("true"))>-->
<#if RequestParameters.error??>
<h1 style="color: #ff0000">Invalid username or password</h1>
<br>
</#if>

<form th:action="@{/login}" method="post">
    <#--<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>-->
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div>Remember Me: <input type="checkbox" name="remember-me" /> </div>
    <br>
    <div><input type="submit" value="Sign In"/></div>
</form>

<br>
<pre>
    Booking manager login : admin@google.com
    Booking manager pass  : admin

    Simple user login     : laory@yandex.ru
    Simple user pass      : user
</pre>
</body>
</html>