<#import "parts/pageTemplate.ftl" as p>
<@p.page cssFile="css/homepage.css">


<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <div>
        <label>Username: <input type="text" name="username"/></label>
    </div>
    <div>
        <label>Password: <input type="password" name="password"/></label>
    </div>
    <div>
        <button type="submit">Log in</button>
    </div>
</form>
</body>
</html>


</@p.page>