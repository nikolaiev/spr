<html>
<head>
    <title>Auditoriums List</title>
</head>

<body>
<h1>Auditorium List</h1>

<ul>
<#list auditoriums as aud>
    <li>${aud.id} </li>
    <li>${aud.name} </li>
    <li>${aud.seatsNumber} </li>
    <li>${aud.vipSeats} </li>

    <br>
</#list>
</ul>

</body>
</html>