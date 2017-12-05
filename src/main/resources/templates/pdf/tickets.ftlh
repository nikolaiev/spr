<!DOCTYPE HTML>
<html>
<head>
    <title>TICKETS</title>

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
<h1>TICKETS</h1>
<#if tickets?has_content>
    <br></br>
    <table style="">
        <tr>
            <th>code</th>
            <th>event</th>
            <th>event date</th>
            <th>auditorium</th>
            <th>seats</th>
            <th>price per one seat</th>
            <th>order date</th>
            <th>user name</th>
        </tr>

    <#list tickets as item>
        <tr>
            <td>${item.id};</td>
            <td>${item.event.name}</td>
            <td>${item.event.dateTime}</td>
            <td>${item.event.auditorium.name}</td>
            <td>${item.seats}</td>
            <td>${item.price}</td>
            <td>${item.dateTime}</td>
            <td>${item.user.name}</td>
        </tr>
    </#list>
    </table>
<#else>
    <h2>No orders was created</h2>
</#if>



</body>
</html>