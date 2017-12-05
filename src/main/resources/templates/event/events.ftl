<html>
<head>
    <title>Events List</title>

    <style>
        table, th, td {
            border: 1px solid black;
        }
        tr:hover td {
            background: #5bc0de;
        }
    </style>
</head>

<body>
<h1>Events List</h1>
<br>
<#if commName??>
<h2>${commName}</h2>
</#if>
Table elements are <b>clickable</b>
<table style="">
    <tr>
        <th>name</th>
        <th>rate</th>
        <th>basePrice</th>
        <th>dateTime</th>
        <th>auditorium</th>
    </tr>

<#list events as event>
    <#if commName??>
        <tr onclick="window.location = '/booking/event/${event.id}'">
    <#else>
        <tr onclick="window.location = '/events/name/${event.name}'">
    </#if>

        <td>${event.name} </td>
        <td>${event.rate} </td>
        <td>${event.basePrice} </td>
        <td>${event.dateTime} </td>
        <td>${event.auditorium.name} </td>
    </tr>
</#list>

</table>

</body>
</html>