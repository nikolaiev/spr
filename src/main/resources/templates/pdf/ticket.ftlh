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
<h1>TICKET</h1>
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


    <tr>
        <td>${ticket.id}</td>
        <td>${ticket.event.name}</td>
        <td>${ticket.event.dateTime}</td>
        <td>${ticket.event.auditorium.name}</td>
        <td>${ticket.seats}</td>
        <td>${ticket.price}</td>
        <td>${ticket.dateTime}</td>
        <td>${ticket.user.name}</td>
    </tr>

</table>

</body>
</html>