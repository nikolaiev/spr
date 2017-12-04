<html>
<head>
    <title>Users List</title>
</head>

<body>
<h1>Users List</h1>

<ul>
<#list users as user>
    <li>${user.email} </li>
    <li>${user.name} </li>
    <li>${user.id} </li>
    <li>${user.birthday} </li>
    <br>
</#list>
</ul>

</body>
</html>