<body>

<h1>Batch file upload interface</h1>

<h3>USERS</h3>
<form method="POST" action="/batch/users" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

<br>

<h3>EVENTS</h3>
<form method="POST" action="/batch/events" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>