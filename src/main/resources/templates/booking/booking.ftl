<html>
<head>
    <title>Users List</title>

    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>

<body>
<h1>Users List</h1>
<br>

<h3>Choose user to order ticket for</h3>
<table>
    <tr>
        <th>Email</th>
        <th>Name</th>
        <th>Birthday</th>
        <th>Seats</th>
    </tr>

<#list users as user>
    <tr>
        <td>${user.email} </td>
        <td>${user.name} </td>
        <td>${user.birthday} </td>


<td>
    <form action="/booking/book" method="post">
        <input type="hidden" name="user_id" value="${user.id}"/>
        <input type="hidden" name="event_id" value="${event_id}"/>
        <input type="text" name="seats" required/>
        <button type="submit" >ORDER</button>
    </form>
</td>
    </tr>
</#list>

</table>

</body>
</html>