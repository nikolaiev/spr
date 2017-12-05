<html>
<head>
    <title>Users List</title>

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
<h1>Users List</h1>
<br>
Table elements are <b>clickable</b>
<table style="">
    <tr>
        <th>Email</th>
        <th>Name</th>
        <th>Birthday</th>
    </tr>

    <#list users as user>
    <tr onclick="window.location = '/users/id/${user.id}'">
        <td>${user.email} </td>
        <td>${user.name} </td>
        <td>${user.birthday} </td>
    </tr>
    </#list>

</table>

</body>
</html>