<html>
<head>
    <title>User</title>

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
<h1>Simple user</h1>
<br>
<table style="">
    <tr>
        <th>Email</th>
        <th>Name</th>
        <th>Birthday</th>
    </tr>


    <tr>
        <td>${user.email} </td>
        <td>${user.name} </td>
        <td>${user.birthday} </td>
    </tr>

</table>
<br></br>
<form action="/pdf/user/${user.id}" method="get">
    <button type="submit">Get Tickets Lists</button>
</form>

</body>
</html>