<html>
<head>
    <title>Events List</title>
</head>

<body>
<h1>Events List</h1>

<ul>
<#list events as event>

    <li>${event.id} </li>
    <li>${event.name} </li>
    <li>${event.rate} </li>
    <li>${event.basePrice} </li>
    <li>${event.dateTime} </li>
    <li>${event.auditorium} </li>

</#list>
</ul>

</body>
</html>