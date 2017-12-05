<!DOCTYPE HTML>
<html>
<head>
    TICKETS
</head>
<body>
<h1>Tickets list</h1>
    <ul>
    <#list tickets as item>
        <li>${item.id};</li>
        <li>${item.event.name};</li>
        <li>${item.dateTime};</li>
        <li>${item.seats};</li>
        <li>${item.user.name};</li>
        <li>${item.price};</li>
        <br></br>
        <br></br>
    </#list>
    </ul>
</body>
</html>